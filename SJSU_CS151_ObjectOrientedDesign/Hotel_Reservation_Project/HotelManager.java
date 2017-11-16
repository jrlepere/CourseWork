import java.io.Serializable;
import java.util.ArrayList;

/**
 * The manager for the hotel. Contains all user and room information.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class HotelManager implements Serializable {

	private static final long serialVersionUID = 468356262437L;
	private ReservationCollection reservationCollection;
	private ArrayList<GuestAccount> guestAccounts;

	/**
	 * Creates a new HotelManager.
	 */
	public HotelManager() {

		this.reservationCollection = new ReservationCollection();
		this.guestAccounts = new ArrayList<>();

	}

	public String toString() {
		return this.getClass().getName() + "[" + reservationCollection.toString() + guestAccounts.toString() + "]";
	}

	/**
	 * Adds a new guest account to the hotel.
	 * 
	 * @param theFirstName
	 *            the first name of the guest
	 * @param theLastName
	 *            the last name of the guest
	 * @return the guest account.
	 */
	public GuestAccount addGuestAccount(String theFirstName, String theLastName) {
		GuestAccount account = new GuestAccount(theFirstName, theLastName);
		guestAccounts.add(account);
		return account;
	}

	/**
	 * Gets if a user has the id number for signing in.
	 * 
	 * @param theIdNumber
	 *            the id number
	 * @return true if there is a user with the id number
	 */
	public GuestAccount validIdForLogIn(int theIdNumber) {

		for (GuestAccount account : guestAccounts) {
			if (account.getId() == theIdNumber) {
				return account;
			}
		}
		return null;

	}

	/**
	 * Gets the number of guest accounts.
	 * 
	 * @return the number of guest accounts
	 */
	public int getGuestAccountSize() {
		return guestAccounts.size();
	}

	/**
	 * Gets a list of available rooms for the date range.
	 * 
	 * @param resQueue
	 *            a list of rooms that are in the guests queue.
	 * @param theInDate
	 *            the in date.
	 * @param theOutDate
	 *            the out date.
	 * @return a list of hotel rooms.
	 */
	public ArrayList<HotelRoom> getAvailableRooms(ArrayList<Reservation> resQueue, MyDate theInDate,
			MyDate theOutDate) {
		return reservationCollection.getAvailableRooms(resQueue, theInDate, theOutDate);
	}

	/**
	 * Adds a reservation.
	 * 
	 * @param theReservation
	 *            the reservation.
	 */
	public void addReservation(Reservation theReservation) {
		reservationCollection.addReservation(theReservation);
	}

	/**
	 * Gets a list of reservations that the date falls on.
	 * 
	 * @param date
	 *            the date.
	 * @return a list of reserations.
	 */
	public ArrayList<Reservation> getReservationsByDate(MyDate date) {
		return reservationCollection.getReservationsByDate(date);
	}

	/**
	 * Removes the reservation.
	 * 
	 * @param r
	 *            the reservation.
	 */
	public void removeReservation(Reservation r) {
		reservationCollection.removeReservation(r);
	}

}
