import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class CPIConverter 
{
	private double[] cpi;
	private static final int FIRST_YEAR = 1913;
	private static final int LAST_YEAR = 2003;
	
	public void read(String location)
	{
		cpi = new double[LAST_YEAR - FIRST_YEAR + 1];
		URL cpiURL;
		try {
			cpiURL = new URL(location);
			InputStream cpiIn = cpiURL.openStream();
			Scanner in = new Scanner(cpiIn, "UTF-8");
			double[][] list = new double[LAST_YEAR - FIRST_YEAR + 1][2];
			int count = 0;
			while(in.hasNextLine() && count < LAST_YEAR - FIRST_YEAR + 1) {
				String line = in.nextLine();
				String[] splitLine = line.split("\\s+");
				ArrayList<Double> values = new ArrayList<>();
				for (int i = 0; i < splitLine.length; i ++) {
					values.add(Double.parseDouble(splitLine[i]));
				}
				list[count][0] = values.get(0);
				list[count][1] = values.get(1);
				count++;
			}
			in.close();
			for (int i = 0; i < cpi.length; i ++) {
				int y = (int) list[i][0];
				double ave = list[i][1];
				cpi[y - 1913] = ave;
			}
		} catch (MalformedURLException e) {
			System.out.println("No URL");
		} catch (IOException e) {
			System.out.println("No input");
		}
		
	}

	public double equivalentAmount(double amount, int fromYear, int toYear) 
	{
		double fromYearAve = cpi[fromYear - 1913];
		double toYearAve = cpi[toYear - 1913];
		
		return ((amount/fromYearAve) * (toYearAve)); // We'll fix this later
	}
}
