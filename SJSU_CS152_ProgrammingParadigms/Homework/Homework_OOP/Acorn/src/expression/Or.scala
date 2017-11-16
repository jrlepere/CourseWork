package expression

import value._

/**
 * A class for the Or expression
 */
class Or(val op1: Expression, val op2: Expression) extends Expression {
  def execute = {
    val v1 = op1.execute
    if (!v1.isInstanceOf[Boole])
      throw new Exception(toString + " Type mismatch: only Booles for or expression.")
    if (v1.asInstanceOf[Boole].value) Boole.TRUE
    else {
      val v2 = op2.execute
      if (!v2.isInstanceOf[Boole])
        throw new Exception(toString + " Type mismatch: only Booles for or expression.")
      v2.asInstanceOf[Boole]
    }
  }
  override def toString = "(" + op1.toString() + " || " + op2.toString() + ")"
}

/**
 * Companion for the Or class
 */
object Or {
  def apply(op1: Expression, op2: Expression) = new Or(op1, op2)
}