package system

import expressions._
import scala.util.parsing.combinator._
import scala.util.Failure

class JediException(val gripe: String = "Jedi exception")
  extends Exception(gripe)

class UndefinedException(val irritant: Identifier)
  extends JediException("Undefined identifier: " + irritant)

class SyntaxException(val result: console.parsers.Failure, val msg: String = "Syntax Exception")
  extends JediException("Syntax exception: " + msg)

class TypeException(val irritant: String)
  extends JediException("Type exception: " + irritant)

class InvalidFunctionCall(val irritant: String)
  extends JediException("Invalid Function Call: " + irritant)