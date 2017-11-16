/**
 * An exception for overlapping events
 * @author JLepere2
 * Version 1.1
 */
public class EventOverlapException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * An EventOverlapException.
	 */
	public EventOverlapException() {
		super();
	}
	
	/**
	 * An EventOverlapException.
	 * @param exception the exception
	 */
	public EventOverlapException(String exception) {
		super(exception);
	}
	
}
