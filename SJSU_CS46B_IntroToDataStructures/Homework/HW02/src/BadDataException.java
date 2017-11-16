
public class BadDataException extends NumberFormatException
{
	public BadDataException() {}
	public BadDataException(String error)
	{
		super(error);
		System.out.println(error);
	}
}
