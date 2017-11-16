
/**
 * A Manager for designing a date based on the strategy pattern.
 * @author JLepere2
 * Version 1.1
 */
public interface DateTextDesign {

	static String[] longDates = {"January", "February", "March", 
			"April", "May", "June", "July", "August", "September",
			"October", "November", "December"};
	
	/**
	 * Returns the date as a string in the given format.
	 * @param year the year.
	 * @param month the month.
	 * @param day the day.
	 * @return the date as a string.
	 */
	String getDate(int year, int month, int day);
	
}
