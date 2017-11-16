
public class Daily extends Appointment
{
	public Daily(String description)
	{
		super.setDescription(description);
	}
	
	public boolean occursOn(int year, int month, int day)
	{
		return true;
	}
	
}
