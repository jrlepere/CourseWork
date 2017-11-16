import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A grid of buttons representing a calendar for a month;
 * @author JLepere2
 * Version 1.1
 */
public class CalendarGrid extends JComponent {
	
	private static final long serialVersionUID = 1L;
	private int currentDay, currentMonth, currentYear;
	private MyCalendar calendar;
	private String[] shortDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	
	/**
	 * Creates a calendar grid.
	 * @param myCalendar the calendar model.
	 */
	public CalendarGrid(MyCalendar myCalendar) {
		
		this.calendar = myCalendar;
		this.currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		this.currentMonth = calendar.get(Calendar.MONDAY);
		this.currentYear = calendar.get(Calendar.YEAR);
		
		calendar.addListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				currentDay = calendar.get(Calendar.DAY_OF_MONTH);
				currentMonth = calendar.get(Calendar.MONTH);
				currentYear = calendar.get(Calendar.YEAR);
				repaint();
			}
		});
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Component c : this.getComponents()) {
			this.remove(c);
		}
		
		final int headerHeight = 40;
		
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight() - headerHeight;
		
		//-----HEADER
		final int numberOfDays = 7;
		double headerLabelWidth = frameWidth / numberOfDays;
		for (int i = 0; i < numberOfDays; i ++) {
			JLabel headerLabel = new JLabel(shortDays[i]);
			headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			headerLabel.setBounds(new Rectangle((int)headerLabelWidth*i, 0, (int)headerLabelWidth, headerHeight));
			this.add(headerLabel);
		}
		
		//------ DAYS
		GregorianCalendar temp = new GregorianCalendar(currentYear, currentMonth, 1, 0, 0);
		int maxDaysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK);
		
		int numberOfRows = (5 + maxDaysInMonth + firstDayOfWeek) / 7;
		int rowHeight = frameHeight / numberOfRows;
		int columnWidth = frameWidth / 7;
		int currentDayOfWeek = firstDayOfWeek-1;
		int currentRow = 0;
		
		for (int i = 1; i <= maxDaysInMonth; i ++) {
			
			JButton dayButton = new JButton(""+i);
			dayButton.setSize(columnWidth, rowHeight);
			dayButton.setLocation(currentDayOfWeek*columnWidth, headerHeight + currentRow*rowHeight);
			dayButton.setOpaque(true);
			dayButton.setFocusPainted(false);
			if (currentDay == i) {
				dayButton.setBorder(new LineBorder(Color.BLACK));
			}
			int day = i;
			dayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calendar.newDayOfMonth(day);
				}
			});
			this.add(dayButton);
			
			currentDayOfWeek ++;
			if (currentDayOfWeek == 7) {
				currentDayOfWeek = 0;
				currentRow ++;
			}
		}
		
	}
	
}
