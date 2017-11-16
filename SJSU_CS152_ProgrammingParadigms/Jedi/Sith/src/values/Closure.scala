package values

import expressions._
import system._

case class Closure(val params: List[Identifier], val body: Expression, val defEnv: Environment) extends Value {
  def apply(args: List[Value]): Value = {
    if (params.length > args.length)
      throw new InvalidFunctionCall(params.length + " params expected, " + args.length + " args given.")
    val localEnv = Environment(defEnv)
    for (i <- 0 until params.length)
      localEnv.put(params(i), args(i))
    body.execute(localEnv)  
  }
  override def toString = "Closure(Params:"+params.toString()+",Body:"+body.toString()+")"
}