/**
 * An exception for an illegal product.
 * @author JLepere2
 * Version 1.1 Initial Implementation.
 */
public class IllegalProductException extends Exception {

	private static final long serialVersionUID = 23624361L;
	private String product;
	
	/**
	 * Creates an IllegalProductException.
	 * @param theProduct The illegal product.
	 */
	public IllegalProductException(String theProduct) {
		super();
		this.product = theProduct;
	}
	
	/**
	 * Gets the illegal product.
	 * @return The illegal product.
	 */
	public String getMessage() {
		return "Illegal Product: " + product;
	}
	
}
