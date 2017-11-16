import java.util.ArrayList;
import java.util.List;

/**
 * A Product Expression that can be executed.
 * @author JLepere2
 * Version 1.1 Product has [1...*] Doubles.
 */
public class Product implements Expression {

	private List<Double> operands;
	
	/**
	 * Creates a Product expression.
	 */
	public Product() {
		operands = new ArrayList<>();
	}
	
	/**
	 * Adds a Double operand to the product expression.
	 * @param d The double to add.
	 */
	public void addOperand(Double d) {
		operands.add(d);
	}
	
	public Double execute() {
		double result = 1.0;
		for (Double d : operands) {
			result *= d;
		}
		return result;
	}
	
}
