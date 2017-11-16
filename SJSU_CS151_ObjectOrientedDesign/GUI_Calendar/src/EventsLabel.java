import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A label for the number of events for the current day selected
 * @author JLepere2
 * Version 1.1
 */
public class EventsLabel extends JLabel{

	private static final long serialVersionUID = 1L;
	private MyCalendar calendar;
	
	/**
	 * Creates an EventLabel.
	 * @param calendar the calendar model
	 */
	public EventsLabel(MyCalendar calendar) {
		this.calendar = calendar;
		resetText();
		calendar.addListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resetText();
				repaint();
			}
		});
	}
	
	/**
	 * Resets the events label text.
	 */
	private void resetText() {
		if (calendar.getEventsForCurrentDay() != null) {
			String eventText = "<html>";
			boolean first = true;
			for (Event e : calendar.getEventsForCurrentDay()) {
				if (first) {
					eventText += e.getStartTime().getTime() + " - " + e.getEndTime().getTime();
					first = false;
				} else {
					eventText += "<BR>"+e.getStartTime().getTime() + " - " + e.getEndTime().getTime();
				}
				eventText += " " + e.getEvent();
			}
			eventText += "</html>";
			this.setText(eventText);
		} else {
			this.setText("0 events.");
		}
	}
	
}
