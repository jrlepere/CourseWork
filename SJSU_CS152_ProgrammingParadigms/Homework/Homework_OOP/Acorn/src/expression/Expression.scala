package expression

import value._

/**
 * An Expression trait.
 */
trait Expression {
  /**
   * Performs execution of an expression.
   * @return The result of executing the expression.
   */
  def execute: Value
}