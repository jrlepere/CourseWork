import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * A button to save events.
 * 
 * @author JLepere2 Version 1.1
 */
public class SaveEventButton extends JButton {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the save event button.
	 * @param eventTextArea the text area for the event.
	 * @param dateTextArea the text area for the date.
	 * @param startTimeTextArea the text area for the start time.
	 * @param endTimeTextArea the text are for the end time.
	 * @param calendar the calendar model.
	 * @param parentFrame the parent frame.
	 */
	public SaveEventButton(JTextField eventTextArea, JTextField dateTextArea, JTextField startTimeTextArea,
			JTextField endTimeTextArea, MyCalendar calendar, JFrame parentFrame) {

		this.setText("SAVE");

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					MyTime startTime = validTime(startTimeTextArea.getText());
					MyTime endTime = validTime(endTimeTextArea.getText());
					if (startTime.compareTo(endTime) > 0) {
						throw new InvalidTimeException();
					}

					int[] date = validDate(dateTextArea.getText());
					String event = validEvent(eventTextArea.getText());

					ArrayList<Event> eventsForDay = calendar.getEventsByDay(date[0], date[1], date[2]);
					if (eventsForDay != null) {
						for (Event oEvent : eventsForDay) {
							MyTime oStartTime = oEvent.getStartTime();
							MyTime oEndTime = oEvent.getEndTime();
							if (startTime.compareTo(oStartTime) > 0 && startTime.compareTo(oEndTime) < 0) {
								throw new EventOverlapException();
							} else if (endTime.compareTo(oStartTime) > 0 && endTime.compareTo(oEndTime) < 0) {
								throw new EventOverlapException();
							} else if (startTime.compareTo(oStartTime) <= 0 && endTime.compareTo(oEndTime) >= 0) {
								throw new EventOverlapException();
							}
						}
					}

					calendar.addEvent(date[0], date[1], date[2], event, startTime, endTime);
					JOptionPane.showMessageDialog(null, "Your Event Was Saved!");
					parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));

				} catch (InvalidTimeException invalidTime) {
					JOptionPane.showMessageDialog(null, "Invalid Time.");
				} catch (InvalidDateException invalidDate) {
					JOptionPane.showMessageDialog(null, "Invalid Date.");
				} catch (InvalidEventException invalidEvent) {
					JOptionPane.showMessageDialog(null, "Invalid Event.");
				} catch (EventOverlapException eventOverlap) {
					JOptionPane.showMessageDialog(null, "Confilicting Events.");
				}

			}
		});

	}

	/**
	 * Checks if the time is valid. If valid, returns a MyTime object
	 * representing the provided time.
	 * @return the time represented as a MyTime object.
	 * @throws InvalidTimeException an exception indicating that the time is invalid.
	 */
	private MyTime validTime(String theTime) throws InvalidTimeException {

		if (theTime.contains(":")) {

			String[] time = theTime.split(":");
			if (time.length == 2) {

				String hour = time[0];
				if (hour.length() != 2) {
					throw new InvalidTimeException();
				}
				String minutePlus = time[1];
				if (minutePlus.length() != 4) {
					throw new InvalidTimeException();
				}
				String amPm = minutePlus.substring(2);
				if (!amPm.toLowerCase().equals("am") && !amPm.toLowerCase().equals("pm")) {
					throw new InvalidTimeException();
				}
				String minute = minutePlus.substring(0, 2);

				try {

					int theHour = Integer.parseInt(hour);
					int theMinute = Integer.parseInt(minute);

					if (theHour < 1 || theMinute < 0 || theHour > 12 || theMinute > 59) {
						throw new InvalidTimeException();
					}

					if (amPm.equals("am")) {
						if (theHour == 12) {
							theHour = 0;
						}
					} else {
						if (theHour != 12) {
							theHour += 12;
						}
					}

					return new MyTime(theHour, theMinute);

				} catch (NumberFormatException e) {

					throw new InvalidTimeException();

				}

			} else {
				throw new InvalidTimeException();
			}

		} else {
			throw new InvalidTimeException();
		}

	}

	/**
	 * Checks if a provided date is in the correct format.
	 * @param date the date.
	 * @return an array representing the year, month, day of the date given
	 * @throws InvalidDateException an invalid date exception.
	 */
	private int[] validDate(String date) throws InvalidDateException {

		if (date.contains("/")) {

			String[] theDate = date.split("/");
			if (theDate.length != 3) {
				throw new InvalidDateException();
			}

			String month = theDate[0];
			String day = theDate[1];
			String year = theDate[2];

			try {

				int theMonth = Integer.parseInt(month);
				int theDay = Integer.parseInt(day);
				int theYear = Integer.parseInt(year);

				if (theMonth < 1 || theMonth > 12 || theDay < 1 || theYear < 0) {
					throw new InvalidDateException();
				}

				GregorianCalendar temp = new GregorianCalendar(theYear, theMonth, 1);
				int maxDays = temp.getMaximum(Calendar.DAY_OF_MONTH);
				if (theDay > maxDays) {
					throw new InvalidDateException();
				}

				int[] dateArray = new int[3];
				dateArray[0] = theYear;
				dateArray[1] = theMonth;
				dateArray[2] = theDay;

				return dateArray;

			} catch (NumberFormatException e) {

				throw new InvalidDateException();

			}

		} else {
			throw new InvalidDateException();
		}

	}

	/**
	 * Checks if the given event is valid.
	 * @param event the event.
	 * @return the event if the event is valid
	 * @throws InvalidEventException an invalid event exception.
	 */
	private String validEvent(String event) throws InvalidEventException {

		if (!event.isEmpty()) {
			return event;
		}

		throw new InvalidEventException();

	}

}
