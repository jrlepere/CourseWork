import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Dict 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(args[0]));
		in.useDelimiter("[^A-Za-z0-9_]+|A|Za|z0|9|_");
		
		HashSet<String> reserved = new HashSet<>(Arrays.asList("abstract", "continue", "for",
				   "new", "switch", "assert", "default", "goto", "package", "synchronized",
				   "boolean", "do", "if", "private", "this", "break", "double", "implements",
				   "protected", "throw", "byte", "else", "import", "public", "throws", "case",
				   "enum", "instanceof", "return", "transient", "catch", "extends", "int",
				   "short", "try", "char", "final", "interface", "static", "void", "class",
				   "finally", "long", "strictfp", "volatile", "const", "float", "native",
				   "super", "while"));
		
		HashMap<String, TreeSet<Integer>> index = new HashMap<String, TreeSet<Integer>>();
		int lineNumber = 0;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine();
			Scanner in2 = new Scanner(line).useDelimiter("[^A-Za-z0-9_]+|A|Za|z0|9|_");
			while (in2.hasNext()) {
				String identifier = in2.next();
				boolean javaReserved = false;
				for (String java: reserved) {
					if (java.equals(identifier)) {
						javaReserved = true;
					}
				}
				if (!javaReserved) {
					if (!identifier.equals("")) {
						if (index.get(identifier) == null) {
							TreeSet<Integer> set = new TreeSet<>();
							set.add(lineNumber);
							index.put(identifier, set);
						} else {
							TreeSet<Integer> set = index.get(identifier);
							set.add(lineNumber);
							index.put(identifier, set);
						}
					}
				}
			}
			in2.close();
		}
		for (String s : index.keySet()) {
			System.out.print(s + ": ");
			for (Integer i : index.get(s)) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		in.close();
	}
}
