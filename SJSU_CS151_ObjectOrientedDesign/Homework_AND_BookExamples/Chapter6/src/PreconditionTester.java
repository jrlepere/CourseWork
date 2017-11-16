
public class PreconditionTester {

	public static void main(String[] args) {
		
		Precondition pc = new Precondition(10);
		System.out.println(pc.getN());
		pc.setN(-10);
		System.out.println(pc.getN());
		
	}
	
}
