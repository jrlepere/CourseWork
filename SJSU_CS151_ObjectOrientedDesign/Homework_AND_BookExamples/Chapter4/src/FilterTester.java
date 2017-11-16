import java.util.Arrays;

/**
 * Test with anonymous classes using the Filtering interface
 * @author JLepere2
 *
 */
public class FilterTester {

	public static void main(String[] args) {
		
		String[] friends = new String[10];
		friends[0]= "Jake Lepere";
		friends[1]= "Lauren Holden";
		friends[2]= "Bill Lepere";
		friends[3]= "Nick Lepere";
		friends[4]= "Didi Lepere";
		friends[5]= "Nico Mendieta";
		friends[6]= "John Pricco";
		friends[7]= "Nick Cantoni";
		friends[8]= "Paul";
		friends[9]= "Tom Brady";
		
		String[] results = filter(friends, new Filter() {
			public boolean accept(String x) {
				if (x.contains("Lepere")) {
					return true;
				}
				return false;
			}
		});
		System.out.println(Arrays.toString(results));
		
	}
	
	/**
	 * Returns all elements in a that are accepted by the filter
	 * @param a the array of strings
	 * @param f the filter
	 * @return an array of elements that are accepted by the filter
	 */
	public static String[] filter(String[] a, Filter f) {
		
		int acceptedCount = 0;
		for (int i = 0; i < a.length; i ++) {
			if (f.accept(a[i])) {
				acceptedCount ++;
			}
		}
		
		String[] accepted = new String[acceptedCount];
		acceptedCount = 0;
		
		for (int i = 0; i < a.length; i ++) {
			if (f.accept(a[i])) {
				accepted[acceptedCount] = a[i];
				acceptedCount ++;
			}
		}
		
		return accepted;
		
	}
	
}
