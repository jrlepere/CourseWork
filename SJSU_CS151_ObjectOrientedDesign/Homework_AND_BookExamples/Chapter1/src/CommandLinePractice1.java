/**
 * A class to practice with command line arguments
 * @author JLepere2
 */
public class CommandLinePractice1 {

	/**
	 * A main method to practice running command line arguments
	 * @param args the arguments passed from the command line
	 */
	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("You did not enter any arguments!");
		} else {
			int count = 1;
			for (String arg: args) {
				System.out.println("Args " + count + ": " + arg);
				count ++;
			}
		}
	}
	
}
