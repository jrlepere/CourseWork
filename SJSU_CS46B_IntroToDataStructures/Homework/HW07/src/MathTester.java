
public class MathTester 
{
	public static void main(String[] args)
	{
		double pow = Math.log(16)/Math.log(2);
		System.out.println(pow);
		boolean powOf2 = pow % 1 == 0;
		System.out.println(powOf2);
		double pow2 = Math.log(18)/Math.log(2);
		System.out.println(pow2);
		System.out.println(pow2 % 1 == 0);
		System.out.println(10.0%1);
	}
}
