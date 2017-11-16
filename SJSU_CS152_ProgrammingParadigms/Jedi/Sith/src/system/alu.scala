package system

import expressions._
import values._

object alu {

  // HARD CODE operators
  val ADD = "add"
  val SUBTRACT = "subtract"
  val MULTIPLY = "multiple"
  val DIVIDE = "divide"
  val EQUAL = "equal"
  val LESSTHAN = "lessthan"
  val GREATERTHAN = "greaterthan"
  val CONTENT = "content"
  val MAKEVAR = "makevar"

  def execute(operator: Identifier, args: List[Value]): Value = {
    operator.name match {
      case ADD => add(args)
      case SUBTRACT => subtract(args)
      case MULTIPLY => multiply(args)
      case DIVIDE => divide(args)
      case EQUAL => equal(args)
      case LESSTHAN => lessthan(args)
      case GREATERTHAN => greaterthan(args)
      case CONTENT => content(args)
      case MAKEVAR => makevar(args)
      case _ => throw new UndefinedException(operator)
    }
  }

  private def add(args: List[Value]): Number = {
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length)
      throw new TypeException("Inputs to add must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2.reduce(_ + _)
  }

  private def subtract(args: List[Value]): Number = {
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length)
      throw new TypeException("Inputs to subtract must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2.reduce(_ - _)
  }

  private def multiply(args: List[Value]): Number = {
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length)
      throw new TypeException("Inputs to multiply must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2.reduce(_ * _)
  }

  private def divide(args: List[Value]): Number = {
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length)
      throw new TypeException("Inputs to divide must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2.reduce(_ / _)
  }

  private def lessthan(args: List[Value]): Boole = {
    // if (args.length != 2) throw new SyntaxException("");
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length) throw new TypeException("Inputs to < must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2(0) < nums2(1)
  }
  
  private def greaterthan(args: List[Value]): Boole = {
    // if (args.length != 2) throw new SyntaxException("");
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length) throw new TypeException("Inputs to > must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    nums2(0) > nums2(1)
  }

  private def equal(args: List[Value]): Boole = {
    var nums = args.filter(_.isInstanceOf[Number])
    if (nums.length != args.length) throw new TypeException("Inputs to == must be numbers")
    val nums2 = nums.map(_.asInstanceOf[Number])
    var result = Boole.TRUE
    var i = 0
    while (i < nums2.length - 1 && result.value){
      if (nums2(i) != nums2(i + 1))
        result = result.!
      i += 1
    }
    result
  }
  
  private def content(args: List[Value]): Value = {
    if (!args.head.isInstanceOf[Variable]) throw new TypeException("Can only get the content of Variables")
    args.head.asInstanceOf[Variable].content
  }
  
  private def makevar(args: List[Value]) = Variable(args.head)

}