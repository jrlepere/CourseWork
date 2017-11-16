import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PolygonTester
{
   public static void main(String[] args)
   {
      // square
      ArrayList<Point2D.Double> p = new ArrayList<>();
      p.add(new Point2D.Double(10, 20));
      p.add(new Point2D.Double(20, 20));
      p.add(new Point2D.Double(20, 10));
      p.add(new Point2D.Double(10, 10));
      
      System.out.println("Area: " + Polygons.area(p));
      System.out.println("Expected: 100.0");

      // regular hexagon with radius 1
      p = new ArrayList<>();
      for (int i = 0; i < 6; i++)
         p.add(new Point2D.Double(Math.sin(i * Math.PI / 3),
               Math.cos(i * Math.PI / 3)));
      System.out.println("Area: " + Polygons.area(p));
      System.out.println("Expected: " + 3 * Math.sqrt(3) / 2);      

      // Stop sign
      p = new ArrayList<>();;
      p.add(new Point2D.Double(10, 20));
      p.add(new Point2D.Double(20, 10));
      p.add(new Point2D.Double(20, -10));
      p.add(new Point2D.Double(10, -20));
      p.add(new Point2D.Double(-10, -20));
      p.add(new Point2D.Double(-20, -10));
      p.add(new Point2D.Double(-20, 10));
      p.add(new Point2D.Double(-10, 20));
      System.out.println("Area: " + Polygons.area(p));
      System.out.println("Expected: " + 1400);
      
      String str = "100.10";
      double value = Double.parseDouble(str);
      }
}