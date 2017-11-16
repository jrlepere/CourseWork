import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class SingleLinkedList {

	public static <AnyType> void printReverse(LinkedList<AnyType> list) {
		Stack<AnyType> s = new Stack<>();
		Iterator<AnyType> iter = list.iterator();
		while (iter.hasNext()) {
			s.push(iter.next());
		}
		
		System.out.print("List reversed: [");
		boolean first = true;
		while (!s.isEmpty()) {
			AnyType item = s.pop();
			if (first) {
				System.out.print(item.toString());
				first = false;
			} else {
				System.out.print(", " + item.toString());
			}
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		Random ran = new Random();
		for (int i = 0; i < 15; i ++) {
			list.add(ran.nextInt(101));
		}
		
		System.out.println("List: " + list.toString());
		SingleLinkedList.printReverse(list);
	}
}
