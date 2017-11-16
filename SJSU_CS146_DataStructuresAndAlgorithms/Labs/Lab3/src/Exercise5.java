import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Exercise5 {

	public static void printLosts(Collection<Integer> L, Collection<Integer> P) {
		Iterator<Integer> iterP = P.iterator();
		Iterator<Integer> iterL = L.iterator();
		int currentLIndex = 0;
		int previousPValue = -1;
		int previousLValue = 0;
		while (iterP.hasNext()) {
			int pValue = iterP.next();
			if (previousPValue == pValue) {
				System.out.println(previousLValue);
			} else {
				previousPValue = pValue;
				while (currentLIndex < pValue) {
					currentLIndex ++;
					if (iterL.hasNext()) {
						iterL.next();
					} else {
						throw new IllegalStateException();
					}
				}
				if (iterL.hasNext()) {
					currentLIndex ++;
					previousLValue = iterL.next();
					System.out.println(previousLValue);
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		
		List<Integer> L = new ArrayList<>();
		List<Integer> P = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			L.add(rand.nextInt(101));
			P.add(rand.nextInt(10));
		}
		
		Collections.sort(L);
		Collections.sort(P);
		
		System.out.println("L: " + L.toString());
		System.out.println("P: " + P.toString());
		
		Exercise5.printLosts(L,P);

	}	
}
