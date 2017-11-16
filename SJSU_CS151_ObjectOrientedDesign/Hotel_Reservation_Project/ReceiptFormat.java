import java.util.ArrayList;

/**
 * A receipt layout for post reservation confirmations.
 * 
 * @author Ziwei Wu 
 * Version 1.1
 */
public interface ReceiptFormat {

	/**
	 * Get the receipt.
	 * 
	 * @param guestAccount
	 *            the guest account.
	 * @param reservations
	 *            the current reservations at checkout.
	 * @return the receipt.
	 */
	public String getReceipt(GuestAccount guestAccount, ArrayList<Reservation> reservations);

}
