package expression

import value._

/**
 * A class for the Equal expression
 */
class Equals(val op1: Expression, val op2: Expression) extends Expression {
  def execute = {
    val v1 = op1.execute
    val v2 = op2.execute
    if (!(v1.isInstanceOf[Number]) || !(v2.isInstanceOf[Number]))
      throw new Exception(toString + " Type mismatch: only Numbers for Equal expression.")
    v1.asInstanceOf[Number] == v2.asInstanceOf[Number]
  }
  override def toString = "(" + op1.toString() + " == " + op2.toString() + ")"
}

/**
 * A Companion object Equal Sum class.
 */
object Equals {
  def apply(op1: Expression, op2: Expression) = new Equals(op1, op2)
}