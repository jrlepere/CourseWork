import java.io.Serializable;

/**
 * A room for a hotel.
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public interface HotelRoom extends Serializable {

	/**
	 * Gets the room number of the room.
	 * 
	 * @return the room number
	 */
	public int getRoomNumber();

	/**
	 * Gets the price of the room.
	 * 
	 * @return the price of the room
	 */
	public int getPrice();

	/**
	 * Gets the room type of the room.
	 * 
	 * @return the room type of the room
	 */
	public String getRoomType();

}
