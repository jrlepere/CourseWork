import java.io.Serializable;
import java.util.ArrayList;

/**
 * A manager for the rooms.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class ReservationCollection implements Serializable {

	private static final long serialVersionUID = 1000L;
	private static int LUXURIOUS_ROOMS = 10;
	private static int ECONOMIC_ROOMS = 10;
	private ArrayList<Reservation> reservations;

	/**
	 * Creates a new ReservationCollection.
	 */
	public ReservationCollection() {
		this.reservations = new ArrayList<>();
	}

	/**
	 * Adds a reservation to the collection.
	 * 
	 * @param theReservation
	 *            the reservation
	 */
	public void addReservation(Reservation theReservation) {
		reservations.add(theReservation);
	}

	public String toString() {
		return this.getClass().getName() + reservations.toString();
	}

	/**
	 * Gets the reservations if the date falls on or between the start time and
	 * end time of the reservation
	 * 
	 * @param date
	 *            the date
	 * @return the reservations that contain the date
	 */
	public ArrayList<Reservation> getReservationsByDate(MyDate date) {
		ArrayList<Reservation> theReservations = new ArrayList<>();
		for (Reservation r : reservations) {
			if (r.getCheckInDate().compareTo(date) <= 0 && r.getCheckOutDate().compareTo(date) >= 0) {
				theReservations.add(r);
			}
		}
		return theReservations;
	}

	/**
	 * Gets the available rooms.
	 * 
	 * @param resQueue
	 *            the current rooms in the queue for the guest.
	 * @param theInDate
	 *            the in date.
	 * @param theOutDate
	 *            the out date.
	 * @return a list of hotel rooms.
	 */
	public ArrayList<HotelRoom> getAvailableRooms(ArrayList<Reservation> resQueue, MyDate theInDate,
			MyDate theOutDate) {

		ArrayList<HotelRoom> conflictingHotelRooms = new ArrayList<>();

		for (Reservation res : reservations) {
			MyDate resInDate = res.getCheckInDate();
			MyDate resOutDate = res.getCheckOutDate();
			int inCompIn = theInDate.compareTo(resInDate);
			int outCompOut = theOutDate.compareTo(resOutDate);
			int inCompOut = theInDate.compareTo(resOutDate);
			int outCompIn = theOutDate.compareTo(resInDate);
			if ((inCompIn >= 0 && inCompOut <= 0) || (outCompOut <= 0 && outCompIn >= 0)
					|| (inCompIn <= 0 && outCompOut >= 0)) {
				// Conflicting Dates
				conflictingHotelRooms.add(res.getHotelRoom());
			}
		}
		for (Reservation r : resQueue) {
			if (doesConflict(theInDate, theOutDate, r.getCheckInDate(), r.getCheckOutDate())) {
				conflictingHotelRooms.add(r.getHotelRoom());
			}
		}

		ArrayList<HotelRoom> roomsAvailable = new ArrayList<>();
		for (int e = 1; e <= ECONOMIC_ROOMS; e++) {
			boolean available = true;
			for (HotelRoom r : conflictingHotelRooms) {
				if (r.getRoomType().equals(EconomicRoom.identifier) && r.getRoomNumber() == e) {
					available = false;
				}
			}
			if (available) {
				roomsAvailable.add(new EconomicRoom(e));
			}
		}
		for (int l = 1; l <= LUXURIOUS_ROOMS; l++) {
			boolean available = true;
			for (HotelRoom r : conflictingHotelRooms) {
				if (r.getRoomType().equals(LuxuriousRoom.identifier) && r.getRoomNumber() == l) {
					available = false;
				}
			}
			if (available) {
				roomsAvailable.add(new LuxuriousRoom(l));
			}
		}

		return roomsAvailable;

	}

	/**
	 * Checks if the reservation dates conflict.
	 * 
	 * @param s1
	 *            the start time of the first reservation.
	 * @param e1
	 *            the end time of the first reservation.
	 * @param s2
	 *            the start time of the second reservation.
	 * @param e2
	 *            the end time of the second reservation.
	 * @return true if the reservation times conflict, false otherwise.
	 */
	private static boolean doesConflict(MyDate s1, MyDate e1, MyDate s2, MyDate e2) {
		int inCompIn = s1.compareTo(s2);
		int outCompOut = e1.compareTo(e2);
		int inCompOut = s1.compareTo(e2);
		int outCompIn = e1.compareTo(s2);
		if ((inCompIn >= 0 && inCompOut <= 0) || (outCompOut <= 0 && outCompIn >= 0)
				|| (inCompIn <= 0 && outCompOut >= 0)) {
			// Conflicting Dates
			return true;
		}
		return false;
	}

	/**
	 * Removes the reservation.
	 * 
	 * @param r
	 *            the resevation.
	 */
	public void removeReservation(Reservation r) {
		reservations.remove(r);
	}

}
