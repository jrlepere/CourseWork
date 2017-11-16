
public class EmptyTree implements LispTree
{

	public boolean empty() 
	{
		return true;
	}

	public Object data() 
	{
		return null;
	}

	public LispTree left() 
	{
		return null;
	}

	public LispTree right() 
	{
		return null;
	}

	public String toString()
	{
		return "NIL";
	}

	public int eval() 
	{
		return (Integer) null;
	}

	public int height() 
	{
		return 0;
	}
}
