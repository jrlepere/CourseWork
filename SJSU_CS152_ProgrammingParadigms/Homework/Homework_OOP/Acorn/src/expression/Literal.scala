package expression

/**
 * A class for representing Literals.
 */
class Literal extends Expression with value.Value {
  def execute = this
}