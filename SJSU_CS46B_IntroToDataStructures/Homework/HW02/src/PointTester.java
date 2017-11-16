public class PointTester
{
   public static void main(String[] args)
   {
	   Point p1 = new Point(3, 4);
	   Point p2 = new Point(13, 84);
	   Measurable p3 = new Point(65, 72);

	   System.out.println(p1.getMeasure());
       System.out.println("Expected: 5");
       System.out.println(p2.getMeasure());
	   System.out.println("Expected: 85");
       System.out.println(p3.getMeasure());
       System.out.println("Expected: 97");
	      
      Point p6 = new Point(3, 4);
      Point p4 = new Point(2, 4);
      Comparable<Point> p5 = new Point(3, 2);

      System.out.println(p6.compareTo(p4) < 0);
      System.out.println("Expected: false");
      System.out.println(p4.compareTo(p6) < 0);
      System.out.println("Expected: true");
      System.out.println(p6.compareTo((Point) p5) < 0);
      System.out.println("Expected: false");
      System.out.println(p5.compareTo(p6) < 0);
      System.out.println("Expected: true");
      System.out.println(p6.compareTo(new Point(3, 4)) == 0);
      System.out.println("Expected: true");
   }
}