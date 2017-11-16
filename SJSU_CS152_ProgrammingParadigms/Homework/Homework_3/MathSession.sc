import scala.math._
import scala.util.Random

object MathSession {

  // Problem 1
  // Solves a quadratic.
  def solve(a: Double, b: Double, c: Double) = {
    val rootExp = b * b - (4 * a * c)
    if (rootExp < 0) None
    else Some(((-b + sqrt(rootExp)) / (2 * a), (-b - sqrt(rootExp)) / (2 * a)))
  }                                               //> solve: (a: Double, b: Double, c: Double)Option[(Double, Double)]

  solve(2, -2, -4)                                //> res0: Option[(Double, Double)] = Some((2.0,-1.0))
  solve(1, 0, 1)                                  //> res1: Option[(Double, Double)] = None
  solve(1, 0, -1)                                 //> res2: Option[(Double, Double)] = Some((1.0,-1.0))

  // Q1.1: How would you modify solve to return complex roots
  // A1.1: I would change the return type to Option[(String,String)].
  //     My doing this, I could return all solutions: None, real, complex.
  //     Unfortunantely, this method forces the method caller to parse the result.


  // Problem 2
  // Computes the distance between 2 points.
  def dist(p1: (Double, Double), p2: (Double, Double)) = {
    val (x1, y1) = p1
    val (x2, y2) = p2
    sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
  }                                               //> dist: (p1: (Double, Double), p2: (Double, Double))Double

  dist((1, 1), (0, 0))                            //> res3: Double = 1.4142135623730951
  dist((3, 0), (0, 0))                            //> res4: Double = 3.0

  // Q2.1: What is the type of dist?
  // A2.1: dist is ((Double,Double),(Double,Double)): Double.
  //     That is, dist takes two tuple pairs of type double and returns a double.
  // Q2.2: How would you modify dist to compute the distance between points in 3 dim space?
  // A2.2: I would modify the tuple parameters to tuples of (Double, Double, Double).
  //     This represents the x coord, y coord, and z coord.
  // Q2.3: What about in N dimensional space?
  // A2.3: If N is known, and small, I could increase the tuple size.
  //     More practically, I would modify the params of dist to be Array[Double],
  //     where the 0 index is the 1 dim coord, 1 index is the 2 coord, and so forth.
  //     The lengths of the arrays would need to be the same and greater than 0.


  // Problem 3
  // Computes the dot product of two "vectors"
  def dot(v1: (Double, Double, Double), v2: (Double, Double, Double)) = {
    (v1._1 * v2._1) + (v1._2 * v2._2) + (v1._3 * v2._3)
  }                                               //> dot: (v1: (Double, Double, Double), v2: (Double, Double, Double))Double

  dot((2.0, 3, 4), (2, 2.0, 2))                   //> res5: Double = 18.0


  // Problem 6
  // Determines if an integer is prime
  def isPrime(x: Integer) = {
    if (x < 0) throw new IllegalArgumentException
    var r = true
    if (x < 2) r = false
    for (i <- 2 until x / 2 + 1) if (x % i == 0) r = false
    r
  }                                               //> isPrime: (x: Integer)Boolean

  isPrime(0)                                      //> res6: Boolean = false
  isPrime(1)                                      //> res7: Boolean = false
  isPrime(2)                                      //> res8: Boolean = true
  isPrime(8)                                      //> res9: Boolean = false


  // Problem 7
  // Computers Euler's phi: # integers k s.t. 0 < k <= n and gcd(n,k) = 1
  def phi(n: Integer) = {
    var count = 0
    for (k <- 1 to n) {
      var gcdIs1 = true
      for (i <- 2 to k)
        if (n % i == 0 && k % i == 0)
          gcdIs1 = false
      if (gcdIs1) count += 1
    }
    count
  }                                               //> phi: (n: Integer)Int

  phi(9)                                          //> res10: Int = 6
  phi(10)                                         //> res11: Int = 4
  
  
  // Problem 8
  // Rolls a die twice.
  def rollDice = {
    (Random.nextInt(6) + 1, Random.nextInt(6) + 1)
  }                                               //> rollDice: => (Int, Int)

  rollDice                                        //> res12: (Int, Int) = (6,4)
  rollDice                                        //> res13: (Int, Int) = (3,3)

  // Q8.1: How would you implement rollDice using the function math.random?
  // A8.1: I would generate the double between 0 and 1 from the function,
  //       and perform cases. See the following code:
  // def rollDice2 = {
  //   val r = math.random
  //   if (r < 1/6) 1
  //   else if (r < 2/6) 2
  //   else if (r < 3/6) 3
  //   else if (r < 4/6) 4
  //   else if (r < 5/6) 5
  //   else 6
  // }
  // Q8.2: Will the same sequence of rolls repeat if you restart the interpreter?
  // A8.2: Yes, this is because the funtion is recalled and new numbers are generated.

}