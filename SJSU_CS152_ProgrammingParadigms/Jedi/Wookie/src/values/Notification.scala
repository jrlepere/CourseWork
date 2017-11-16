package values

class Notification(val message: String) extends Value {
  override def toString = message  
}

object Notification {
  def apply(message: String) =  new Notification(message)
  val OK = Notification("ok")
  val DONE = Notification("done")
  val UNSPECIFIED = Notification("unspecified")
}