package expression

import value._

/**
 * A class for the Difference expression
 */
class Difference(val op1: Expression, val op2: Expression) extends Expression {
  def execute = {
    val v1 = op1.execute
    val v2 = op2.execute
    if (!(v1.isInstanceOf[Number]) || !(v2.isInstanceOf[Number]))
      throw new Exception(toString + " Type mismatch: only Numbers for difference expression.")
    v1.asInstanceOf[Number] - v2.asInstanceOf[Number]
  }
  override def toString = "(" + op1.toString() + " - " + op2.toString() + ")"
}

/**
 * A Companion object for Sum class.
 */
object Difference {
  def apply(op1: Expression, op2: Expression) = new Difference(op1, op2)
}