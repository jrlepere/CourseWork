import java.util.Comparator;
import java.util.ArrayList;

public class SectionNumberComparator implements Comparator<String> 
{
	/**
	 * Compares two section numbers
	 * @param section1 the first section to compare
	 * @param section2 the second section to compare
	 */
	public int compare(String section1, String section2) 
	{
		ArrayList<String> section1Array = new ArrayList<>();
		ArrayList<String> section2Array = new ArrayList<>();
		
		String value1 = "";
		for (int i = 0; i < section1.length(); i ++) {
			if (!("" + section1.charAt(i)).equals(".")) {
				value1 += section1.charAt(i);
			} else {
				section1Array.add(value1);
			}
			if (i == section1.length() - 1) {
				section1Array.add(value1);
			}
		}
		
		String value2 = "";
		for (int i = 0; i < section2.length(); i ++) {
			if (!("" + section2.charAt(i)).equals(".")) {
				value2 += section2.charAt(i);
			} else {
				section2Array.add(value2);
			}
			if (i == section2.length() - 1) {
				section2Array.add(value2);
			}
		}
		
		for (int i = 0; i < section1Array.size() && i < section2Array.size(); i ++) {
			if(section1Array.get(i).compareTo(section2Array.get(i)) < 0) {
				return -1;
			} else if(section1Array.get(i).compareTo(section2Array.get(i)) > 0) {
				return 1;
			}
		}
		if (section1Array.size() > section2Array.size()) {
			return 1;
		} else if (section1Array.size() < section2Array.size()) {
			return -1;
		} else {
			return 0;
		}
	}
}