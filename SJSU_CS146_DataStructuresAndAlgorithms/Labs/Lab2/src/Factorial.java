
public class Factorial {

	public static int factorial(int n) {
		if (n < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return getFact(n);
	}
	
	private static int getFact(int n) {		
		if (n == 1) {
			return n;
		} else {
			return n * getFact(n-1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Factorial.factorial(30));
	}
}
