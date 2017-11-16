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

/**
 * An abstract class for the calendar grid component
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public abstract class CalendarGridComponent extends JComponent {

	private static final long serialVersionUID = 1825482L;
	private static String[] shortDays = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private static String[] longMonths = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	/**
	 * An inner class that is used for the guest view. The button actions are
	 * unique.
	 * 
	 * @author JLepere2 Version 1.1
	 */
	public static class Guest extends CalendarGridComponent {

		private static final long serialVersionUID = 1346L;
		private JLabel currentDayLabel;
		private GregorianCalendar liveTime;
		private int currentDay;
		private GregorianCalendar currentTimeCalendar;

		/**
		 * A CalendarGridComponent for the Guest View
		 * 
		 * @param theCurrentDayLabel
		 *            the current day label
		 */
		public Guest(final JLabel theCurrentDayLabel) {
			this.currentDayLabel = theCurrentDayLabel;
			currentTimeCalendar = new GregorianCalendar();
			liveTime = new GregorianCalendar();
			currentDay = liveTime.get(Calendar.DAY_OF_MONTH);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Component c : getComponents()) {
				remove(c);
			}

			GregorianCalendar temp = new GregorianCalendar(liveTime.get(Calendar.YEAR), liveTime.get(Calendar.MONTH),
					1);

			final int headerHeight = 40;

			int frameWidth = getWidth();
			int frameHeight = getHeight() - headerHeight;

			// -----HEADER
			final int numberOfDays = 7;
			double headerLabelWidth = frameWidth / numberOfDays;
			for (int i = 0; i < numberOfDays; i++) {
				JLabel headerLabel = new JLabel(shortDays[i]);
				headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
				headerLabel
						.setBounds(new Rectangle((int) headerLabelWidth * i, 0, (int) headerLabelWidth, headerHeight));
				add(headerLabel);
			}

			// ------ DAYS
			int maxDaysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
			int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK);

			int numberOfRows = (5 + maxDaysInMonth + firstDayOfWeek) / 7;
			int rowHeight = frameHeight / numberOfRows;
			int columnWidth = frameWidth / 7;
			int currentDayOfWeek = firstDayOfWeek - 1;
			int currentRow = 0;

			boolean onCurrentMonth = false;
			if (liveTime.get(Calendar.YEAR) == currentTimeCalendar.get(Calendar.YEAR)
					&& liveTime.get(Calendar.MONTH) == currentTimeCalendar.get(Calendar.MONTH)) {
				onCurrentMonth = true;
			}

			for (int i = 1; i <= maxDaysInMonth; i++) {

				JButton dayButton = new JButton("" + i);
				dayButton.setSize(columnWidth, rowHeight);
				dayButton.setLocation(currentDayOfWeek * columnWidth, headerHeight + currentRow * rowHeight);
				dayButton.setOpaque(true);
				dayButton.setFocusPainted(false);
				if (currentDay == i) {
					dayButton.setBorder(new LineBorder(Color.BLACK));
				}
				final int d = i;
				if (onCurrentMonth) {
					if (i < currentTimeCalendar.get(Calendar.DAY_OF_MONTH)) {
						dayButton.setForeground(Color.GRAY);
					} else {
						dayButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								currentDay = d;
								repaint();
								currentDayLabel.setText(getCurrentDayLong());
								currentDayLabel.repaint();
							}
						});
					}
				} else {
					dayButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							currentDay = d;
							repaint();
							currentDayLabel.setText(getCurrentDayLong());
							currentDayLabel.repaint();
						}
					});
				}
				add(dayButton);

				currentDayOfWeek++;
				if (currentDayOfWeek == 7) {
					currentDayOfWeek = 0;
					currentRow++;
				}
			}

		}

		/**
		 * Changes to the next or previous month.
		 * 
		 * @param nextMonth
		 *            true to change to next, false for previous
		 */
		public void changeMonth(boolean nextMonth) {
			if (nextMonth) {
				liveTime.add(GregorianCalendar.MONTH, 1);
				currentDay = 1;
				liveTime.set(Calendar.DAY_OF_MONTH, currentDay);
				this.repaint();
			} else {
				if (liveTime.get(Calendar.YEAR) > currentTimeCalendar.get(Calendar.YEAR)) {
					liveTime.add(GregorianCalendar.MONTH, -1);
					if (liveTime.get(Calendar.YEAR) == currentTimeCalendar.get(Calendar.YEAR)
							&& liveTime.get(Calendar.MONTH) == currentTimeCalendar.get(Calendar.MONTH)) {
						currentDay = currentTimeCalendar.get(Calendar.DAY_OF_MONTH);
					} else {
						currentDay = 1;
					}
					liveTime.set(Calendar.DAY_OF_MONTH, currentDay);
					this.repaint();
				} else if (liveTime.get(Calendar.MONTH) > currentTimeCalendar.get(Calendar.MONTH)) {
					liveTime.add(GregorianCalendar.MONTH, -1);
					if (liveTime.get(Calendar.YEAR) == currentTimeCalendar.get(Calendar.YEAR)
							&& liveTime.get(Calendar.MONTH) == currentTimeCalendar.get(Calendar.MONTH)) {
						currentDay = currentTimeCalendar.get(Calendar.DAY_OF_MONTH);
					} else {
						currentDay = 1;
					}
					liveTime.set(Calendar.DAY_OF_MONTH, currentDay);
					this.repaint();
				}
			}
		}

		/**
		 * Gets the current day of the grid in the format: January 1, 2017.
		 * 
		 * @return the current day of the grid
		 */
		public String getCurrentDayLong() {
			return longMonths[liveTime.get(GregorianCalendar.MONTH)] + " " + currentDay + ", "
					+ liveTime.get(Calendar.YEAR);
		}

		/**
		 * Gets the current day of the grid in the format: 01/01/2017
		 * 
		 * @return the current day of the grid
		 */
		public String getCurrentDayShort() {
			return (new MyDate(liveTime.get(Calendar.YEAR), liveTime.get(GregorianCalendar.MONTH) + 1, currentDay))
					.getDateString();
		}

	}

	/**
	 * A CalendarGridComponent for the Manager class
	 * 
	 * @author JLepere2 Version 1.1
	 */
	public static class Manager extends CalendarGridComponent {

		private static final long serialVersionUID = 123624L;
		private DateRangeReservationModel dateRangeModel;
		private JLabel currentDayLabel;
		private GregorianCalendar liveTime;
		private int currentDay;

		/**
		 * A calendar grid component for the manager view.
		 * 
		 * @param theDateRangeModel
		 *            the date range model.
		 * @param theCurrentDayLabel
		 *            the current day label.
		 */
		public Manager(final DateRangeReservationModel theDateRangeModel, JLabel theCurrentDayLabel) {
			this.currentDayLabel = theCurrentDayLabel;
			this.dateRangeModel = theDateRangeModel;
			liveTime = new GregorianCalendar();
			currentDay = liveTime.get(Calendar.DAY_OF_MONTH);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Component c : this.getComponents()) {
				this.remove(c);
			}

			GregorianCalendar temp = new GregorianCalendar(liveTime.get(Calendar.YEAR), liveTime.get(Calendar.MONTH),
					1);

			final int headerHeight = 40;

			int frameWidth = this.getWidth();
			int frameHeight = this.getHeight() - headerHeight;

			// -----HEADER
			final int numberOfDays = 7;
			double headerLabelWidth = frameWidth / numberOfDays;
			for (int i = 0; i < numberOfDays; i++) {
				JLabel headerLabel = new JLabel(shortDays[i]);
				headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
				headerLabel
						.setBounds(new Rectangle((int) headerLabelWidth * i, 0, (int) headerLabelWidth, headerHeight));
				this.add(headerLabel);
			}

			// ------ DAYS
			int maxDaysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
			int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK);

			int numberOfRows = (5 + maxDaysInMonth + firstDayOfWeek) / 7;
			int rowHeight = frameHeight / numberOfRows;
			int columnWidth = frameWidth / 7;
			int currentDayOfWeek = firstDayOfWeek - 1;
			int currentRow = 0;

			for (int i = 1; i <= maxDaysInMonth; i++) {

				JButton dayButton = new JButton("" + i);
				dayButton.setSize(columnWidth, rowHeight);
				dayButton.setLocation(currentDayOfWeek * columnWidth, headerHeight + currentRow * rowHeight);
				dayButton.setOpaque(true);
				dayButton.setFocusPainted(false);
				if (currentDay == i) {
					dayButton.setBorder(new LineBorder(Color.BLACK));
				}
				final int d = i;
				dayButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentDay = d;
						repaint();
						dateRangeModel.setDate(true, new MyDate(getCurrentDayShort()));
						dateRangeModel.setDate(false, new MyDate(getCurrentDayShort()));
						currentDayLabel.setText(getCurrentDayLong());
						currentDayLabel.repaint();
					}
				});
				this.add(dayButton);

				currentDayOfWeek++;
				if (currentDayOfWeek == 7) {
					currentDayOfWeek = 0;
					currentRow++;
				}
			}

		}

		/**
		 * Changes to the next or previous month.
		 * 
		 * @param nextMonth
		 *            true to change to next, false for previous
		 */
		public void changeMonth(boolean nextMonth) {
			if (nextMonth) {
				liveTime.add(GregorianCalendar.MONTH, 1);
			} else {
				liveTime.add(GregorianCalendar.MONTH, -1);
			}
			currentDay = 1;
			liveTime.set(Calendar.DAY_OF_MONTH, currentDay);
			this.repaint();
		}

		/**
		 * Changes to the next or previous year.
		 * 
		 * @param nextYear
		 *            true to change to next, false for previous
		 */
		public void changeYear(boolean nextYear) {
			if (nextYear) {
				liveTime.add(GregorianCalendar.YEAR, 1);
			} else {
				liveTime.add(GregorianCalendar.YEAR, -1);
			}
			currentDay = 1;
			liveTime.set(Calendar.DAY_OF_MONTH, currentDay);
			this.repaint();
		}

		/**
		 * Gets the current day of the grid in the format: January 1, 2017.
		 * 
		 * @return the current day of the grid
		 */
		public String getCurrentDayLong() {
			return longMonths[liveTime.get(GregorianCalendar.MONTH)] + " " + currentDay + ", "
					+ liveTime.get(Calendar.YEAR);
		}

		/**
		 * Gets the current day of the grid in the format: 01/01/2017
		 * 
		 * @return the current day of the grid
		 */
		public String getCurrentDayShort() {
			return (new MyDate(liveTime.get(Calendar.YEAR), liveTime.get(GregorianCalendar.MONTH) + 1, currentDay))
					.getDateString();
		}

	}

}
