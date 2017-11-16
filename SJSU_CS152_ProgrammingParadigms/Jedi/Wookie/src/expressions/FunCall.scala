package expressions

import values._
import system._

case class FunCall(val operator: Identifier, operands: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    val args = operands.map(_.execute(env))
    try {
     val fun = env(operator)
     if (fun.isInstanceOf[Closure])
        fun.asInstanceOf[Closure].apply(args)
     else
      throw new TypeException(operator.name)
    } catch {
      case e: UndefinedException => alu.execute(operator, args)
    }
  }
}