package expressions

import values._
import system.TypeException

case class Iteration(condition: Expression, body: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    while ({
      val condExe = condition.execute(env)
      if (!condExe.isInstanceOf[Boole]) throw new TypeException("Condition must be a Boole")
      condExe.asInstanceOf[Boole].value
    })
      body.execute(env)
    Notification.DONE
  }
}