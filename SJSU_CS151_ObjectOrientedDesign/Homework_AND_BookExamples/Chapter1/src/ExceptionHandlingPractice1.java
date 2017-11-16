import java.util.ArrayList;
import java.util.Scanner;

public class ExceptionHandlingPractice1 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); //Scanner to read user input
		
		String input = "";
		ArrayList<Integer> values = new ArrayList<>();
		boolean first = true;
		
		do {
			if (first) {
				System.out.print("Enter a number or q to quit: ");
				first = false;
			} else {
				System.out.print("Enter another: ");
			}
			
			input = scanner.next();
			
			try {
				int value = Integer.parseInt(input);
				if (value > 5) {
					values.add(value);
				} else {
					throw new IllegalArgumentException("The value should be greater than 0!");
				}
				
			} catch (Exception e) {
				// e.printStackTrace(); good idea to track the issue
				if (!input.equals("q")) {
					System.out.println("You have entered an illegal value.");
				}
			}
			
		} while (!input.toLowerCase().equals("q"));
		
		if (values.size() > 0) {
			System.out.println("The values you entered are: "+values.toString());
		} else {
			System.out.println("You did not enter any values.");
		}
		
		scanner.close();
		
	}
	
}
