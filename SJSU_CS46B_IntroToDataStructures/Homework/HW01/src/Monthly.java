
public class Monthly extends Appointment
{
	private int day;
	
	public Monthly(int day, String description)
	{
		this.day = day;
		super.setDescription(description);
	}

	public boolean occursOn(int year, int month, int day) {
		if (this.day == day)
		{
			return true;
		}
		return false;
	}
	
	
}
