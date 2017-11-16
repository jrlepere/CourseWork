package expression

import value._

/**
 * A class for the Not expression
 */
class Not(val op1: Expression) extends Expression {
  def execute = {
    val v1 = op1.execute
    if (!v1.isInstanceOf[Boole])
      throw new Exception(toString + " Type mismatch: only Booles for not expression.")
    v1.asInstanceOf[Boole]!
  }
  override def toString = "!" + op1.toString()
}

/**
 * Companion for the Not class
 */
object Not {
   def apply(op1: Expression) = new Not(op1) 
}