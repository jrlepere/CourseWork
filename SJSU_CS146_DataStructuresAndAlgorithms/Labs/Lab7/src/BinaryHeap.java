import java.util.Arrays;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public BinaryHeap(int capacity) {
		currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
	}
	
	public BinaryHeap(AnyType[] items) {
		 currentSize = items.length;
	     array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];
	       
	     int i = 1;
	     for(AnyType item : items) {
	    	 array[i++] = item;
	     }
	     buildHeap();
	}
	
	
	public void insert(AnyType x) {
		if (currentSize == array.length -1) {
			enlargeArray(array.length * 2 - 1);
		}
		
		//percolate Up
		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole/2]) < 0; hole /= 2) {
			array[hole] = array[hole/2];
		}
		array[hole] = x;
		array[0] = x;
	}
	
	public AnyType findMin() {
		if(isEmpty()) {
            throw new NullPointerException();
        } else {  
            return array[1];
        }
	}
	
	public AnyType deleteMin() {
		if (isEmpty()) {
			throw new NullPointerException();
		}
		
		AnyType minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}
	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public void makeEmpty() {
		currentSize = 0;
	}
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int currentSize; // Number of elements in heap
	private AnyType[] array; // The heap array
	
	private void percolateDown(int hole) { 
		int child = hole;
		AnyType tmp = array[hole];
		
		for ( ; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && array[child+1].compareTo(array[child]) < 0) {
				child ++;
			}
		}
		if (array[child].compareTo(tmp) < 0) {
			array[hole] = array[child];
		} else { 
			return;
		}
		
		array[hole] = tmp;
	}
	
	private void buildHeap() {
		for(int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
	}
	
	private void enlargeArray(int newSize) {
		AnyType[] old = array;
        array = (AnyType []) new Comparable[newSize];
        for(int i = 0; i < old.length; i++) {
            array[i] = old[i];        
        }
	}
	
	public static void main(String[] args) {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		heap.insert(12);
		heap.insert(1);
		heap.insert(14);
		heap.insert(6);
		heap.insert(5);
		heap.insert(8);
		heap.insert(15);
		heap.insert(3);
		heap.insert(9);
		heap.insert(7);
		heap.insert(4);
		heap.insert(11);
		heap.insert(13);
		heap.insert(2);
		
		System.out.println(Arrays.toString(heap.array));
		
		heap.deleteMin();
		
		System.out.println(Arrays.toString(heap.array));
	}
}
