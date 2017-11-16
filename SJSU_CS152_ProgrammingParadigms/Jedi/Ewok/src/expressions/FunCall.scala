package expressions

import values._
import system._

case class FunCall(val operator: Identifier, operands: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    val args = operands.map(_.execute(env))
    alu.execute(operator, args)
  }
}