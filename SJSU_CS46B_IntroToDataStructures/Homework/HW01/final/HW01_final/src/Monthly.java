/**
 * This program creates an appointment during a given month
 * @author JLepere2
 */
public class Monthly extends Appointment
{
	private int day;
	/**
	 * Creates a monthly appointment
	 * @param day the day of the month
	 * @param description the description of the appointment
	 */
	public Monthly(int day, String description)
	{
		super.setDescription(description);
		this.day = day;
	}
	
	/**
	 * Checks whether the appointment occurs on a given month
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return whether or not the appointment occurs on the given day
	 */
	public boolean occursOn(int year, int month, int day)
	{
		if (this.day == day) {
			return true;
		} else {
			return false;
		}
	}
}
