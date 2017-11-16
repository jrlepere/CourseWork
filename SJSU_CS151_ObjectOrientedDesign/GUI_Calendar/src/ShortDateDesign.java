/**
 * A short date design implementing the DateTextDesign
 * @author JLepere2
 * Version 1.1
 */
public class ShortDateDesign implements DateTextDesign {

	public String getDate(int year, int month, int day) {
		return month + "/" + day + "/" + year;
	}

}
