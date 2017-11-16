import java.awt.geom.Line2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClassClassTester {
	
	public static void main(String[] args) {
		
		Class c = new ClassClass().getClass();
		System.out.println("Constructors");
		for (Constructor con : c.getConstructors()) {
			System.out.println("   "+con.toString());
		}
		
		System.out.println("Methods");
		for (Method met : c.getMethods()) {
			System.out.println("   "+met.toString());
		}
		
	}
	
}
