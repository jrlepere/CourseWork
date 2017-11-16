import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Union {

	public static <T extends Comparable<? super T>> List<T> union(List<T> L1, List<T> L2) {
		List<T> L3 = new ArrayList<T>();
		
		ListIterator<T> iter1 = L1.listIterator();
		ListIterator<T> iter2 = L2.listIterator();
		
		while (iter1.hasNext() || iter2.hasNext()) {
			if (iter1.hasNext() && iter2.hasNext()) {
				T elementFirst = iter1.next();
				T elementSecond = iter2.next();
				
				int compareResult = elementFirst.compareTo(elementSecond);
				
				if (compareResult < 0) {
					if (!L3.contains(elementFirst)) {
						L3.add(elementFirst);
					}
					iter2.add(elementSecond);
					iter2.previous();
				}
				else if (compareResult > 0) {
					if (!L3.contains(elementSecond)) {
						L3.add(elementSecond);
					}
					iter1.add(elementFirst);
					iter1.previous();
				} else {
					if (!L3.contains(elementFirst)) {
						L3.add(elementFirst);
					}
					if (!L3.contains(elementSecond)) {
						L3.add(elementSecond);
					}
				}
			} else if (iter1.hasNext()) {
				T elementFirst = iter1.next();
				if (!L3.contains(elementFirst)) {
					L3.add(elementFirst);
				}
			} else {
				T elementSecond = iter2.next();
				if (!L3.contains(elementSecond)) {
					L3.add(elementSecond);
				}
			}
		}
		
		return L3;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		
		List<Integer> L1 = new ArrayList<>();
		List<Integer> L2 = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			int rand1 = rand.nextInt(10) + (10*i);
			L1.add(rand1);
			int rand2 = rand.nextInt(10) + (10*i);
			L2.add(rand2);
		}
		
		L1.add(100);
		L1.add(100);
		L2.add(100);
		L2.add(100);
		
		System.out.println("L1: " + L1.toString());
		System.out.println("L2: " + L2.toString());
		
		List<Integer> L3 = Union.union(L1, L2);
		System.out.println("Union: " + L3.toString());
	}
	
}
