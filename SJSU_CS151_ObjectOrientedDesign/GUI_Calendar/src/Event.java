import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * An Event Object with start time, end time, and the event.
 * @author JLepere2
 * Version 1.1
 */
public class Event implements Comparable<Event> {

	private String event;
	private MyTime startTime;
	private MyTime endTime;
	
	/**
	 * Creates an Event Object.
	 * @param event the event
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 */
	public Event(String event, MyTime startTime, MyTime endTime) {
		this.event = event;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Gets the event.
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	
	public int compareTo(Event o) {
		return startTime.compareTo(o.startTime);
	}
	
	/**
	 * Gets the start time of the event
	 * @return the start time of the event
	 */
	public MyTime getStartTime() {
		return startTime;
	}
	
	/**
	 * Checks if there is an end time for this event
	 * @return true if there is an end time for this event, false otherwise
	 */
	public boolean hasEndTime() {
		return endTime != null;
	}
	
	/**
	 * Gets the end time of the event
	 * @return the end time of the event
	 */
	public MyTime getEndTime() {
		return endTime;
	}
	
	/**
	 * Gets a string representing this event.
	 * @return a string representing this event
	 */
	public String getEventString() {
		if (endTime != null) {
			return event + " " + startTime.toString() + " - " + endTime.toString();
		} else {
			return event + " " + startTime.toString();
		}
	}
	
	/**
	 * Draws the event on the calendar view.
	 * @param parentComponent the parent component to draw the label
	 * @param color the color of the event
	 * @param frameWidth the width of the event
	 * @param frameHeight the height of the event
	 */
	public void draw(JComponent parentComponent, Color color, int frameWidth, int frameHeight) {
		
		int startTimeSeconds = startTime.getSeconds();
		int endTimeSeconds = endTime.getSeconds();
		
		int maxSeconds = 23*3600 + 59*60;
		
		double y = frameHeight*((double) startTimeSeconds)/maxSeconds;
		double height = frameHeight*((double) endTimeSeconds - startTimeSeconds)/maxSeconds;
		
		JLabel eventLabel = new JLabel("<html>" + event + "<BR>" + startTime.getTime() + " - " + endTime.getTime() + "</html>");
		
		eventLabel.setBounds(new Rectangle(0, (int) y, frameWidth, (int) height));
		eventLabel.setBackground(color);
		eventLabel.setOpaque(true);
		eventLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eventLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		parentComponent.add(eventLabel);
		
	}
	
}
