
public class Exercise2 {

	public static void main(String[] args)
	{
		
		Object obj = new Object();
		Object obj2 = new Object();
		String str = new String("Hello");
		String s = "Hello";
		Integer n1 = new Integer(6);
		Integer n2 = new Integer(6);
		Double d = new Double(6);
		Boolean b1 = new Boolean(true);
		Boolean b2 = new Boolean(true);
		Boolean b3 = new Boolean(false);
		
		System.out.println(Exercise2.isEqualTo(obj, str));
		System.out.println(Exercise2.isEqualTo(str, n1));
		System.out.println(Exercise2.isEqualTo(n1, n2));
		System.out.println(Exercise2.isEqualTo(n1, d));
		System.out.println(Exercise2.isEqualTo(str, s));
		System.out.println(Exercise2.isEqualTo(obj, obj2));
		
		System.out.println(Exercise2.isEqualTo(b1, b2));
		System.out.println(Exercise2.isEqualTo(b1, b3));
		
	}
	
	private static <T> boolean isEqualTo(T t1, T t2) {
		if (t1.equals(t2)) {
			return true;
		}
		return false;		
	}
	
}
