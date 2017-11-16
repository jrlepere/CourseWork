
public class Greeter {

	private String name;
	
	/**
	 * Constructs a Greeter object that can greet a person or entity
	 * @param nName the name of the person or entity who should be 
	 * addressed in the greetings.
	 */
	public Greeter(String aName) {
		name = aName;
	}
	
	/**
	 * Greet with a "Hello" message
	 * @return a message containing "Hello" and the nam of the greeted
	 * person or entity.
	 */
	public String sayHello() {
		return "Hello, " + name + "!";
	}
	
	/*
	@Override
	public String toString() {
		return "Something";
	}*/
}
