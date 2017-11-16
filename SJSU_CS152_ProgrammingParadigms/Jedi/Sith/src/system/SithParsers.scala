package system

import scala.util.parsing.combinator._
import expressions._
import values._

class SithParsers extends RegexParsers {

  def expression: Parser[Expression] = loop |declaration | conditional | iteration |assignment | disjunction | failure("Invalid expression")

  def loop: Parser[Loop] = "loop" ~ "[" ~ expression ~ "]" ~ expression ^^ 
  {
    case "loop" ~ "[" ~ count ~ "]" ~ body => Loop(count, body)
  }
  
  def declaration: Parser[Declaration] = "def" ~ identifier ~ "=" ~ expression ^^
    {
      case "def" ~ id ~ "=" ~ exp => Declaration(id, exp)
    }
  
  def iteration: Parser[Iteration] = "while" ~ "(" ~ expression ~ ")" ~ expression ^^ {
    case "while" ~ "(" ~ cond ~ ")" ~ body => Iteration(cond, body)
  }
  
  def assignment: Parser[Assignment] = identifier ~ "=" ~ expression ^^ {
    case id ~ "=" ~ exp => Assignment(id, exp)
  }

  def conditional: Parser[Conditional] = "if" ~ "(" ~ expression ~ ")" ~ expression ~ opt("else" ~ expression) ^^
    {
      case "if" ~ "(" ~ exp ~ ")" ~ then ~ None => Conditional(exp, then, None)
      case "if" ~ "(" ~ exp ~ ")" ~ then ~ Some("else" ~ otherwise) => Conditional(exp, then, Some(otherwise))
    }

  def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^
    {
      case con ~ Nil => con
      case con ~ cons => Disjunction(con :: cons)
    }

  def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^
    {
      case eq ~ Nil => eq
      case eq ~ eqs => Conjunction(eq :: eqs)
    }

  def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^
    {
      case in ~ Nil => in
      case in ~ ins => FunCall(Identifier(alu.EQUAL), in :: ins)
    }

  def inequality: Parser[Expression] = sum ~ opt(("<" | ">") ~ sum) ^^
    {
      case s ~ None => s
      case s ~ Some("<" ~ ss) => FunCall(Identifier(alu.LESSTHAN), List(s, ss))
      case s ~ Some(">" ~ ss) => FunCall(Identifier(alu.GREATERTHAN), List(s, ss))
    }

  def sum: Parser[Expression] =
    product ~ rep(("+" | "-") ~ product ^^ { case "+" ~ s => s case "-" ~ s => negate(s) }) ^^ {
      case p ~ Nil => p
      case p ~ rest => FunCall(Identifier(alu.ADD), p :: rest)
    }

  def product: Parser[Expression] = term ~ rep(("*" | "/") ~ term ^^ { case "*" ~ p => p case "/" ~ p => invert(p) }) ^^
    {
      case t ~ Nil => t
      case t ~ rest => FunCall(Identifier(alu.MULTIPLY), t :: rest)
    }

  def negate(exp: Expression): Expression =
    FunCall(Identifier(alu.SUBTRACT), List(Number(0), exp))

  def invert(exp: Expression): Expression =
    FunCall(Identifier(alu.DIVIDE), List(Number(1), exp))

  def term: Parser[Expression] = deref | lambda | block | literal | funcall | "(" ~ expression ~ ")" ^^
    {
      case "(" ~ exp ~ ")" => exp
    }
  
  def deref: Parser[Expression] = "["~ expression ~"]"~ opt(operands) ^^ 
  {
    case "[" ~ exp ~ "]" ~ None => FunCall(Identifier(alu.CONTENT), List(exp))
    case "[" ~ exp ~ "]" ~ Some(ops) => {
      Block(List(Declaration(Identifier(Identifier.TEMP_IDENTIFIER), FunCall(Identifier(alu.CONTENT), List(exp))),
          FunCall(Identifier(Identifier.TEMP_IDENTIFIER), ops)))
    }
  }

  def lambda: Parser[Expression] = undefined_lambda | "lambda" ~ parameters ~ expression ^^
    {
      case "lambda" ~ params ~ exp => Lambda(params, exp)
    }

