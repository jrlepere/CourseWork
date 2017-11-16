import java.util.ArrayList;
import java.util.Random;

public class DoubleSidedStack<T> {
	
	private T[] buffer;
	private int size1;
	private int size2;
	
	public DoubleSidedStack() {
		buffer = (T[]) new Object[10];
		size1 = 0;
		size2 = 0;
	}
	
	/**
	 * Pushes an element of type T to the first stack
	 * @param element the element to push
	 */
	public void push1(T element) {
		checkForResize();
		buffer[size1] = element;
		size1 ++;
	}
	
	/**
	 * Pushes an element of type T to the second stack
	 * @param element the element to push
	 */
	public void push2(T element) {
		checkForResize();
		buffer[buffer.length - (size2 + 1)] = element;
		size2 ++;
	}
	
	/**
	 * Peeks the next element to be popped on the first stack
	 * @return the element to next be popped on the first stack
	 */
	public T peek1() {
		return buffer[size1];
	}
	
	/**
	 * Peeks the next element to be popped on the second stack
	 * @return the element to next be popped on the second stack
	 */
	public T peek2() {
		return buffer[buffer.length - (size2 + 1)];
	}
	
	/**
	 * Pops the first stack
	 * @return the element on the top of the first stack
	 */
	public T pop1() {
		if (size1 <= 0) {
			throw new IndexOutOfBoundsException();
		}
		size1 --;
		T element1 = buffer[size1];
		buffer[size1] = null;
		return element1;
	}
	
	/**
	 * Pops the second stack
	 * @return the element on the top of the second stack
	 */
	public T pop2() {
		if (size2 <= 0) {
			throw new IndexOutOfBoundsException();
		}
		size2 --;
		T element2 = buffer[buffer.length - (1 + size2)];
		buffer[buffer.length - (1 + size2)] = null;
		return element2;
	}
	
	/**
	 * Resizes the buffer
	 */
	private void resize() {
		T[] newBuffer = (T[]) new Object[buffer.length * 2];
		for (int i = 0; i < size1; i ++) {
			newBuffer[i] = buffer[i];
		}
		for (int i = buffer.length - 1; i > buffer.length - (size2+ 1); i --) {
			newBuffer[buffer.length + i] = buffer[i];
		}
		
		buffer = newBuffer;
	}
	
	private void checkForResize() {
		if ((size1 + size2) == (buffer.length)) {
			resize();
		}
	}
	
	public String toString() {
		String result = "[";
		for (int i = 0; i < buffer.length; i ++) {
			if (i != buffer.length - 1) {
				if (buffer[i] != null) {
					result += buffer[i].toString() + ", ";
				} else {
					result += "null, ";
				}
			} else {
				if (buffer[i] != null) {
					result += buffer[i].toString();
				} else {
					result += "null";
				}
			}
		}
		result += "]";
		return result;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		
		ArrayList<Integer> first = new ArrayList<>();
		ArrayList<Integer> second = new ArrayList<>();
		
		int maxFirst = rand.nextInt(10) + 5;
		int maxSecond = rand.nextInt(10) + 5;
		
		for (int i = 0; i < maxFirst; i ++) {
			first.add(rand.nextInt(1000));
		}
		
		for (int i = 0; i < maxSecond; i ++) {
			second.add(rand.nextInt(1000));
		}
		
		System.out.println("First List: " + first.toString());
		System.out.println("Second List: " + second.toString());
		
		DoubleSidedStack<Integer> dStack = new DoubleSidedStack<>();
		for (int i = 0; i < first.size() || i < second.size(); i ++) {
			if (i < first.size() && i < second.size()) {
				dStack.push1(first.get(i));
				dStack.push2(second.get(i));
			} else if (i < first.size()) {
				dStack.push1(first.get(i));
			} else {
				dStack.push2(second.get(i));
			}
		}
		
		System.out.println("Stack: " + dStack.toString());
		
		System.out.print("Popped first: ");
		for (int i = 0; i < first.size(); i ++) {
			System.out.print(dStack.pop1() + " ");
		}
		System.out.println();
		System.out.print("Popped second: ");
		for (int i = 0; i < second.size(); i ++) {
			System.out.print(dStack.pop2() + " ");
		}
	}
}
