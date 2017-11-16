import java.util.ArrayList;

public class Exercise1 {
	
	public static void main(String args[]) {
		
		//What are the values of the following?
		
		//(a) 2 + 2 + "2"
		String a = 2 + 2 + "2";
		System.out.println("(a) 2 + 2 + '2'");
		System.out.println("Result: " + a);
		System.out.println();
		
		//(b) "" + Countries, where countries is an ArrayList
		 //filled with several Strings
		ArrayList<String> countries = new ArrayList<>();
		countries.add("USA");
		countries.add("Germany");
		countries.add("India");
		countries.add("China");
		countries.add("Canada");
		System.out.println("(b) '' + countries");
		System.out.println("Result: " + "" + countries);
		System.out.println();
		
		//(c) "Hello" + new Greeter("World")
		System.out.println("(c) 'Hello' + new Greeter('World')");
		System.out.println("Result: " + "Hello" + new Greeter("World"));
		
	}
}
