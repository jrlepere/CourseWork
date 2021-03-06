package expressions

import values._
import system.TypeException

case class Assignment(variable: Identifier, update: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    val varExe = variable.execute(env)
    if (!varExe.isInstanceOf[Variable]) throw new TypeException("Can only make assignment calls to Variables.")
    varExe.asInstanceOf[Variable].content = update.execute(env)
    Notification.DONE
  }
}