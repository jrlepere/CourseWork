package expressions

import values._

case class Lambda(val params: List[Identifier], val exp: Expression) extends SpecialForm{
    def execute(env: Environment): Value = {
      Closure(params, exp, env)
    }
}