import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Component for the calendar view.
 * 
 * @author JLepere2 Version 1.1
 */
public class CalendarViewComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private static Color[] EVENT_COLORS = { Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE,
			Color.PINK };
	private static final int HEIGHT_MULTIPLE = 4;
	private MyCalendar calendar;

	/**
	 * Creates a CalendarViewComponent.
	 * @param calendar the calendar model.
	 * @param frameWidth the frame width
	 * @param frameHeight the frame height
	 */
	public CalendarViewComponent(MyCalendar calendar, int frameWidth, int frameHeight) {
		this.calendar = calendar;
		stateChangedAction(frameWidth, frameHeight);
		this.setPreferredSize(new Dimension(frameWidth, frameHeight * HEIGHT_MULTIPLE));
		calendar.addListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				stateChangedAction(frameWidth, frameHeight);
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int width = this.getWidth();
		int height = this.getHeight();

		for (Component c : this.getComponents()) {
			this.remove(c);
		}

		double hourHeight = height / 24.0;
		int timeLabelWidth = 80;
		int timeLabelHeight = 20;

		for (int i = 0; i < 24; i++) {
			double theHourHeight = i * hourHeight;
			g2.setStroke(new BasicStroke(2));
			g2.draw(new Line2D.Double(0, theHourHeight, width, theHourHeight));
			g2.setStroke(new BasicStroke(1));
			for (int j = 1; j < 4; j++) {
				int offset = (int) ((j / 4.0) * hourHeight);
				g2.draw(new Line2D.Double(0, theHourHeight + offset, width, theHourHeight + offset));
			}
			JLabel timeLabel = new JLabel();
			String timeLabelText = "";
			String amPm = "";
			if (i == 0) {
				timeLabelText = "12:00";
			} else if (i < 10) {
				timeLabelText = "0" + i + ":00";
			} else if (i < 13) {
				timeLabelText = i + ":00";
			} else {
				timeLabelText = i - 12 + ":00";
			}
			if (i < 12) {
				amPm = "am";
			} else {
				amPm = "pm";
			}
			timeLabel.setText(timeLabelText + amPm);
			timeLabel.setBounds(0, (int) theHourHeight, timeLabelWidth, timeLabelHeight);
			this.add(timeLabel);
		}

		if (calendar.getEventsForCurrentDay() != null) {
			int i = 0;
			for (Event e : calendar.getEventsForCurrentDay()) {
				e.draw(this, EVENT_COLORS[i], width, height);
				i++;
				if (i >= EVENT_COLORS.length) {
					i = 0;
				}
			}
		}

	}
	
	/**
	 * Action for state changed event.
	 * @param frameWidth the frame width
	 * @param frameHeight the frame height
	 */
	private void stateChangedAction(int frameWidth, int frameHeight) {
		for (Component c : this.getComponents()) {
			this.remove(c);
		}
		ArrayList<Event> events = calendar.getEventsForCurrentDay();
		this.scrollRectToVisible(new Rectangle(0, 0, 0, 0));
		if (events != null) {
			Collections.sort(events);
			int firstEventStartTime = events.get(0).getStartTime().getSeconds();
			int maxSeconds = 23 * 3600 + 59 * 60;
			this.scrollRectToVisible(new Rectangle(0, (int) (this.getHeight() * ((double) firstEventStartTime) / maxSeconds), frameWidth, frameHeight));
		}
		repaint();
	}

}
