package values

import expressions._

case class Number(val value: Double) extends Literal {
  def +(other: Number) = Number(value + other.value)
  def -(other: Number) = Number(value - other.value)
  def *(other: Number) = Number(value * other.value)
  def /(other: Number) = Number(value / other.value)
  def <(other: Number) = Boole(value < other.value)
  def >(other: Number) = Boole(value > other.value)
  def ==(other: Number) = Boole(value == other.value)
  def !=(other: Number) = Boole(value != other.value)
  override def toString = value.toString
}