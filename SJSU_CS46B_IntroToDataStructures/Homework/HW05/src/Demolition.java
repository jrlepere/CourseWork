/**
 * This program demolishes a string
 * @author JLepere2
 */
public class Demolition
{
	private int cost;
	private String description;
	private Demolition restDemolition;
	/**
	 * Creates a Demolition object
	 * @param line the string to demolish
	 */
	public Demolition(String s)
	{
		if (s.length() <= 1) {
			description = s;
		} else {
			String first = s.substring(0, 1);
			String last = s.substring(s.length()-1);
			String rest;
			if (isVowel(first) && !isVowel(last) || isVowel(first) && isVowel(last)) {
				rest = s.substring(1);
				description = first + " " + rest;
				restDemolition = new Demolition(rest);
				cost = stepCost(first,rest) + restDemolition.getCost();
			} else if (!isVowel(first) && isVowel(last)) {
				rest = s.substring(0,s.length()-1);
				description = rest + " " + last;
				restDemolition = new Demolition(rest);
				cost = stepCost(last,rest) + restDemolition.getCost();
			} else {
				//Both are consonants
				boolean[] removeFirst = new boolean[s.length()];
				boolean[] removeLast = new boolean[s.length()];
				for (int i = 0; i < removeFirst.length; i ++) {
					removeFirst[i] = isVowel(s.substring(i,i+1));
					removeLast[i] = isVowel(s.substring(s.length()-(i+1), s.length()-i));
				}
				boolean found = false;
				for (int i = 1; i < removeFirst.length && !found; i ++) {
					if (removeFirst[i] && !removeFirst[i+1] && removeLast[i] && !removeLast[i+1]) {
						found = true;
						rest = s.substring(1);
						description = first + " " + rest;
						restDemolition = new Demolition(rest);
						cost = stepCost(first,rest) + restDemolition.getCost();
					}
					if (removeFirst[i] != removeLast[i]) {
						if (removeFirst[i]) {
							found = true;
							rest = s.substring(1);
							description = first + " " + rest;
							restDemolition = new Demolition(rest);
							cost = stepCost(first,rest) + restDemolition.getCost();
						} else {
							found = true;
							rest = s.substring(0,s.length()-1);
							description = rest + " " + last;
							restDemolition = new Demolition(rest);
							cost = stepCost(last,rest) + restDemolition.getCost();
						}
					}
					if (!found) {
						if (i == removeFirst.length-1) {
							rest = s.substring(1);
							description = first + " " + rest;
							restDemolition = new Demolition(rest);
							cost = stepCost(first,rest) + restDemolition.getCost();
					}
					}
				}
			}
		}
	}
	
	/**
	 * Gets the cost of the demolition
	 * @return the cost of the demolition
	 */
	public int getCost()
	{
		return cost;
	}
	
	/**
	 * Calculates the demolition cost of any string
	 * @param s the string to demolish
	 * @return the cost of the demolition
	 */
	public static int cost(String s)
	{
		String str = s;
		if (str.length() == 1) {return 0;}
		int cost = 0;
		String first = str.substring(0, 1);
		String last = str.substring(str.length()-1);
		int c1 = stepCost(first,str.substring(1));
		int c2 = stepCost(last,str.substring(0, str.length()-1));
		if (c1 < c2) {
			str = str.substring(1);
			cost = c1 + cost(str);
		} else if (c1 > c2) {
			str = str.substring(0, str.length()-1);
			cost = c2 + cost(str);
		} else {
			String nextFirst = str.substring(1, 2);
			String nextLast = str.substring(str.length()-2, str.length()-1);
			int c11 = stepCost(nextFirst,str.substring(2));
			int c21 = stepCost(nextLast,str.substring(0, str.length()-2));
			if (c11 <= c21) {
				str = str.substring(1);
				cost = c1 + cost(str);
			} else {
				str = str.substring(0, str.length()-1);
				cost = c2 + cost(str);
			}
		}
		return cost;
	}

	/**
	 * Calculates the step cost for a specific demolition step
	 * @param letter the letter to remove
	 * @param rest the rest of the string
	 * @return the cost to demolish the specific step
	 */
	public static int stepCost(String letter, String rest) 
	{
		return "aeiou".contains(letter) ? 0 : rest.length();
	}
	
	public String toString()
	{
		if (restDemolition == null) return description;
		else return description + " -> " + restDemolition.toString();
	}
	
	public static boolean isVowel(String s)
	{
		return "aeiou".contains(s);
	}
}
