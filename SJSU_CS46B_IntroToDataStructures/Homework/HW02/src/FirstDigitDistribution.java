
public class FirstDigitDistribution
{
	int[] counters;
	public static final double MAX_NUMBER_STAR = 40.0;
	
	public FirstDigitDistribution() 
	{
		counters = new int[10];
	}
	
	public void process(Sequence seq, int valuesToProcess) 
	{
		for (int i = 1; i <= valuesToProcess; i++) {
			int value = seq.next();
			int firstDigit = Math.abs(value);
			while(firstDigit > 9) {
				firstDigit /= 10;
			}
			counters[firstDigit]++;
		}
	}	
	
	public void display()
	{
		int largestNumber = counters[0];
		for (int i = 1; i < counters.length; i ++) {
			if (counters[i] > largestNumber) {
				largestNumber = counters[i];
			}
		}
		for (int i = 0; i < counters.length; i ++) {
			System.out.print((i) + ": ");
			double starRatio = largestNumber/MAX_NUMBER_STAR;
			for (int j = 0; j < (int)(counters[i]/(starRatio)); j ++) {
				System.out.printf("*");
			}
			System.out.println();
		}
	}
}
