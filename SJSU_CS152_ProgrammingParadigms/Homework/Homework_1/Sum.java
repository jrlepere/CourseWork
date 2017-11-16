import java.util.ArrayList;
import java.util.List;

/**
 * A Sum Expression that can be executed.
 * @author JLepere2
 * Version 1.1 Sum has [1...*] Products.
 */
public class Sum implements Expression {

	private List<Expression> operands;
	
	/**
	 * Creates a Sum expression.
	 */
	public Sum() {
		operands = new ArrayList<>();
	}
	
	/**
	 * Adds a product to the sum.
	 * @param p The product.
	 */
	public void addProduct(Product p) {
		operands.add(p);
	}
	
	public Double execute() {
		double result = 0;
		for (Expression exp : operands) {
			result += exp.execute();
		}
		return result;
	}
	
}
