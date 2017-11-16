package expressions

import values._

/**
 * A Literal.
 */
trait Literal extends Expression with Value {
  def execute(env: Environment): Value = this
}