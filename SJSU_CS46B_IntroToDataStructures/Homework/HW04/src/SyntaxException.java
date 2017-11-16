public class SyntaxException extends RuntimeException
{
   public SyntaxException() {}
   public SyntaxException(String message) { super(message); }
   public SyntaxException(int line) { this("Syntax error in line " + line); }
}