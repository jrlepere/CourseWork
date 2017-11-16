/**
 * An exception if the time is not in the correct format
 * @author JLepere2
 * Version 1.1
 */
public class InvalidTimeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * An InvalidDateException.
	 */
	public InvalidTimeException() {
		super();
	}

	/**
	 * An InvalidDateException.
	 * @param exception the exception
	 */
	public InvalidTimeException(String exception) {
		super(exception);
	}
	
}
