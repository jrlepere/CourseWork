import java.io.Serializable;

/**
 * A reservation for the hotel.
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class Reservation implements Serializable, Comparable<Reservation> {

	private static final long serialVersionUID = 73737828466L;
	private GuestAccount guest;
	private HotelRoom room;
	private MyDate checkInDate, checkOutDate;

	/**
	 * Creates a new Reservation.
	 * 
	 * @param theGuest
	 *            the guest
	 * @param theRoom
	 *            the hotel room
	 * @param theCheckInDate
	 *            the check in date
	 * @param theCheckOutDate
	 *            the check out date
	 */
	public Reservation(GuestAccount theGuest, HotelRoom theRoom, MyDate theCheckInDate, MyDate theCheckOutDate) {
		this.guest = theGuest;
		this.room = theRoom;
		this.checkInDate = theCheckInDate;
		this.checkOutDate = theCheckOutDate;
	}

	public int compareTo(Reservation otherReservation) {
		if (checkInDate.compareTo(otherReservation.checkInDate) == 0) {
			return checkOutDate.compareTo(otherReservation.checkOutDate);
		}
		return checkInDate.compareTo(otherReservation.checkInDate);
	}

	/**
	 * Gets the check in date for the reservation.
	 * 
	 * @return the check in date
	 */
	public MyDate getCheckInDate() {
		return checkInDate;
	}

	/**
	 * Gets the check out date for the reservation.
	 * 
	 * @return the check out date
	 */
	public MyDate getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Gets the hotel room for the reservation.
	 * 
	 * @return the hotel room
	 */
	public HotelRoom getHotelRoom() {
		return room;
	}

	/**
	 * Gets the time range of this reservation
	 * 
	 * @return the time range of this reservation
	 */
	public String getTime() {
		return checkInDate.getDateString() + " - " + checkOutDate.getDateString();
	}

	/**
	 * Gets the id of the guest for this reservation
	 * 
	 * @return the id of the guest
	 */
	public String getGuestId() {
		return guest.getId() + "";
	}

	/**
	 * Gets the name of the guest for this reservation
	 * 
	 * @return the name of the guest
	 */
	public String getGuestName() {
		return guest.getLastName() + ", " + guest.getFirstName();
	}

	/**
	 * Gets the total price of the reservation.
	 * 
	 * @return the total price of the reservation.
	 */
	public long getReservationPrice() {
		long days = checkInDate.totalDays(checkOutDate);
		return days * room.getPrice();
	}

}
