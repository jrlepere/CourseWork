import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Calendar containing events.
 * @author JLepere2
 * Version 1
 */
public class MyCalendar extends GregorianCalendar{

	private static final long serialVersionUID = 1L;
	private CalendarStructure eventStructure;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Creates a Calendar with no events
	 */
	public MyCalendar() {
		eventStructure = new CalendarStructure();
		listeners = new ArrayList<>();
	}
	
	/**
	 * Adds the event to the data structure
	 * @param year the year of the event
	 * @param month the month of the event
	 * @param day the day of the event
	 * @param event the event itself
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 */
	public void addEvent(int year, int month, int day, String event, MyTime startTime, MyTime endTime) {
		eventStructure.addEvent(year, month, day, event, startTime, endTime);
		updateListeners();
	}
	
	/**
	 * Gets the events from a given day in the form of an array of strings representing the event
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return an list for the given day
	 */
	public ArrayList<Event> getEventsByDay(int year, int month, int day) {
		return eventStructure.getEventsByDay(year, month, day);
	}
	
	/**
	 * Gets the events for the current day.
	 * @return the events for the current day
	 */
	public ArrayList<Event> getEventsForCurrentDay() {
		return eventStructure.getEventsByDay(this.get(YEAR), this.get(MONTH)+1, this.get(DAY_OF_MONTH));
	}
	
	/**
	 * Add a listener to the model.
	 * @param listener the listener
	 */
	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Moves the calendar forward or backward by one day.
	 * @param forward true to move forward, false to move backward.
	 */
	public void nextDay(boolean forward) {
		if (forward) {
			// Move forward
			this.add(Calendar.DAY_OF_MONTH, 1);
		} else {
			// Move backward
			this.add(Calendar.DAY_OF_MONTH, -1);
		}
		updateListeners();
	}
	
	/**
	 * Sets the day of a month to a new day.
	 * @param day the new day of the month.
	 */
	public void newDayOfMonth(int day) {
		this.set(DAY_OF_MONTH, day);
		updateListeners();
	}
	
	/**
	 * Gets the events in the correct format to save data
	 * @return a string containing the events to save
	 */
	public String getEventsToSave() {
		return eventStructure.getEventsToSave();
	}
	
	/**
	 * Update the listeners.
	 */
	private void updateListeners() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}
}
