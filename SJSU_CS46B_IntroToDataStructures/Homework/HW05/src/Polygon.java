import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
   A polygon with a number of Point2D.Double corners
*/
public class Polygon
{
   private ArrayList<Point2D.Double> corners;

   /**
      Constructs a Polygon object with no corners
   */
   public Polygon()
   {
      corners = new ArrayList<Point2D.Double>();
   }
   
   /**
      Adds a point to the list.
      @param p the point to add
   */
   public void add(Point2D.Double p)
   {
      corners.add(p);   
   }
   
   /**
      Computes the area of a polygon.
      @return area of a polygon
   */
   public double getArea()
   {
	   if (corners.size() == 3) { 
		   return areaOfTriangle(corners.get(0), corners.get(1), corners.get(2));
	   }
	   Polygon newPolygon = new Polygon();
	   for (int i = 0; i < corners.size(); i ++) {
		   if (i != 1) {
			   newPolygon.add(corners.get(i));
		   }
	   }
	   return areaOfTriangle(corners.get(0), corners.get(1), corners.get(2)) + newPolygon.getArea();
   }
   
   /**
    * Computes the area of a triangle within the polygon
    * @param p1 the first point
    * @param p2 the second point
    * @param p3 the third point
    * @return the area of the triangle
    */
   public static double areaOfTriangle(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) 
   {
	   double x1 = p1.getX();
	   double y1 = p1.getY();
	   double x2 = p2.getX();
	   double y2 = p2.getY();
	   double x3 = p3.getX();
	   double y3 = p3.getY();
	   
	   return Math.abs(((x1*y2) + (x2*y3) + (x3*y1) - (y1*x2) - (y2*x3) - (y3*x1))/2);
   }
}
