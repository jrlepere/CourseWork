import java.util.Arrays;

public class EnumTester {
	
	public static void main(String[] args) {
		Grade a = Grade.INCOMPLETE;
		System.out.println(a);
		System.out.println(Arrays.toString(Grade.values()));
		
		Size[] sizes = Size.values();
		System.out.println(Arrays.toString(sizes));
	
		for (Size s : sizes) {
			System.out.println(s.getValue());
		}
		
		System.out.println("SMALL VALUE: " + Size.SMALL.getValue());
	}
	
}
