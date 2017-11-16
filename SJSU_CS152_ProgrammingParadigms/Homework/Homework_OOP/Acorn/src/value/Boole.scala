package value

/**
 * A class for Boolean values.
 */
class Boole(val value: Boolean) extends expression.Literal {
  def &&(other: Boole) = Boole(value && other.value)
  def ||(other: Boole) = Boole(value || other.value)
  def ! = Boole(!value)
  override def toString = value.toString()
}

/**
 * Companion Object for Boolean class.
 */
object Boole {
  def apply(value: Boolean) = new Boole(value)
  val TRUE = Boole(true)
  val FALSE = Boole(false)
}