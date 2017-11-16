package system

import expression._
import value._

/**
 * A singleton for testing.
 */
object test {
  def execute(exp: Expression) {
    try {
      val v = exp.execute
      println(exp + " = " + v)
    } catch {
      case e: Exception => println(e)
    }
  }
    
  def main(args: Array[String]) {
    // Numbers Exclusive
    println("Number Testing")
    execute(Sum(Number(128), Number(128)))
    execute(Difference(Number(3.1), Number(4.2)))
    execute(Product(Number(2),Number(1000)))
    execute(Quotient(Number(32),Number(4)))
    execute(Sum(Sum(Number(3.1), Number(4.2)), Sum(Number(3.5), Number(2.8))))
    execute(Product(Sum(Number(2), Number(2)), Difference(Number(4), Number(2))))
    execute(Quotient(Product(Sum(Number(2), Number(2)), Difference(Number(4), Number(2))),Number(2)))
    execute(Equals(Number(1),Number(10)))
    execute(Equals(Number(16),Number(16)))
    execute(LessThan(Number(1),Number(10)))
    execute(GreaterThan(Number(1),Number(10)))
    println
    // Booles
    println("Boole Testing")
    execute(And(Boole(true), Boole.FALSE))
    execute(Or(Boole(false), Boole.TRUE))
    execute(Not(Boole.FALSE))
    execute(And(Not(Or(Boole.FALSE,Boole(false))),Not(Boole.FALSE)))
    execute(And(Boole.FALSE,Product(Number(12),Sum(Number(2),Number(1234)))))
    execute(Or(Boole.TRUE,Product(Number(12),Sum(Number(2),Number(1234)))))
    println
    // Mix
    println("Combination")
    execute(Equals(Sum(Number(1),Number(1)),Number(2)))
    execute(Or(LessThan(Number(1),Number(2)),Equals(Number(1),Number(2))))
    println
    // Errors
    println("Error Testing")
    execute(Sum(Number(3.1), Boole.FALSE))
    execute(Product(Number(1),Not(Number(3.1))))
  }
}