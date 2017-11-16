
public class Prime {

	public static boolean isPrime(int N) {
		if (N % 2 != 0 && N != 2) {
			for (int i = 3; i <= Math.sqrt(N); i += 2) {
				if (N % i == 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println("3 is prime: " + Prime.isPrime(3));
		System.out.println("18 is prime: " + Prime.isPrime(18));
		System.out.println("49 is prime: " + Prime.isPrime(49));
		System.out.println("11 is prime: " + Prime.isPrime(11));
		System.out.println("13153 is prime: " + Prime.isPrime(13153));
		System.out.println("15485867 is prime: " + Prime.isPrime(15485867));
	}
}
