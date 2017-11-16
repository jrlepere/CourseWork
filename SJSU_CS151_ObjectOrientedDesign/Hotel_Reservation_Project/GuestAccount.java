import java.io.Serializable;
import java.util.ArrayList;

/**
 * A Guest that can stay at the hotel.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class GuestAccount implements Serializable {

	private static final long serialVersionUID = 4683548378L;
	private static int guestAccountCount;

	private int id;
	private String firstName;
	private String lastName;
	private ArrayList<Reservation> reservations;

	/**
	 * Creates a new Hotel Guest
	 * 
	 * @param theFirstName
	 *            the guest's first name
	 * @param theLastName
	 *            the guest's last name
	 */
	public GuestAccount(String theFirstName, String theLastName) {

		guestAccountCount++;

		this.id = guestAccountCount;
		this.firstName = theFirstName;
		this.lastName = theLastName;
		this.reservations = new ArrayList<>();

	}

	/**
	 * Gets the first name of the guest.
	 * 
	 * @return the first name of the guest
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of the guest.
	 * 
	 * @return the last name of the guest
	 */
	public String getLastName() {
		return lastName;
	}

	public String toString() {
		return this.getClass().getName() + "[" + "id: " + ", firstName: " + firstName + ", lastName: " + lastName
				+ reservations.toString();
	}

	/**
	 * Adds a reservation to this guest's account.
	 * 
	 * @param theReservation
	 *            the reservation
	 */
	public void addReservation(Reservation theReservation) {
		reservations.add(theReservation);
	}

	/**
	 * Gets the id number for this account.
	 * 
	 * @return the id number
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the current number of GuestAccounts created. Use for setting
	 * GuestAccount ID numbers
	 * 
	 * @return the current number of guest accounts.
	 */
	public static int getCount() {
		return guestAccountCount;
	}

	/**
	 * Sets the number of accounts created. Used for post deserialization
	 * purposed.
	 * 
	 * @param accountCount
	 *            the account count
	 */
	public static void setGuestAccountCount(int accountCount) {
		guestAccountCount = accountCount;
	}

	/**
	 * Gets the reservations for this guest.
	 * 
	 * @return the reservations for this guest.
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Cancels the reservation.
	 * 
	 * @param r
	 *            the reservation.
	 */
	public void cancelReservation(Reservation r) {
		reservations.remove(r);
	}

}
