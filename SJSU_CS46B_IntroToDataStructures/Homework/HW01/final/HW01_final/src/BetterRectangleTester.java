import java.awt.Rectangle;

/**
   A class to test BetterRectangle.
*/
public class BetterRectangleTester
{
   public static void main(String[] args)
   {
      BetterRectangle rect = new BetterRectangle(5, 10, 5, 7);
      System.out.println(rect.getPerimeter());
      System.out.println("Expected: 24");
      System.out.println(rect.getArea());
      System.out.println("Expected: 35");
      rect.grow(5, 5);
      System.out.println(rect.getPerimeter());
      System.out.println("Expected: 64");
      System.out.println(rect.getArea());
      System.out.println("Expected: 255");
      System.out.println(rect instanceof Rectangle);
      System.out.println("Expected: true");
   }
}