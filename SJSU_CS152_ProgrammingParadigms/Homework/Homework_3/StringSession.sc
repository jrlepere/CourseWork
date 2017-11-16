import scala.util.Random

object StringSession {

  // Problem 1
  // Tests if a string is a palindrome.
  def isPal(str: String) = {
    var isP = true
    val s = str.trim().toLowerCase()
    for (i <- 0 until s.length / 2)
      if (s.charAt(i) != s.charAt(s.length - (i + 1)))
        isP = false
    isP
  }                                               //> isPal: (str: String)Boolean

	isPal("rotator")                          //> res0: Boolean = true
	isPal("cat")                              //> res1: Boolean = false
	isPal("3x$f!!f$x3")                       //> res2: Boolean = true


	// Problem 2
	// Tests if a string, with case punctuation and white space removed, is a palindrome.
	def isPal2(str: String) = {
			isPal(str.toLowerCase().replaceAll("[.,?! ]",""))
	}                                         //> isPal2: (str: String)Boolean
	 
	
	isPal2("A man, a plan, a canal, Panama!") //> res3: Boolean = true
	isPal2("This ., should ?! fail miserably...")
                                                  //> res4: Boolean = false
	
	
	// Problem 3
	// A palindrone maker.
	def mkPal(str: String) = {
		str + str.reverse
	}                                         //> mkPal: (str: String)String
	
	mkPal("mars")                             //> res5: String = marssram
	mkPal("3X@#")                             //> res6: String = 3X@##@X3
	
	
	// Problem 4
	// A random word generator. Average word length was found online to be 5.
	def mkWord(len: Integer = 5) = {
		var r = ""
		for (i <- 0 until len) r += (Random.nextInt(26) + 97).toChar
		r
	}                                         //> mkWord: (len: Integer)String
	
	mkWord()                                  //> res7: String = otuqe
	mkWord(20)                                //> res8: String = njxesayuwfklfttvmvkl
	
	
	// Problem 5
	// A random sentence generator.
	// Optimal sentence length is 3 - 12 words.
	// Word length is between 1 and 12 characters.
	def mkSentence(len: Integer = 0) = {
		var theLen = len
		if (len < 1) theLen = Random.nextInt(10) + 3
		var sen = (Random.nextInt(26)+65).toChar + mkWord(Random.nextInt(11)+1)
		for (i <- 0 until theLen-1) sen += " "+mkWord(Random.nextInt(12)+1)
		sen + "."
	}                                         //> mkSentence: (len: Integer)String

	mkSentence()                              //> res9: String = Gzzzn unppsjm xcqosulzfy loflzx d vte.
	mkSentence()                              //> res10: String = Rriisvq ccihbeiew lxzr lrd isdkrfxg htzmjr vcxfue.
	mkSentence(5)                             //> res11: String = Mha bxyk quzubydfwqc ukzkqazhud xxzp.
	
	// Q5.1 Using mkSentence how would you implement mkNovel? mkPoem? mkReply? mkPost?
	// A5.1 I would use the sentence length to my advantage. mkNovel would have longer
	//      sentences than say mkPoen or mkPost. For mkPoem, I would need to length
	//      of the sentence to be particular to satisfy the constraints of the poem.
	
}