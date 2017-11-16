import java.util.Scanner;

/**
 * Represents the Consol for executing SOPs.
 * @author JLepere2
 * Version 1.1 Initial Implementation.
 */
public class Consol {

	private Scanner sc = new Scanner(System.in);
	
	/**
	 * The main method for the application.
	 * @param args The command line arguments, not used.
	 */
	public static void main(String[] args) {
		Consol consol = new Consol();
		consol.repl();
	}
	
	/**
	 * Read Execute Print Loop.
	 */
	public void repl() {
		System.out.println("Enter Q to quit.");
		while (true) {	
			try {		
				System.out.print("-->");
				String str = sc.nextLine();
				if (str.equals("Q")) {
					break;
				}
				if (str.isEmpty()) {
					throw new EmptyExpressionException();
				}
				Expression exp = parse(str);
				System.out.println(exp.execute());
			} catch (EmptyExpressionException e) {
				System.out.println(e.getMessage());
			} catch (IllegalOperandException e) {
				System.out.println(e.getMessage());
			} catch (IllegalSumException e) {
				System.out.println(e.getMessage());				
			} catch (IllegalProductException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Bye");
	}
	
	/**
	 * Parses a string and returns an Expression object.
	 * @param str The string to parse.
	 * @return an Expression representing the parsed string.
	 * @throws IllegalProductException 
	 * @throws IllegalSumException 
	 * @throws IllegalOperandsException 
	 */
	private Expression parse(String str) throws IllegalOperandException, IllegalProductException, IllegalSumException {
		
		if (str.startsWith("+") || str.endsWith("+")) {
			throw new IllegalSumException(str);
		}
		Sum sumExp = new Sum();
		Scanner sumScanner = new Scanner(str);
		sumScanner.useDelimiter("\\s*\\+\\s*");
		try {
			while (sumScanner.hasNext()) {
				// Products
				Product prodExp = new Product();
				String prod = sumScanner.next();
				if (prod.startsWith("*") || prod.endsWith("*")) {
					throw new IllegalProductException(prod);
				}
				Scanner prodScanner = new Scanner(prod);
				prodScanner.useDelimiter("\\s*\\*\\s*");
				String operand = "";
				try {
					while (prodScanner.hasNext()) {
						// Numbers
						operand = prodScanner.next();
						prodExp.addOperand(Double.parseDouble(operand));
					}
				} catch (NumberFormatException e) {
					throw new IllegalOperandException(operand);
				} finally {
					prodScanner.close();
				}
			sumExp.addProduct(prodExp);
			}
		return sumExp;
		} finally {
			sumScanner.close();
		}
	}
	
}
