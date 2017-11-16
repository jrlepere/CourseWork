import java.util.ArrayList;
import java.util.Collections;

/**
 * A comprehensive receipt of all user reservations.
 * 
 * @author Ziwei Wu
 * Version 1.1
 */
public class ComprehensiveReceipt implements ReceiptFormat {

	public String getReceipt(GuestAccount guestAccount, ArrayList<Reservation> reservations) {
		ArrayList<Reservation> theReservations = new ArrayList<>();
		for (Reservation r : guestAccount.getReservations()) {
			theReservations.add(r);
		}
		Collections.sort(theReservations);
		String receipt = "User Id: " + guestAccount.getId() + "\n";
		receipt += "Name: " + guestAccount.getLastName() + ", " + guestAccount.getFirstName() + "\n\n";
		int total = 0;
		for (Reservation r : theReservations) {
			HotelRoom room = r.getHotelRoom();
			receipt += room.getRoomType() + room.getRoomNumber() + " " + r.getTime() + ": $" + r.getReservationPrice()
					+ "\n";
			total += r.getReservationPrice();
		}
		receipt += "\nTotal Cost: $" + total;
		return receipt;
	}

}
