/**
 * An exception for illegal operands.
 * @author JLepere2
 * Version 1.1 Initial Implementation.
 */
public class IllegalOperandException extends Exception {

	private static final long serialVersionUID = 1153L;
	private String illegalOperands;
	
	/**
	 * Creates an IllegalOperandsException.
	 * @param theIllegalOperands The illegal operands.
	 */
	public IllegalOperandException(String theIllegalOperands) {
		super();
		this.illegalOperands = theIllegalOperands;
	}
	
	/**
	 * Gets the illegal operands for the exception.
	 * @return the illegal operands for the exception.
	 */
	public String getMessage() {
		return "Illegal Operand: " + illegalOperands;
	}
	
}
