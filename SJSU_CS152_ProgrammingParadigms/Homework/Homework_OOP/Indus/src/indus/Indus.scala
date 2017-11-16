package indus

/**
 * An Object for testing the Indus application.
 */
object Indus {
  
  val inventory = new scala.collection.mutable.ArrayBuffer[Item]()
  
  def main(args: Array[String]) {
    
    inventory += Item(Description("The Matrix DVD",1550,"DVD World",5))
    inventory += Item("The Terminator DVD",1325,"DVD World",3)
    inventory += Item("Ironman DVD",1800,"DVD Planet",2)
    
    println("ID, Description, Price, Supplier, # In Stock")
    for (i <- 0 until inventory.size)
      println(inventory(i))
  }
  
}