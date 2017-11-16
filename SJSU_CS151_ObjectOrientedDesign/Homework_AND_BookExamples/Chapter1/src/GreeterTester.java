/**
 * A class to test the Greeting Class
 * @author JLepere2
 */
public class GreeterTester {

	/**
	 * The main function to run the program. Creates a reference to the Greeter
	 * class and greets the user.
	 * @param args unused
	 */
	public static void main(String[] args) {
		
		Greeter worldGreeter = new Greeter("World");
		Greeter daveGreeter = new Greeter("Dave");
		
		System.out.println(worldGreeter.sayHello());
		System.out.println(daveGreeter.sayHello());
		
		worldGreeter.setNameTo(daveGreeter);
		
		System.out.println(worldGreeter.sayHello());
		System.out.println(daveGreeter.sayHello());
		
	}
	
}
