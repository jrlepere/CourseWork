package system

import expressions._
import values._

object console {
  val parsers = new WookieParsers
  val globalEnv = new Environment

  def execute(cmmd: String): String = {
    val tree = parsers.parseAll(parsers.expression, cmmd)
    tree match {
      case t: parsers.Failure => throw new SyntaxException(t)
      case _ => {
        val exp = tree.get
        val result = exp.execute(globalEnv)
        result.toString
      }
    }
  }

  def repl {
    var more = true;
    while (more) {
      try {
        // read/execute/print
        print("-> ")
        val cmmd = readLine()
        if (cmmd.equals("quit")) more = false
        else println(execute(cmmd))
      } catch {
        case e: SyntaxException => {
          println(e.msg)
          println(e.result.msg)
          println("line # = " + e.result.next.pos.line)
          println("column # = " + e.result.next.pos.column)
          println("token = " + e.result.next.first)
        }
        case e: UndefinedException =>
          println("Undefined Identifier: " + e.irritant.name)
        case e: TypeException =>
          println("Type Exception: " + e.irritant)

      } finally {
        Console.flush
      }
    }
    println("Bye")
  }

  def main(args: Array[String]): Unit = {
    repl
  }
}