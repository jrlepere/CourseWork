import java.util.Stack;

public class PostFix {

	public static double evaluatePostfix(String expression) {
		Stack<String> stack = new Stack<>();
		String[] ops = expression.split(" ");
		for (int i = 0; i < ops.length - 1; i ++) {
			// i < ops.length - 1 to compenstate for the =
			String str = ops[i];
			if ("0123456789".contains(str.substring(0,1))) {
				// str is a digit 
				stack.push(str);
			} else {
				// str is an operation
				double first = Double.parseDouble(stack.pop());
				double second = Double.parseDouble(stack.pop());
				if (str.equals("+")) {
					stack.push(second + first + "");
				} else if (str.equals("-")) {
					stack.push(second - first + "");
				} else if (str.equals("*")) {
					stack.push(second * first + "");
				} else {
					stack.push(second / first + "");
				}
			}
		}
		return Double.parseDouble(stack.pop());
	}
	
	public static void main(String[] args)
	{
		String expression1 = "4 5 + =";
		System.out.println(expression1 + " " + PostFix.evaluatePostfix(expression1));
		
		String expression2 = "4 5 - =";
		System.out.println(expression2 + " " + PostFix.evaluatePostfix(expression2));
		
		String expression3 = "4 5 * =";
		System.out.println(expression3 + " " + PostFix.evaluatePostfix(expression3));
		
		String expression4 = "4 5 / =";
		System.out.println(expression4 + " " + PostFix.evaluatePostfix(expression4));
		
		String expression5 = "1 2 + 3 * 6 + 2 3 + / =";
		System.out.println(expression5 + " " + PostFix.evaluatePostfix(expression5));
		
		String expression6 = "10 2 8 * + 3 - =";
		System.out.println(expression6 + " " + PostFix.evaluatePostfix(expression6));
	}
}
