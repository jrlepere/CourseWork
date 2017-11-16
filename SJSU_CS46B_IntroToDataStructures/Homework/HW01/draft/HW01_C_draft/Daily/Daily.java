/**
 * Subclass of the class Appointment
 */
public class Daily extends Appointment
{
    /**
     * Creates a Daily object with given parameters
     * @param description the description of the appointment
     */
    public Daily(String description)
    {
        setDescription(description);
    }
    
    /**
     * checks whether the appointment occurs daily
     * @param year the year of the appointment
     * @param month the month of the appointment
     * @param day the day of the appointment
     * @return whether or not the appointment occurs daily (boolean)
     */
    public boolean occursOn(int year, int month, int day)
    {
        return true;
    }
}