/**
 * A long date design format.
 * @author JLepere2
 * Version 1.1
 */
public class LongDateDesign implements DateTextDesign {

	public String getDate(int year, int month, int day) {
		return longDates[month] + " " + day + ", " + year;
	}

}
