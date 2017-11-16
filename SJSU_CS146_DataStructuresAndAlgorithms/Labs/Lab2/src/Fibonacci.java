
public class Fibonacci {

	/**
	 * Recursively finds the Fibonacci number for a given n
	 * @param n the nth Fibonacci number
	 * @return the Fibonacci number 
	 */
	public static int fibRecursive(int n) {
		if (n < 0) {
			throw new IndexOutOfBoundsException();
		}
		return getFib(n);
	}
	
	private static int getFib(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else {
			return getFib(n-1) + getFib(n-2);
		}
	}
	
	/**
	 * Iteratively finds the Fibonacci number for a given n
	 * @param n the nth Fibonacci number
	 * @return the Fibonacci number
	 */
	public static int fibIteratively(int n) {
		if (n < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		int[] storedFib = new int[n+1];
		
		for (int i = 0; i <= n; i ++) {
			if (i == 0 || i == 1) {
				storedFib[i] = 1;
			} else {
				storedFib[i] = storedFib[i-1] + storedFib[i-2];
			}
		}
		
		return storedFib[n];
		
	}
	
	public static void main(String[] args) {
				
		for (int n = 0; n <= 40; n += 5) {
			
			long startTimeRec = System.nanoTime();
			int fibRec = Fibonacci.fibRecursive(n);
			long stopTimeRec = System.nanoTime();
			long timeFibRec = stopTimeRec - startTimeRec;
			
			long startTimeIter = System.nanoTime();
			int fibIter = Fibonacci.fibIteratively(n);
			long stopTimeIter = System.nanoTime();
			long timeFibIter = stopTimeIter - startTimeIter;
			
			if (fibRec != fibIter) {
				throw new Error("Incorrect Results");
			}
			
			float recMil = (float) (timeFibRec/(1000000.0));
			float iterMil = (float) (timeFibIter/(1000000.0));
			
			System.out.println("Fib(" + n + ")=" + fibRec);
			System.out.printf("   Recursive Time: %.6f ms%n", recMil);
			System.out.printf("   Iterative Time: %.6f ms%n", iterMil);
			System.out.println("");
		}
		
	}
}
