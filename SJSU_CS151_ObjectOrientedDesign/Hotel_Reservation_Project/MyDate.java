import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * A date for the hotel reservations
 * 
 * @author Jake Lepere 
 * Version 1.1 Too many static methods 
 * Version 1.2 Removes static methods
 */
public class MyDate implements Comparable<MyDate>, Serializable {

	private static final long serialVersionUID = 15325699L;
	private int year, month, day;

	/**
	 * Creates a MyDate object.
	 * 
	 * @param theYear
	 *            the year.
	 * @param theMonth
	 *            the month.
	 * @param theDay
	 *            the day.
	 */
	public MyDate(int theYear, int theMonth, int theDay) {
		this.year = theYear;
		this.month = theMonth;
		this.day = theDay;
	}

	/**
	 * Creates a MyDate object.
	 * 
	 * @param date
	 *            the date.
	 */
	public MyDate(Date date) {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTime(date);
		this.year = temp.get(Calendar.YEAR);
		this.month = temp.get(Calendar.MONTH) + 1;
		this.day = temp.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Creates a MyDate Object.
	 * 
	 * @param date
	 *            a string in the format "mm/dd/yyy"
	 */
	public MyDate(String date) {
		String[] x = date.split("/");
		this.year = Integer.parseInt(x[2]);
		this.month = Integer.parseInt(x[0]);
		this.day = Integer.parseInt(x[1]);
	}

	public int compareTo(MyDate otherDate) {
		if (year - otherDate.year != 0) {
			return year - otherDate.year;
		} else if (month - otherDate.month != 0) {
			return month - otherDate.month;
		}
		return day - otherDate.day;
	}

	/**
	 * Gets the date as a string.
	 * 
	 * @return the date as a string.
	 */
	public String getDateString() {
		String theMonth = "";
		if (month < 10) {
			theMonth += "0";
		}
		theMonth += month;
		String theDay = "";
		if (day < 10) {
			theDay += "0";
		}
		theDay += day;
		return theMonth + "/" + theDay + "/" + year;
	}

	/**
	 * Gets the total days from the other day.
	 * 
	 * @param otherDate
	 *            the other.
	 * @return the total days.
	 */
	public long totalDays(MyDate otherDate) {
		GregorianCalendar t1 = new GregorianCalendar(year, month, day);
		GregorianCalendar t2 = new GregorianCalendar(otherDate.year, otherDate.month, otherDate.day);
		return 1 + TimeUnit.DAYS.convert(Math.abs(t1.getTime().getTime() - t2.getTime().getTime()),
				TimeUnit.MILLISECONDS);
	}

}
