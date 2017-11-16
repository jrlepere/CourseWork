import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A model for the date range for the main guest view
 * 
 * @author Jake Lepere
 * Version 1.1 complicated. 
 * Version 1.2 simplified.
 */
public class DateRangeReservationModel {

	private HotelManager hotelManager;
	private MyDate dateFrom, dateTo;
	private ArrayList<ChangeListener> listeners;
	private boolean economicSelected;

	/**
	 * Creates a date range reservation model.
	 * 
	 * @param theHotelManager
	 *            the hotel manager.
	 */
	public DateRangeReservationModel(HotelManager theHotelManager) {
		this.hotelManager = theHotelManager;
		this.listeners = new ArrayList<>();
		this.dateFrom = new MyDate(new Date());
		this.dateTo = new MyDate(new Date());
		this.economicSelected = true;
	}

	/**
	 * Sets the date from of the date to.
	 * 
	 * @param dateFrom
	 *            true to set the date from, false to set the date to
	 * @param theDate
	 *            the date to set
	 */
	public void setDate(boolean dateFrom, MyDate theDate) {
		if (dateFrom) {
			this.dateFrom = theDate;
		} else {
			this.dateTo = theDate;
		}

		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}

	/**
	 * Sets the room selected.
	 * 
	 * @param economicSelected
	 *            true for economic, false for luxurious.
	 * @param updateListeners
	 *            true to update the listeners, false otherwise.
	 */
	public void setRoomSelected(boolean economicSelected, boolean updateListeners) {
		this.economicSelected = economicSelected;

		if (updateListeners) {
			ChangeEvent e = new ChangeEvent(this);
			for (ChangeListener l : listeners) {
				l.stateChanged(e);
			}
		}
	}

	/**
	 * Gets a header for the current date range of the model.
	 * 
	 * @return a header for the current date range of the model.
	 */
	public String getDateRangeHeader() {
		String header = dateFrom.getDateString() + " - " + dateTo.getDateString() + ":\n";
		if (economicSelected) {
			header = "Economic Rooms From " + header;
		} else {
			header = "Luxurious Rooms From " + header;
		}
		return header;
	}

	/**
	 * Gets the available rooms for the model.
	 * 
	 * @param resQueue
	 *            the queue of reservations at the guest view.
	 * @return the available rooms for the model.
	 */
	public ArrayList<HotelRoom> getAvailableRooms(ArrayList<Reservation> resQueue) {
		ArrayList<HotelRoom> roomsAvailable = hotelManager.getAvailableRooms(resQueue, dateFrom, dateTo);
		ArrayList<HotelRoom> roomsToReturn = new ArrayList<>();
		if (economicSelected) {
			for (HotelRoom r : roomsAvailable) {
				if (r.getRoomType().equals(EconomicRoom.identifier)) {
					roomsToReturn.add(r);
				}
			}
		} else {
			for (HotelRoom r : roomsAvailable) {
				if (r.getRoomType().equals(LuxuriousRoom.identifier)) {
					roomsToReturn.add(r);
				}
			}
		}
		return roomsToReturn;
	}

	/**
	 * Adds a change listener to the model.
	 * 
	 * @param listener
	 *            the listener.
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Gets the from date.
	 * 
	 * @return the from date.
	 */
	public MyDate getDateFrom() {
		return dateFrom;
	}

	/**
	 * Gets the to date.
	 * 
	 * @return the to date.
	 */
	public MyDate getDateTo() {
		return dateTo;
	}

}
