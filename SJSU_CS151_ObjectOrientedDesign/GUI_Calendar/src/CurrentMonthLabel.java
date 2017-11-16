import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A label for the current month of the calendar.
 * @author JLepere2
 * Version 1.1
 */
public class CurrentMonthLabel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DateTextDesign textDesign;
	private int currentDay, currentMonth, currentYear;
	
	/**
	 * A label with a description of the current month.
	 * @param myCalendar the calendar data model
	 */
	public CurrentMonthLabel(MyCalendar myCalendar) {
		currentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
		currentMonth = myCalendar.get(Calendar.MONTH);
		currentYear = myCalendar.get(Calendar.YEAR);
		
		this.textDesign = new LongDateDesign();
		
		final JLabel label = new JLabel(displayText());
		
		myCalendar.addListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				currentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
				currentMonth = myCalendar.get(Calendar.MONTH);
				currentYear = myCalendar.get(Calendar.YEAR);
				label.setText(displayText());
				label.repaint();
			}
		});
		
		this.add(label);
		
	}
	
	/**
	 * Gets the text to display for the label.
	 * @return the month lab in the specified text
	 */
	private String displayText() {
		return textDesign.getDate(currentYear, currentMonth, currentDay);
	}
	
}
