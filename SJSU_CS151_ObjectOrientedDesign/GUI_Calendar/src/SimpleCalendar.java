import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Main class for the GUI Calendar Program.
 * @author JLepere2 Version 1.1
 */
public class SimpleCalendar {

	private static String FILENAME = "events.txt";

	/**
	 * Main method to run the program.
	 * @param args the command line arguments. Not Used.
	 */
	public static void main(String[] args) {

		MyCalendar calendar = new MyCalendar();
		
		File eventsFile = new File(FILENAME);
		try {
			if (eventsFile.exists()) {
				// Load the events
				Scanner eventsScanner = new Scanner(eventsFile);
				while (eventsScanner.hasNextLine()) {
					String event = eventsScanner.nextLine();
					// 10/09/2015: 21:30 - 23:40 Go to a movie
					String date = event.substring(0, 10).trim();
					String startingTime = event.substring(12, 17).trim();
					String remaining = event.substring(18).trim();
					String endingTime = "";
					String theEvent = "";
					endingTime = remaining.substring(2, 7).trim();
					theEvent = remaining.substring(8).trim();

					int[] startTime = splitTime(startingTime);
					int[] monDayYear = splitDate(date);

					int[] endTime = splitTime(endingTime);
					calendar.addEvent(monDayYear[2], monDayYear[0], monDayYear[1], theEvent, new MyTime(startTime[0], startTime[1]), new MyTime(endTime[0], endTime[1]));
				
				}
				eventsScanner.close();
			} else {
				// Create events txt file
				FileWriter writer = new FileWriter(eventsFile);
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		CalendarFrame frame = new CalendarFrame(calendar);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/**
	 * Splits a string containing the time in the following format: HH:MM
	 * @param t the time as a string
	 * @return an array where the 0 element is the hour and the 1 element is the minute
	 */
	private static int[] splitTime(String t) {
		String[] hoursMinutes = t.split(":");
		int[] result = new int[2];
		result[0] = Integer.parseInt(hoursMinutes[0]);
		result[1] = Integer.parseInt(hoursMinutes[1]);
		return result;
	}

	/**
	 * Splits a string containing the date in the following format: MM/DD/YYYY
	 * @param dthe day as a string
	 * @return an array where the 0 element is the month, 1 is the day, and 2 is the year
	 */
	private static int[] splitDate(String d) {
		String[] monDayYear = d.split("/");
		int[] result = new int[3];
		result[0] = Integer.parseInt(monDayYear[0]);
		result[1] = Integer.parseInt(monDayYear[1]);
		result[2] = Integer.parseInt(monDayYear[2]);
		return result;
	}

}
