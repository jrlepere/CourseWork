/**
   A class to keep track of an appointment.
*/
public class Appointment
{
   private String description;

   /**
      Constructs an appointment without a description.
   */
   public Appointment()
   {
      description = "";
   }

   /**
      Sets the description of this appointment.
      @param description the text description of the appointment
   */
   public void setDescription(String description)
   {
      this.description = description;
   }

   /**
      Determines if this appointment occurs on the given date.
      @param year the year
      @param month the month
      @param day the day
      @return true if the appointment occurs on the given date.
   */
   public boolean occursOn(int year, int month, int day)
   {
      return false;
   }

   /**
      Converts appointment to string description.
   */
   public String toString()
   {
      return description;
   }
}
