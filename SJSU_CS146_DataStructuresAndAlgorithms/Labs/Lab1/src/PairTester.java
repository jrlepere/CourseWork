
public class PairTester 
{
	
	public static void main(String[] args)
	{
		Pair pair = new Pair();
		System.out.println(pair.getFirst());
		pair.setSecond("Hello");
		System.out.println(pair.getSecond());
		pair.setSecond(0);
		System.out.println(pair.getSecond());
		
		Pair pair2 = new Pair("Hello",true);
		System.out.println(pair2.getFirst());
		System.out.println(pair2.getSecond());
		pair2.setFirst(false);
		pair2.setSecond("NO");
		System.out.println(pair2.getFirst());
		System.out.println(pair2.getSecond());
		pair2.setFirst("WHY?");
		pair2.setSecond(false);
		System.out.println(pair2.getFirst());
		System.out.println(pair2.getSecond());
		
	}
	
}
