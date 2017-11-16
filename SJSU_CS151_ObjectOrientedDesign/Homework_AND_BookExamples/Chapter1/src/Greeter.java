/**
 * A Class for creating simple greetings.
 * @author JLepere2
 */
public class Greeter {
	
	/**
	 * Constructor for the Greeter class.
	 * @param aName the name of the user
	 */
	public Greeter(String aName) {
		this.name = aName;
	}
	
	/**
	 * Greets the user.
	 * @return a Greeting String
	 */
	public String sayHello() {
		return "Hello, " + name + "!";
	}
	
	/**
	 * A static method to greet any name input
	 * @param name the name of the person to greet
	 * @return a string containing the hello greeting
	 */
	public static String sayHelloTo(String name) {
		
		return "Hello, " + name + "!";
		
	}
	
	/**
	 * Sets this greeter's name to the explicit parameter
	 * @param name the new name for the greeter
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	/**
	 * Copies the name of this greeter to another greeter
	 * @param otherGreeter the other greeter
	 */
	public void setNameTo(Greeter otherGreeter) {
		
		otherGreeter.name = this.name;
		
	}
	
	/**
	 * Gets the name of greeter
	 * @return the name of the greeter
	 */
	public String getName() {
		
		return this.name;
		
	}
	
	private String name; // The name provided for the class
	
}
