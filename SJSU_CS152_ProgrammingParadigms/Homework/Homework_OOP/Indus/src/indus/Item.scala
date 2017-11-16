package indus

/**
 * An Item in the Indus Store.
 */
class Item(private val desc: Description) {
  // Instance Variables
  private val id: Int = Item.nextId
  Item.nextId += 1

  // Additional Constuctors
  def this(desc: String, price: Int, supplier: String, inStock: Int) = this(Description(desc, price, supplier, inStock))

  // Override AnyRef methods
  override def toString = id + ", " + desc

}

/**
 * Companion Object for the Item Class.
 */
object Item {
  private var nextId: Int = 0
  def apply(desc: Description) = new Item(desc)
  def apply(desc: String, price: Int, supplier: String, inStock: Int) = new Item(Description(desc, price, supplier, inStock))
}