/**
 * An Invalid Date Exception.
 * @author JLepere2
 * Version 1.1
 */
public class InvalidDateException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * An InvalidDateException.
	 */
	public InvalidDateException() {
		super();
	}
	
	/**
	 * An InvalidDateException.
	 * @param exception the exception
	 */
	public InvalidDateException(String exception) {
		super(exception);
	}
	
}
