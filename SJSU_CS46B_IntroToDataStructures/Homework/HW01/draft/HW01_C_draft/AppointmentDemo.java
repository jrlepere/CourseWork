import java.util.Scanner;

/**
   Demonstration of the appointment classes
*/
public class AppointmentDemo
{
   public static void main(String[] args)
   {
      Appointment[] appointments = new Appointment[2];
      appointments[0] = new Daily("Brush your teeth.");
      appointments[1] = new Daily("Write some code.");

      Scanner in = new Scanner(System.in);
      int year = in.nextInt();
      int month = in.nextInt();
      int day = in.nextInt();
      for (Appointment a : appointments)
      {
         if (a.occursOn(year, month, day))
         {
            System.out.println(a);
         }
      }
   }
}