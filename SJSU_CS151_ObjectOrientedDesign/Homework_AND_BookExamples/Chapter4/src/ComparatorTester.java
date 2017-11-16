import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class to experiment with a comparator
 * @author JLepere2
 *
 */
public class ComparatorTester {

	public static void main(String[] args) {
		ArrayList<String> friends = new ArrayList<>();
		friends.add("Lauren");
		friends.add("Nico");
		friends.add("Bill");
		friends.add("John");
		friends.add("Paul");
		friends.add("Nick C");
		friends.add("Nick L");
		friends.add("Didi");
		friends.add("Merle");
		friends.add("Tom");
		String max = maximum(friends, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		System.out.println(max);
		max = maximum(friends, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		System.out.println(max);
		max = maximum(friends, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return -s1.compareTo(s2);
			}
		});
		System.out.println(max);
	}
	
	/**
	 * Returns the maximum string in the array list based on the comparator
	 * @param a the array list of strings
	 * @param c the comparator
	 * @return the maximum string
	 */
	public static String maximum(ArrayList<String> a, Comparator<String> c) {
		
		if (a.isEmpty()) {
			throw new IllegalArgumentException("No elements in the array.");
		}
		
		String max = a.get(0);
		
		for (int i = 1; i < a.size(); i ++) {
			int comp = c.compare(max, a.get(i));
			if (comp < 0) {
				// new element is greater than max;
				max = a.get(i);
			}
		}
		
		return max;
	}
	
}
