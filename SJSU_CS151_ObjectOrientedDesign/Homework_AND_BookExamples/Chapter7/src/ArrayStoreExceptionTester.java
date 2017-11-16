import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

/**
 * Class to experiment with ArrayStoreException
 * @author JLepere2
 * Version 1.1
 */
public class ArrayStoreExceptionTester {

	public static void main(String[] args) {
		
		//n(new ArrayList<Rectangle>()); // ArrayList<Shape> isNOT an arraylist of <Shape>
		q(new Rectangle());
	}
	
	public static void m(Shape[] s) {
		s[0] = new Rectangle();
		s[1] = new Polygon();
	}
	
	public static void n(ArrayList<Shape> s) {
		s.add(new Rectangle());
		s.add(new Polygon());
	}
	
	public static void q(Shape s) {
		if (s instanceof Rectangle) {
			System.out.println("Rectangle");
		} else {
			System.out.println("Not a Rectangle");
		}
	}
	
}
