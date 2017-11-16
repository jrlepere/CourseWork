package values

import scala.collection.mutable.HashMap
import system._
import expressions._

class Environment(var extension: Environment = null) extends HashMap[Identifier,Value] with Value {
 
  override def apply(name: Identifier) = {
    if (this.contains(name)) super.apply(name)
    else if (extension != null) extension(name)
    else throw new UndefinedException(name)
  }
}