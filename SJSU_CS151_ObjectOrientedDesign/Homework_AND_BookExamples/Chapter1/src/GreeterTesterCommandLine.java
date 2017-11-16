/**
 * A class that takes command line arguments and makes Greeters from them.
 * @author JLepere2
 *
 */
public class GreeterTesterCommandLine {

	/**
	 * The main method of the class.
	 * @param args the arguments passed from the command line.
	 */
	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("You did not enter any command line arguments!");
		} else {
			for (String arg : args) {
				
				Greeter g = new Greeter(arg);
				System.out.println(g.sayHello());
				
			}
		}
		
	}
	
}
