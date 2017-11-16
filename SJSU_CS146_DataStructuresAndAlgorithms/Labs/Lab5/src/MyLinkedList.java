import java.util.Iterator;

public class MyLinkedList<AnyType> {
	/*
	 * Provide an implementation of a removeAll method for the MyLinkedList class. 
	 * Method removeAll removes all items in the specified collection given by items 
	 * from the MyLinkedList. Also provide the running time of your implementation. 
	 * The method signature for you to use is slightly different than the one in 
	 * the Java Collections API, and is as follows: 
	 * public void removeAll( Iterable<? extends AnyType> items )
	 */
	public void removeAll(Iterable<? extends AnyType> items) {
		LinkedListIterator<AnyType> iter = iterator();
		while (iter.hasNext()) {
			AnyType value = iter.next();
			Iterator<? extends AnyType> itemIter = items.iterator();
			while (itemIter.hasNext()) {
				AnyType item = itemIter.next();
				if (item.equals(value)) {
					iter.remove();
				}
			}
		}
	}
}
