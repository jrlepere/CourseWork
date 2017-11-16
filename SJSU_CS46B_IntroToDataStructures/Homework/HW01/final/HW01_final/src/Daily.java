/**
 * This program creates an appointment that happens daily
 * @author JLepere2
 *
 */
public class Daily extends Appointment
{
	/**
	 * Creates a daily appointment
	 * @param description a description of the appointment
	 */
	public Daily(String description)
	{
		super.setDescription(description);
	}
	
	/**
	 * Checks if the appointment occurs on a given date
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return whether or not the appointment occurs on the given day
	 */
	public boolean occursOn(int year, int month, int day)
	{
		return true;
	}
}