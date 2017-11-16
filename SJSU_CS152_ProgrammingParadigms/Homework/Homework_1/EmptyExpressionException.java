/**
 * An exception for an empty expression.
 * @author JLepere2
 * Version 1.1 Initial Implementation
 */
public class EmptyExpressionException extends Exception {

	private static final long serialVersionUID = 1136L;

	public EmptyExpressionException() {
		super();
	}
	
	public String getMessage() {
		return "Empty Expression";
	}
	
}