  def undefined_lambda: Parser[Expression] = "(" ~ lambda ~ ")" ~ operands ^^
    {
      case "(" ~ fun ~ ")" ~ ops => {
        Block(List(Declaration(Identifier(Identifier.TEMP_IDENTIFIER), fun),
          FunCall(Identifier(Identifier.TEMP_IDENTIFIER), ops)))
      }
    }
  
  def parameters: Parser[List[Identifier]] = "(" ~> opt(identifier ~ rep("," ~> identifier)) <~ ")" ^^
    {
      case None => Nil
      case Some(e ~ Nil) => List(e)
      case Some(e ~ exps) => e :: exps
      case _ => Nil
    }

  def block: Parser[Expression] = "{" ~ opt(expression) ~ rep(";" ~> expression) ~ "}" ^^
    {
      case "{" ~ None ~ Nil ~ "}" => Block(Nil)
      case "{" ~ None ~ exps ~ "}" => Block(exps)
      case "{" ~ Some(exp) ~ Nil ~ "}" => Block(exp :: Nil)
      case "{" ~ Some(exp) ~ exps ~ "}" => Block(exp :: exps)
    }

  def funcall: Parser[Expression] = identifier ~ opt(operands) ^^
    {
      case t ~ None => t
      case t ~ Some(ops) => FunCall(t, ops)
    }

  def operands: Parser[List[Expression]] = "(" ~ opt(expression ~ rep("," ~> expression)) ~ ")" ^^ {
    case "(" ~ None ~ ")" => List()
    case "(" ~ Some(exp ~ Nil) ~ ")" => exp :: Nil
    case "(" ~ Some(exp ~ exps) ~ ")" => exp :: exps
  }

  def literal: Parser[Literal] = (boole | number)

  def identifier: Parser[Identifier] = """[a-zA-Z][a-zA-Z0-9]*""".r ^^
    {
      case id => Identifier(id);
    }

  def number: Parser[Number] = opt("+" | "-") ~ """[0-9]""".r ~ rep("""[0-9]""".r) ~ opt("""\.""".r ~ """[0-9]""".r ~ rep("""[0-9]""".r)) ^^
    {
      case None ~ d ~ Nil ~ None => Number(d.toString().toDouble)
      case Some("+") ~ d ~ Nil ~ None => Number(d.toString().toDouble)
      case Some("-") ~ d ~ Nil ~ None => Number((-1) * d.toString().toDouble)
      case None ~ d ~ ds ~ None => Number(toString(d :: ds).toDouble)
      case Some("+") ~ d ~ ds ~ None => Number(toString(d :: ds).toDouble)
      case Some("-") ~ d ~ ds ~ None => Number((-1) * toString(d :: ds).toDouble)
      case None ~ d ~ ds ~ Some("." ~ f ~ Nil) => Number((toString(d :: ds) + "." + f.toString()).toDouble)
      case Some("+") ~ d ~ ds ~ Some("." ~ f ~ Nil) => Number((toString(d :: ds) + "." + f.toString()).toDouble)
      case Some("-") ~ d ~ ds ~ Some("." ~ f ~ Nil) => Number((-1) * (toString(d :: ds) + "." + f.toString()).toDouble)
      case None ~ d ~ ds ~ Some("." ~ f ~ fs) => Number((toString(d :: ds) + "." + toString(f :: fs)).toDouble)
      case Some("+") ~ d ~ ds ~ Some("." ~ f ~ fs) => Number((toString(d :: ds) + "." + toString(f :: fs)).toDouble)
      case Some("-") ~ d ~ ds ~ Some("." ~ f ~ fs) => Number((-1) * (toString(d :: ds) + "." + toString(f :: fs)).toDouble)
    }

  def boole: Parser[Boole] = """true|false""".r ^^
    {
      case "true" => Boole.TRUE
      case "false" => Boole.FALSE
    }

  private def toString(l: List[String]): String = {
    def helper(count: Integer, result: String): String = {
      if (count >= l.length) result
      else helper(count + 1, result + l(count))
    }
    helper(0, "")
  }

}