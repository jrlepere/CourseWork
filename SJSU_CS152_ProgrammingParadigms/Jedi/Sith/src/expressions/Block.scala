package expressions

import values._

case class Block(val exps: List[Expression]) extends SpecialForm{
    def execute(env: Environment): Value = {
      val tempEnv = Environment(env)
      var res:Value = Notification.UNSPECIFIED.asInstanceOf[Value]
      for (localExp <- exps)
        res = localExp.execute(tempEnv)
      res
    }
}