import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeMap;

public class Set
{
   TreeMap<Integer, LinkedList<Object>> buckets;
   // No other instance variables

   /**
      Constructs an empty set.
   */
   public Set()
   {
      buckets = new TreeMap<>();
   }

   /**
      Tests for set membership.
      @param x an object
      @return true if x is an element of this set
   */
   public boolean contains(Object x)
   {
	   int h = x.hashCode();
	   if (buckets.get(h) != null) {
		   LinkedList<Object> list = buckets.get(h);
		   for (Object obj : list) {
			   if (x.equals(obj)) {
				   return true;
			   }
		   }
	   }
	   return false;
   }

   /**
      Adds an element to this set.
      @param x an object
      @return true if x is a new object, false if x was
      already in the set
   */
   public boolean add(Object x)
   {
	   int h = x.hashCode();
	   if (buckets.get(h) == null) {
		   //No list yet for the hashcode
		   LinkedList<Object> list = new LinkedList<>();
		   list.add(x);
		   buckets.put(h, list);
	   } else {
		   LinkedList<Object> list = buckets.get(h);
		   for (Object obj : list) {
			   if (obj.equals(x)) {
				   //x already in the set
				   return false;
			   }
		   }
		   list.addFirst(x);
		   buckets.put(h, list);
	   }
	   return true;
   }

   /**
      Gets the number of elements in this set.
      @return the number of elements
   */
   public int size()
   {
	   int size = 0;
	   for (Integer n : buckets.keySet()) {
		   size += buckets.get(n).size();
	   }
	   return size;
   }

   /**
      Removes an object from this set.
      @param x an object
      @return true if x was removed from this set, false
      if x was not an element of this set
   */
   public boolean remove(Object x)
   {
	   if (contains(x)) {
		   int h = x.hashCode();
		   LinkedList<Object> list = buckets.get(h);
		   list.remove(x);
		   if (list.isEmpty()) {
			   buckets.remove(h);
		   }
		   return true;
	   }
	   return false;
      // final
   }

   /**
      Returns an iterator that traverses the elements of this set.
      @return a hash set iterator
   */
   public Iterator iterator()
   {
      return new SetIterator();
   }

   class SetIterator implements Iterator
   {
      private Iterator<LinkedList<Object>> bucketIterator;
      private LinkedList<Object> currentBucket;
      private ListIterator<Object> current; 
      // No other instance variables
      
      /**
         Constructs an iterator that points to the
         first element of the set.
      */
      public SetIterator()
      {
         bucketIterator = buckets.values().iterator();
         currentBucket = bucketIterator.next();
         current = currentBucket.listIterator();
      }

      public boolean hasNext()
      {
    	  if (current.hasNext()) {
    		  return true;
    	  }
          return bucketIterator.hasNext();
      }
       
      public Object next()
      {
    	  if (hasNext()) {
    		  if (current.hasNext()) {
    			  return current.next();
    		  }
    		  //Advance to the next bucket
    		  currentBucket = bucketIterator.next();
    		  current = currentBucket.listIterator();
    		  return current.next();
    	  }
    	  return null;
      }

      public void remove()
      {
    	  if (hasNext()) {
    		  current.remove();
    		  if (currentBucket.isEmpty()) {
    			  bucketIterator.remove();
    		  }
    	  } else {
    		  throw new IllegalStateException();
    	  }
      }
   }
}