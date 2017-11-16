
public class NonEmptyTree implements LispTree
{
	private Object data;
	private LispTree left;
	private LispTree right;
	
	public NonEmptyTree(Object data, LispTree left, LispTree right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public boolean empty() 
	{
		return false;
	}

	public Object data() 
	{
		return data;
	}

	public LispTree left() 
	{
		return left;
	}

	public LispTree right() 
	{
		return right;
	}

	public String toString()
	{	
		if (left.empty() && right.empty()) {
			return "t(" + data.toString() + ")";
		} else {
			return "t(" + data.toString() + ", " + left.toString() + ", " + right.toString() + ")";
		}
	}

	public int eval() 
	{
		if (left.empty() && right.empty()) {
			return Integer.parseInt(data.toString());
		} else {
			if (data.equals("+")) {
				return left.eval() + right.eval();
			} else if (data.equals("-")) {
				return left.eval() - right.eval();
			} else if (data.equals("/")) {
				return left.eval() / right.eval();
			} else if (data.equals("*")){
				return left.eval() * right.eval();
			} else if (data.equals("%")) {
				return left.eval() % right.eval();
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	public int height() 
	{
		return 1 + Math.max(left.height(), right.height());
	}
}
