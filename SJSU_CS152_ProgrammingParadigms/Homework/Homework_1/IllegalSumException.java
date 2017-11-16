/**
 * An exception for an illegal sum.
 * @author JLepere2
 * Version 1.1 Initial Implementation.
 */
public class IllegalSumException extends Exception {

	private static final long serialVersionUID = 23624361L;
	private String sum;
	
	/**
	 * Creates an IllegalSumException.
	 * @param theSum The illegal sum.
	 */
	public IllegalSumException(String theSum) {
		super();
		this.sum = theSum;
	}
	
	/**
	 * Gets the illegal sum.
	 * @return The illegal sum.
	 */
	public String getMessage() {
		return "Illegal Sum: " + sum;
	}
	
}
