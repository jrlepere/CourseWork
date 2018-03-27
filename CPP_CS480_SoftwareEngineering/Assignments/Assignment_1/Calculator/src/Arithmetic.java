/**
 * An abstract class for performing arithmetic.
 * @author JLepere2
 * @date 01/22/2018
 */
public abstract class Arithmetic {

	/**
	 * Executes the expression.
	 * @param leftOperand the left operand
	 * @param operator the operator
	 * @param rightOperand the right operand
	 * @return the result of executing the expression
	 */
	public static String ExecuteExpression(String leftOperand, String operator, String rightOperand) {
		if (operator.equals(ButtonPanel.ADD_CHAR)) {
			return Double.toString(Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand));
		} else if (operator.equals(ButtonPanel.SUBTRACT_CHAR)) {
			return Double.toString(Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));
		} else if (operator.equals(ButtonPanel.MULTIPLY_CHAR)) {
			return Double.toString(Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand));
		} else if (operator.equals(ButtonPanel.DIVIDE_CHAR)) {
			return Double.toString(Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand));
		} else {
			return "";
		}
	}
	
}
