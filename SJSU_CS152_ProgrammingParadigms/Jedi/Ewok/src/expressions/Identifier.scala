package expressions

import values._

case class Identifier(val name: String) extends Expression {
  def execute(env: Environment): Value = env(this)
}