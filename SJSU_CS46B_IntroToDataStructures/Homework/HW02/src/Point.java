public class Point implements Measurable, Comparable
{
   private double x;
   private double y;
   
   /**
      Constructs a point with given x- and y-components.
      @param x the x-component
      @param y the y-component
   */
   public Point(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   /**
      Returns the x-component of this point.
      @return the x-component
   */
   public double getX() { return x; }

   /**
      Returns the y-component of this point.
      @return the y-component
   */
   public double getY() { return y; }

   /**
    * Gets the distance from the origin
    * @return the distance from the origin
    */
   public double getMeasure() 
   {
	   return Math.sqrt((x*x) + (y*y));
   }

   /**
    * Compares two Point Objects
    * @param object the other point to compare
    * @return an int corresponding to the relationship
    */
   public int compareTo(Object object) {
	   Point otherPoint = (Point) object;
	   if (this.x < otherPoint.getX()) {
		   return -1;
	   } else if (this.x > otherPoint.getX()) {
		   return 1;
	   } else {
		   if (this.y < otherPoint.getY()) {
			   return -1;
		   } else if (this.y > otherPoint.getY()) {
			   return 1;
		   }
	   }
	   return 0;
   }
}

