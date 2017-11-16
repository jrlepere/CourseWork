import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple receipt.
 * 
 * @author Ziwei Wu 
 * Version 1.1
 */
public class SimpleReceipt implements ReceiptFormat {

	public String getReceipt(GuestAccount guestAccount, ArrayList<Reservation> reservations) {
		String receipt = "User Id: " + guestAccount.getId() + "\n";
		receipt += "Name: " + guestAccount.getLastName() + ", " + guestAccount.getFirstName() + "\n\n";
		int total = 0;
		Collections.sort(reservations);
		for (Reservation r : reservations) {
			HotelRoom room = r.getHotelRoom();
			receipt += room.getRoomType() + room.getRoomNumber() + " " + r.getTime() + ": $" + r.getReservationPrice()
					+ "\n";
			total += r.getReservationPrice();
		}
		receipt += "\nTotal Cost: $" + total;
		return receipt;
	}

}
