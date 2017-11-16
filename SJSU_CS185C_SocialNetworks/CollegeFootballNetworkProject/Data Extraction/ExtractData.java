import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * This class extracts the college football scores from a user entered year in the command line
 * @author JLepere2
 * Version 1.1, 2015 fixed as the year.
 * Version 1.2, Pass any year between 1869 and 2015 to the command line.
 */
public class ExtractData {
	
	private static String URL = "http://www.sports-reference.com/cfb/years/*-schedule.html";
	private static int LATEST_YEAR = 2016;
	private static int EARLIEST_YEAR = 1869;
	
	private static String[] DATA_STATS = new String[]{"winner_school_name","winner_points",
			"loser_school_name","loser_points"};
	
	/**
	 * The main method for this program.
	 * @param args the command line arguments. First argument is the year of the desired scores the extract.
	 */
	public static void main(String[] args) {
		String year = args[0]; // The year passed from the command line
		try {
			int theYear = Integer.parseInt(year);
			if (theYear < EARLIEST_YEAR || theYear > LATEST_YEAR) {
				throw new NumberFormatException();
			}
			FileWriter writer = new FileWriter(new File(theYear + ".csv"));
			// CSV Header
			String csvHeader = DATA_STATS[0];
			for (int i = 1; i < DATA_STATS.length; i ++) {
				csvHeader += "," + DATA_STATS[i];
			}
			csvHeader += "\n";
			writer.write(csvHeader);
			// The URL
			String theURL = URL.replace("*", year);
			URL url = new URL(theURL);
			// Extract the Data
			Scanner urlScanner = new Scanner(url.openStream());
			while (urlScanner.hasNext()) {
				String line = urlScanner.nextLine();
				if (line.contains("scope=\"row\"")) {
					Scanner lineScanner = new Scanner(line);
					int dataStatIndex = 0;
					String csvString = "";
					boolean first = true;
					while (lineScanner.hasNext()) {
						String str = lineScanner.next();
						if (dataStatIndex < DATA_STATS.length && str.equals("data-stat=\"" + DATA_STATS[dataStatIndex] + "\"")) {
							boolean found = false;
							while (!found) {
								str = lineScanner.next();
								if (str.contains(">") && !str.contains("<")) {
									// Spaces in the Data
									boolean toContinue = true;
									String additional = "";
									while (toContinue) {
										String nextStr = lineScanner.next();
										if (!nextStr.contains("\"") && !nextStr.contains(">") && !nextStr.contains("<")) {
											// Data is in multiple words
											additional += " " + nextStr;
										} else {
											toContinue = false;
											additional += " " + nextStr;
										}
									}
									str += additional;
									str = str.trim().replace(" ", "-");
								}
								if (str.contains("</a>")) {
									String data = str.split("</a>")[0].substring(str.indexOf(">") + 1);
									if (first) {
										csvString += data;
										first = false;
									} else {
										csvString += "," + data;
									}
									found = true;
								} else if (str.contains("</th>")) {
									String data = str.split("</th>")[0].substring(str.indexOf(">") + 1);
									if (first) {
										csvString += data;
										first = false;
									} else {
										csvString += "," + data;
									}
									found = true;
								} else if (str.contains("</td>")) {
									String data = str.split("</td>")[0].substring(str.indexOf(">") + 1);
									if (first) {
										csvString += data;
										first = false;
									} else {
										csvString += "," + data;
									}
									found = true;
								}
							}
							dataStatIndex ++;
						}
					}
					// Write to the CSV file
					writer.write(csvString + "\n");
					lineScanner.close();
				}
			}
			urlScanner.close();
			writer.close();
		} catch (NumberFormatException e) {
			System.out.println("The year entered is not valid.");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}