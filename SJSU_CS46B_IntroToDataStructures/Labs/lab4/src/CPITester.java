import java.io.IOException;

public class CPITester 
{
	public static void main(String[] args) throws IOException
	{
		CPIConverter conv = new CPIConverter();
		conv.read("http://sjsucs.bitbucket.org/cs46b/lab3/cpiai2.txt");
		double amount = 100000;
		double adjusted = conv.equivalentAmount(amount, 1961, 2003);
		System.out.printf("Adjusted amount: %10.0f\n", adjusted);
		
		CPIConverter conv2 = new CPIConverter();
		conv2.read("http://www.cs.sjsu.edu/foo.html");
		double amount1 = 100000;
		double adjusted1 = conv2.equivalentAmount(amount1, 1961, 2003);
		System.out.printf("Adjusted amount: %10.0f\n", adjusted1);
	}
}
