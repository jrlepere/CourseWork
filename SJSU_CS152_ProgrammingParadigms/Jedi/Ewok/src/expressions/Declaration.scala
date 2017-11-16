package expressions

import values._

/**
 * A Declaration.
 */
case class Declaration(val id: Identifier, val exp: Expression) extends SpecialForm {
   def execute(env: Environment): Value = {
     val result = exp.execute(env)
     env(id) = result
     Notification.DONE
   }
}