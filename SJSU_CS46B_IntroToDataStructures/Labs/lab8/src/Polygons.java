import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Polygons
{
   public static double tTriangleArea(
      Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) 
   {
         double x1 = p1.getX();
         double y1 = p1.getY();     
         double x2 = p2.getX();
         double y2 = p2.getY();         
         double x3 = p3.getX();
         double y3 = p3.getY(); 
      
         return Math.abs((x1 * y2) 
            + (x2 * y3) 
            + (x3 * y1) 
            - (y1 * x2) 
            - (y2 * x3) 
            - (y3 * x1)) 
            / 2;
   }

   /**
      Computes the area of a polygon.
      @param p the vertices of the polygon
      @return area of a polygon
   */
   public static double area(ArrayList<Point2D.Double> p)
   {
      int n = p.size();
      if (n==3) {
    	  return tTriangleArea(p.get(0),p.get(1),p.get(2));
      } else {
    	  int k = n/2;
    	  ArrayList<Point2D.Double> p1 = new ArrayList<>();
    	  for (int i = 0; i <= k; i ++) {
    		  p1.add(p.get(i));
    	  }
    	  ArrayList<Point2D.Double> p2 = new ArrayList<>();
    	  for (int i = k; i < p.size(); i ++) {
    		  p2.add(p.get(i));
    	  }
    	  p2.add(p.get(0));
    	  return area(p1) + area(p2);
      }
   }
}