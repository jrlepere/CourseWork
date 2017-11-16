
public class RemoveTester 
{
	public static void main(String[] args)
	{
		String value = "$1,009,151";
		try {
			double amount = Integer.parseInt(value.replace("$", "").replace(",", ""));
			System.out.println(amount);
			System.out.printf("%2.2f", amount);
		} catch (NumberFormatException exception) {
			throw new BadDataException(value + " is not an integer.");
		}
	}
}
