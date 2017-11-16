
/**
 * Write a description of class CounterTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CounterTester
{
    public static void main(String[] args)
    {
        Counter num1 = new Counter();
        Counter num2 = num1;
        System.out.println(num1.equals(num2));
    }
}
