object RecursiveSession {
  
  // Computes the factorial recursively
  def factRec(n: Int): Int =
  	if (n < 1) 1 else n * factRec(n-1)        //> factRec: (n: Int)Int
  
  // Computes the factorial iteratively
 	def factIter(n: Int) = {
 		var res = 1
 		for (i <- 1 to n) res *= i
 		res
 	}                                         //> factIter: (n: Int)Int
  
  // Computes the factorial using the tail recursive method
  def factTail(n: Int) = {
  	def helper(count: Int, result: Int): Int =
  		if (n < count) result else helper(count+1,result*count)
	  helper(1,1)
  }                                               //> factTail: (n: Int)Int
  
  // Comparison of the three methods
  factRec(0)                                      //> res0: Int = 1
  factIter(0)                                     //> res1: Int = 1
  factTail(0)                                     //> res2: Int = 1
  
  factRec(4)                                      //> res3: Int = 24
  factIter(4)                                     //> res4: Int = 24
  factTail(4)                                     //> res5: Int = 24
  
  factRec(19)                                     //> res6: Int = 109641728
  factIter(19)                                    //> res7: Int = 109641728
  factTail(19)                                    //> res8: Int = 109641728
}