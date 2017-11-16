package expressions

import values._

/**
 * An Expression trait.
 */
trait Expression {
  /**
   * Performs execution of an expression.
   * @return The result of executing the expression.
   */
  def execute(env: Environment): Value
}