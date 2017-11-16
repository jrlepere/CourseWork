package expressions

import values._
import system._

case class FunCall(val operator: Identifier, operands: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    val args = operands.map(_.execute(env))
    try {
      if (operator.name.equals(FunCall.VAR))
        alu.execute(Identifier(alu.MAKEVAR), args)
      else {
        val fun = env(operator)
        if (fun.isInstanceOf[Closure])
          fun.asInstanceOf[Closure].apply(args)
        else
          throw new TypeException(operator.name)
      }
    } catch {
      case e: UndefinedException => alu.execute(operator, args)
    }
  }
}

object FunCall {
  val VAR = "var"
}