package value

import expression.Literal

/**
 * Representation of a Number.
 */
class Number(val value: Double) extends Literal {
   def +(other: Number) = Number(value + other.value)
   def -(other: Number) = Number(value - other.value)
   def *(other: Number) = Number(value * other.value)
   def /(other: Number) = Number(value / other.value)
   def <(other: Number) = Boole(value < other.value)
   def >(other: Number) = Boole(value > other.value)
   def ==(other: Number) = Boole(value == other.value)
   override def toString = value.toString
}

/**
 * Companion object for Number class.
 */
object Number {
  def apply(value: Double) = new Number(value)
}