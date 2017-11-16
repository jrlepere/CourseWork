
public interface LispTree 
{
	boolean empty();
	Object data();
	LispTree left();
	LispTree right();
	int eval();
	int height();
}
