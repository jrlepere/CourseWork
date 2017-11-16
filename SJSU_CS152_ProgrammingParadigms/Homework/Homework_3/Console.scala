import scala.util.control.Breaks._

/**
 * A Console object that can execute commands
 */
object Console {

  /**
   * Executes one of the following commands: add, mul, sub, div, quit, help.
   * @param cmnd The command to execute.
   * @return The result of executing the command.
   */
  def execute(cmnd: String): String = {
    var result = ""
    try {
      val operands = cmnd.split("\\s+")
      if (operands.length != 3) throw new IllegalExecutableFormatException
      operands(0) match {
        case "add" => result = operands(1).toDouble + operands(2).toDouble + ""
        case "mul" => result = operands(1).toDouble * operands(2).toDouble + ""
        case "sub" => result = operands(1).toDouble - operands(2).toDouble + ""
        case "div" => result = operands(1).toDouble / operands(2).toDouble + ""
        case _     => throw new IllegalCommandException(operands(0))
      }
    } catch {
      case e: IllegalExecutableFormatException => result = e.getMessage
      case e: IllegalCommandException          => result = e.getMessage
      case e: NumberFormatException            => result = "Error! " + e.getMessage
      case e: Exception                        => result = e.getMessage
    }
    result
  }

  /**
   * Read, execute, print, loop.
   */
  def repl {
    println("Enter an executable expression")
    breakable {
      while (true) {
        print("==> ")
        var cmnd = readLine()
        if (cmnd.equals("quit")) break
        if (cmnd.equals("help")) println("Commands: add, mul, sub, div, quit, help")
        else println(execute(cmnd))
      }
    }
    println("Bye")
  }

  /**
   * The main method for the program.
   * @param args The command line arguments. Not used.
   * @returns A unit.
   */
  def main(args: Array[String]): Unit = {
    repl
  }

}

/**
 * An exception for an illegal executable format.
 */
class IllegalExecutableFormatException extends Exception {
  override def getMessage =
    "Executable must be in the form: cmnd arg1 arg2"
}

/**
 * An exception for an illegal command.
 */
class IllegalCommandException(cmnd: String) extends Exception {
  override def getMessage =
    "Unrecognizable command: " + cmnd
}