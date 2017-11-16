import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class BabyNameDiff {
	public static void main(String[] args) throws FileNotFoundException {
		String filename1 = args[0];
		String filename2 = args[1];

		ArrayList<ArrayList<String>> names1 = read(filename1);
		ArrayList<ArrayList<String>> names2 = read(filename2);

		//Gets boys names
		ArrayList<String> boyNamesInFirstButNotSecond = BabyNameDiff.contains(names1.get(0), names2.get(0));
		ArrayList<String> boyNamesInSecondButNotFirst = BabyNameDiff.contains(names2.get(0), names1.get(0));
		
		//Gets girl names
		ArrayList<String> girlNamesInFirstButNotSecond = BabyNameDiff.contains(names1.get(1), names2.get(1));
		ArrayList<String> girlNamesInSecondButNotFirst = BabyNameDiff.contains(names2.get(1), names1.get(1));
		
		for (int i = 0; i < boyNamesInFirstButNotSecond.size() || i < girlNamesInFirstButNotSecond.size(); i ++) {
			if (i < boyNamesInFirstButNotSecond.size() && i < girlNamesInFirstButNotSecond.size()) {
				System.out.printf("%-30s %s\n", boyNamesInFirstButNotSecond.get(i), girlNamesInFirstButNotSecond.get(i));
			} else if (i < girlNamesInFirstButNotSecond.size()) {
				System.out.printf("%-30s %s\n", "", girlNamesInFirstButNotSecond.get(i));
			} else {
				System.out.println(boyNamesInFirstButNotSecond.get(i));
			}
		}
		System.out.println();
		for (int i = 0; i < boyNamesInSecondButNotFirst.size() || i < girlNamesInSecondButNotFirst.size(); i ++) {
			if (i < boyNamesInSecondButNotFirst.size() && i < girlNamesInSecondButNotFirst.size()) {
				System.out.printf("%-30s %s\n", boyNamesInSecondButNotFirst.get(i), girlNamesInSecondButNotFirst.get(i));
			} else if (i < girlNamesInSecondButNotFirst.size()) {
				System.out.printf("%30s %s\n", "", girlNamesInSecondButNotFirst.get(i));
			} else {
				System.out.println(boyNamesInSecondButNotFirst.get(i));
			}
		}
	}

	private static ArrayList<ArrayList<String>> read(String filename) throws FileNotFoundException {
		ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();
		ArrayList<String> boys = new ArrayList<>();
		ArrayList<String> girls = new ArrayList<>();
		Scanner in = new Scanner(new File(filename));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);
			boolean nameFound = false;
			while (lineScanner.hasNext() && !nameFound) {
				int value = lineScanner.nextInt();
				String boyName = lineScanner.next();
				value = lineScanner.nextInt();
				String girlName = lineScanner.next();
				nameFound = true;
				boys.add(boyName);
				girls.add(girlName);
			}
			lineScanner.close();
		}
		in.close();
		names.add(boys);
		names.add(girls);
		return names;
	}

	/**
	 * Creates a string with all the names in the first but not the second list
	 * @param first the first set of names
	 * @param second the second set of names
	 * @return an ArrayList of names in first but not second
	 */
	private static ArrayList<String> contains(ArrayList<String> first, ArrayList<String> second) {
		ArrayList<String> namesInFirstButNotSecond = new ArrayList<>();
		for (int i = 0; i < first.size(); i++) {
			String name1ToCompare = first.get(i);
			boolean foundSimilarName = false;
			for (int j = 0; j < second.size() && !foundSimilarName; j++) {
				String name2ToCompare = second.get(j);
				if (name1ToCompare.equals(name2ToCompare)) {
					foundSimilarName = true;
				}
			}
			if (!foundSimilarName) {
				namesInFirstButNotSecond.add(name1ToCompare);
			}
		}
		Collections.sort(namesInFirstButNotSecond, new Comparator<String>() {
			public int compare(String name1, String name2) {
				return name1.toString().compareTo(name2.toString());
			}
		});
		return namesInFirstButNotSecond;
	}
}
