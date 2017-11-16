// A session to practice recursive functions.
// 02/28/2017
object RecursionSession {

  // Initial Declarations
  def inc(n: Int) = n + 1                         //> inc: (n: Int)Int
  def dec(n: Int) = n - 1                         //> dec: (n: Int)Int
  def isZero(n: Int) = n == 0                     //> isZero: (n: Int)Boolean

  // ======== Problem 1 ========
  // Adds two values using only recursion, inc, and dec.
  def add(n: Int, m: Int): Int =
    if (isZero(m)) n
    else inc(add(n, dec(m)))                      //> add: (n: Int, m: Int)Int

  add(3, 4)                                       //> res0: Int = 7
  add(5, 10)                                      //> res1: Int = 15
  add(5, 0)                                       //> res2: Int = 5
  add(0, 5)                                       //> res3: Int = 5
  add(0, 0)                                       //> res4: Int = 0

  // ======== Problem 2 ========
  // Multiplies two values using only recursing, add, inc, and dec.
  def mul(n: Int, m: Int): Int =
    if (isZero(m)) 0
    else add(n, mul(n, dec(m))) // n*m = n+n*m-n = n+(n*(m-1))
                                                  //> mul: (n: Int, m: Int)Int

  mul(2, 3)                                       //> res5: Int = 6
  mul(0, 0)                                       //> res6: Int = 0
  mul(5, 0)                                       //> res7: Int = 0
  mul(0, 5)                                       //> res8: Int = 0
  mul(12, 12)                                     //> res9: Int = 144
  mul(5, 4)                                       //> res10: Int = 20

  // ======== Problem 3 ========
  // 2^m using recursion, mul, add, inc, and dec
  def exp2(n: Int): Int =
    if (isZero(n)) 1
    else mul(2, exp2(dec(n))) // 2^M = 2 * 2^(m-1)//> exp2: (n: Int)Int

  exp2(0)                                         //> res11: Int = 1
  exp2(1)                                         //> res12: Int = 2
  exp2(5)                                         //> res13: Int = 32
  exp2(8)                                         //> res14: Int = 256

  // ======== Problem 4 ========
  // hyper-exponentiation function using only recursion, exp2, mul, add, inc, dec
  def hyperExp(n: Int): Int =
    if (isZero(n)) 1
    else exp2(hyperExp(dec(n)))                   //> hyperExp: (n: Int)Int

  hyperExp(0)                                     //> res15: Int = 1
  hyperExp(1)                                     //> res16: Int = 2
  hyperExp(3)                                     //> res17: Int = 16
  // hyperExp(5) => Overflow

  // ======== Problem 5.1 ========
  def tailAdd(n: Int, m: Int) = {
    def helper(count: Int, result: Int): Int =
      if (count == n) result
      else helper(inc(count), inc(result))
    helper(0, m)
  }                                               //> tailAdd: (n: Int, m: Int)Int

  tailAdd(0, 5)                                   //> res18: Int = 5
  tailAdd(5, 0)                                   //> res19: Int = 5
  tailAdd(4, 3)                                   //> res20: Int = 7

  // ======== Problem 5.2 ========
  def tailMul(n: Int, m: Int) = {
    def helper(count: Int, result: Int): Int =
      if (count == n) result
      else helper(inc(count), tailAdd(m, result))
    helper(0, 0)
  }                                               //> tailMul: (n: Int, m: Int)Int

  tailMul(0, 5)                                   //> res21: Int = 0
  tailMul(5, 0)                                   //> res22: Int = 0
  tailMul(0, 0)                                   //> res23: Int = 0
  tailMul(4, 10)                                  //> res24: Int = 40
  tailMul(16, 3)                                  //> res25: Int = 48

  // ======== Problem 5.3 ========
  def tailExp2(n: Int) = {
    def helper(count: Int, result: Int): Int =
      if (count == n) result
      else helper(inc(count), tailMul(2, result)) // 2^n = 2 * 2 ^ n-1
    helper(0, 1)
  }                                               //> tailExp2: (n: Int)Int

  tailExp2(0)                                     //> res26: Int = 1
  tailExp2(1)                                     //> res27: Int = 2
  tailExp2(4)                                     //> res28: Int = 16
  tailExp2(8)                                     //> res29: Int = 256

  // ======== Problem 5.4 ========
  def tailHyperExp(n: Int) = {
    def helper(count: Int, result: Int): Int =
      if (count == n) result
      else helper(inc(count), tailExp2(result))
    helper(0, 1)
  }                                               //> tailHyperExp: (n: Int)Int

  tailHyperExp(0)                                 //> res30: Int = 1
  tailHyperExp(1)                                 //> res31: Int = 2
  tailHyperExp(3)                                 //> res32: Int = 16
  tailHyperExp(4)                                 //> res33: Int = 65536


	// ======== Problem 9 ========
	def fibRec(n: Int): Int =
		if (n == 0 || n == 1) 1
		else fibRec(n-1) + fibRec(n-2)    //> fibRec: (n: Int)Int
	
	def fibTailRec(n: Int) = {
		def helper(count: Int, result1: Int, result2:Int): Int =
			if (count == n) result2
			else helper(count+1,result2, result1+result2)
		helper(0,0,1)
	}                                         //> fibTailRec: (n: Int)Int


	fibRec(0)                                 //> res34: Int = 1
	fibTailRec(0)                             //> res35: Int = 1
	fibRec(1)                                 //> res36: Int = 1
	fibTailRec(1)                             //> res37: Int = 1
	fibRec(2)                                 //> res38: Int = 2
	fibTailRec(2)                             //> res39: Int = 2
	fibRec(3)                                 //> res40: Int = 3
	fibTailRec(3)                             //> res41: Int = 3
	fibRec(4)                                 //> res42: Int = 5
	fibTailRec(4)                             //> res43: Int = 5
	fibRec(10)                                //> res44: Int = 89
	fibTailRec(10)                            //> res45: Int = 89
	fibRec(20)                                //> res46: Int = 10946
	fibTailRec(20)                            //> res47: Int = 10946
 
	
	// ======== Problem 10 ========
	def choose(n: Int, m: Int): Int =
		if (m > n) 0
		else if (m == 0) 1
		else choose(n-1,m) + choose(n-1,m-1)
                                                  //> choose: (n: Int, m: Int)Int

	choose(0,0)                               //> res48: Int = 1
	choose(1,0)                               //> res49: Int = 1
	choose(1,2)                               //> res50: Int = 0
	choose(4,3)                               //> res51: Int = 4
	choose(16,6)                              //> res52: Int = 8008
	(16*15*14*13*12*11)/(1*2*3*4*5*6)         //> res53: Int = 8008
	
}