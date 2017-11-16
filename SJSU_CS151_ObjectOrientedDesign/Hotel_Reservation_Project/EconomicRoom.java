/**
 * An economic hotel room.
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class EconomicRoom implements HotelRoom {

	public static final int PRICE = 80;
	private static final long serialVersionUID = 1253632L;
	public static String identifier = "ECON";
	private int roomNumber;

	/**
	 * Creates an Economic Room.
	 * 
	 * @param theRoomNumber
	 *            the room number.
	 */
	public EconomicRoom(int theRoomNumber) {
		this.roomNumber = theRoomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getPrice() {
		return PRICE;
	}

	public String getRoomType() {
		return identifier;
	}

}
