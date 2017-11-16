import java.util.Iterator;
import java.util.NoSuchElementException;

/**
   This class implements a hash set using separate chaining.
*/
public class HashSet
{
   Node[] buckets;
   int currentSize;

   /**
      Constructs a hash table.
      @param bucketsLength the length of the buckets array
   */
   public HashSet(int bucketsLength)
   {
      buckets = new Node[bucketsLength];
      currentSize = 0;
   }

   /**
      Tests for set membership.
      @param x an object
      @return true if x is an element of this set
   */
   public boolean contains(Object x)
   {
      int h = x.hashCode();
      if (h < 0) { h = -h; }
      h = h % buckets.length;
      
      Node current = buckets[h];
      while (current != null)
      {
         if (current.data.equals(x)) { return true; }
         current = current.next;
      }
      return false;
   }

   /**
      Adds an element to this set.
      @param x an object
      @return the set with this element added
   */
   public HashSet add(Object x)
   {   
      // Draft
	  if (this.contains(x)) {
		  //Result already contains the given object
		  return this;
	  }
	  
	  //Forms the hashcode for this hashset
      int h = x.hashCode();
      if (h < 0) {h = -h;}
      h = h % buckets.length;
      
      //Creates a new hashset from this hashset
      HashSet result = new HashSet(buckets.length);
      result.buckets = this.buckets.clone();
      result.currentSize = this.currentSize;
      
      //Adds the new node to the new hashset
      Node newNode = new Node();
      newNode.data = x;
      newNode.next = result.buckets[h];
      result.buckets[h] = newNode;
      result.currentSize ++;
      
      return result;
   }

   /**
      Removes an object from this set.
      @param x an object
      @return the set with this element removed
   */
   public HashSet remove(Object x)
   {
      // Final
	   if (!contains(x)) {
		   return this;
	   }
	   
      int h = x.hashCode();
      if (h < 0) {h = -h;}
      h = h % buckets.length;
      
      HashSet result = new HashSet(buckets.length);
      for (int i = 0; i < buckets.length; i ++) {
    	  if (i != h) {
    		  result.buckets[i] = this.buckets[i];
    	  }
      }
      result.currentSize = this.currentSize;
      
      Node first = new Node();
      if (this.buckets[h].data.equals(x)) {
    	  first = buckets[h].next;
      } else {
    	  first.data = buckets[h].data;
    	  
    	  Node current = new Node();
    	  current = first;
    	  boolean end = false;
    	  for (Node n = this.buckets[h].next; n != null && !end; n = n.next) {
    		  if (n.data.equals(x)) {
    			  current.next = n.next;
    			  end = true;
    		  } else {
    			  Node newNode = new Node();
    			  newNode.data = n.data;
    			  current.next = newNode;
    			  current = current.next;
    		  }
    	  }
      }
      
      result.buckets[h] = first;
      result.currentSize --;
      
      return result;
   }

   /**
      Returns an iterator that traverses the elements of this set.
      @return a hash set iterator
   */
   public Iterator iterator()
   {
      return new HashSetIterator();
   }

   /**
      Gets the number of elements in this set.
      @return the number of elements
   */
   public int size()
   {
      return currentSize;
   }

   class Node
   {
      public Object data;
      public Node next;
   }

   class HashSetIterator implements Iterator
   {
      private int bucketIndex;
      private Node current;

      /**
         Constructs a hash set iterator that points to the
         first element of the hash set.
      */
      public HashSetIterator()
      {
         current = null;
         bucketIndex = -1;
      }
      
      public boolean hasNext()
      {
         if (current != null && current.next != null) { return true; }
         for (int b = bucketIndex + 1; b < buckets.length; b++)
         {
            if (buckets[b] != null) { return true; }
         }
         return false;
      }
       
      public Object next()
      {
         if (current != null && current.next != null) 
         {            
            current = current.next; // Move to next element in bucket
         }
         else // Move to next bucket
         {            
            do
            {
               bucketIndex++;
               if (bucketIndex == buckets.length) 
               {
                  throw new NoSuchElementException();
               }
               current = buckets[bucketIndex];
            }
            while (current == null);            
         }
         return current.data;
      }

      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
}
