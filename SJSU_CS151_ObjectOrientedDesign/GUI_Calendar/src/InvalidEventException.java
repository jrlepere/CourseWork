/**
 * An exception for an illegal event.
 * @author JLepere2
 * Version 1.1
 */
public class InvalidEventException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * An InvalidDateException.
	 */
	public InvalidEventException() {
		super();
	}
	
	/**
	 * An InvalidDateException.
	 * @param exception the exception
	 */
	public InvalidEventException(String exception) {
		super(exception);
	}
	
}
