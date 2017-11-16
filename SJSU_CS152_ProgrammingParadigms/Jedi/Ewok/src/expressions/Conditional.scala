package expressions

import values._
import system._

case class Conditional(val exp: Expression, val then: Expression, val otherwise: Option[Expression]) extends SpecialForm {

  def execute(env: Environment): Value = {

    val cond = exp.execute(env)
    if (!cond.isInstanceOf[Boole]) throw new TypeException(null)
    if (cond.asInstanceOf[Boole].value) then.execute(env)
    else otherwise match {
      case Some(other) => other.execute(env)
      case None => Notification.UNSPECIFIED
    }

  }

}