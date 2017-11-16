package expressions

import values._
import system.TypeException

/**
 * A case class for a Loop expression. Executes the body, count number of times. 
 */
case class Loop(count: Expression, body: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    val iterations = count.execute(env);
    if (!iterations.isInstanceOf[Number]) throw new TypeException("Loop counter must be a Number.")
    val maxCount = iterations.asInstanceOf[Number].value
    var iter = 0
    while (iter < maxCount) {body.execute(env); iter += 1}
    Notification.DONE
  }
}