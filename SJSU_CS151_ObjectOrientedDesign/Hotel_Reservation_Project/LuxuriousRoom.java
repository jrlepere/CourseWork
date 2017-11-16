/**
 * A luxurious hotel room.
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class LuxuriousRoom implements HotelRoom {

	private static final long serialVersionUID = 126326L;
	public static final int PRICE = 200;
	public static final String identifier = "LUX";
	private int roomNumber;

	/**
	 * Creates a LuxuriosRoom.
	 * 
	 * @param theRoomNumber
	 *            the room number of the room.
	 */
	public LuxuriousRoom(int theRoomNumber) {
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
