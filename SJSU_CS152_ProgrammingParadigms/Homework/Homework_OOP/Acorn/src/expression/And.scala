package expression

import value._

/**
 * A class for the And expression
 */
class And(val op1: Expression, val op2: Expression) extends Expression {
  def execute = {
    val v1 = op1.execute
    if (!v1.isInstanceOf[Boole])
      throw new Exception(toString + " Type mismatch: only Booles for and expression.")
    if (!v1.asInstanceOf[Boole].value) Boole.FALSE
    else {
      val v2 = op2.execute
      if (!v2.isInstanceOf[Boole])
        throw new Exception(toString + " Type mismatch: only Booles for and expression.")
      v2.asInstanceOf[Boole]
    }
  }
  override def toString = "(" + op1.toString() + " && " + op2.toString() + ")"
}

/**
 * Companion for the And class
 */
object And {
  def apply(op1: Expression, op2: Expression) = new And(op1, op2)
}