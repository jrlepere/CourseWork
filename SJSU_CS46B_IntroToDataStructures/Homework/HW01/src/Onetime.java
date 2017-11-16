
public class Onetime extends Appointment
{
	private int year, month, day;
	public Onetime(int year, int month, int day, String description) 
	{
		this.year = year;
		this.day = day;
		this.month = month;
		super.setDescription(description);
	}
	
	public boolean occursOn(int year, int month, int day) {
		if (this.year == year && this.month == month && this.day == day)
		{
			return true;
		}
		return false;
	}
	
}
