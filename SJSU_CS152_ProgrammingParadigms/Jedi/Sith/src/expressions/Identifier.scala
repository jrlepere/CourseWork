package expressions

import values._
import system._

case class Identifier(val name: String) extends Expression {
  def execute(env: Environment): Value =
    if (env(this).isInstanceOf[Closure]) {
      if (env(this).asInstanceOf[Closure].params.length == 0)
        env(this).asInstanceOf[Closure].apply(Nil)
      else
        throw new InvalidFunctionCall(name + " should be called with parameters")
    }
    else
      env(this)
}

object Identifier {
  val TEMP_IDENTIFIER = "temp_id"
}