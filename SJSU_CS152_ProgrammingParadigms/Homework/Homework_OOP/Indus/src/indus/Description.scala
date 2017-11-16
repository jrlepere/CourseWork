package indus

/**
 * A Description for an Item.
 */
class Description(var description: String, var price: Int, var supplier: String, var inStock: Int) { 
  override def toString = description + ", " + convertPrice + ", " + supplier + ", " + inStock
  private def convertPrice = {
    var res = "$" + (price)/100 + "."
    if (price % 100 < 10) res += "0"
    res + price % 100
  }
}

/**
 * Companion Object for the Description Class.
 */
object Description {
  def apply(description: String, price: Int, supplier: String, inStock: Int) = new Description(description, price, supplier, inStock)
}