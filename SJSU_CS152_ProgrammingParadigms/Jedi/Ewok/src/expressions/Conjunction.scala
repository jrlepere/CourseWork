package expressions

import values._
import system._

case class Conjunction(val exps: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    def helper(count: Integer, result: Boole): Boole = {
      if (!result.value || count == exps.length) result
      else {
        val execution = exps(count).execute(env)
        if (!execution.isInstanceOf[Boole]) throw new TypeException("Inputs must be boole")
        helper(count+1,result && execution.asInstanceOf[Boole])
      }
    }
    helper(0,Boole.TRUE)
  }
}