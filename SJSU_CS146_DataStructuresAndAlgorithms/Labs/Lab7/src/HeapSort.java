import java.util.Arrays;

public class HeapSort {
	
	public static <AnyType extends Comparable<? super AnyType>> void heapsortDec( AnyType[] a) {
		
		for (int i = a.length/2-1; i>=0; i--) {
			percDown(a, i, a.length);
		}
					
		for (int i = a.length - 1; i > 0; i --) {
			AnyType holder = a[i];
			a[i] = a[0];
			a[0] = holder;
			percDown(a,0,i);
		}
	}
	
	private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
		
		int child;
		AnyType tmp;
		
		for(tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n-1 && a[child].compareTo(a[child+1]) > 0) {
				child ++;
			}
			if (tmp.compareTo(a[child]) > 0) {
				a[i] = a[child];
			} else {
				a[i] = tmp;
				return;
			}
		}
		a[i] = tmp;
	}
	
	private static int leftChild(int i) {
		return 2*i+1;
	}
	
	public static void main(String[] args) {
		Integer[] a = {142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102};
		heapsortDec(a);
		System.out.println(Arrays.toString(a));
	}
}
