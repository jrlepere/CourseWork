import java.util.Arrays;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays.*;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LinkedListTest 
{
   private LinkedList sampleList;   

   @BeforeClass
   public static void start() 
   {
      Logger.global.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
   }

   @Before
   public void setUp() 
   {
      sampleList = new LinkedList();
      sampleList.addLast("Mary");
      sampleList.addLast("had");
      sampleList.addLast("a");
      sampleList.addLast("little");
      sampleList.addLast("lamb");
   }

   @Test
   public void removeInMiddle() 
   {
      ListIterator iter = sampleList.listIterator();
      iter.next();
      iter.next();
      iter.next();
      iter.remove();
      assertTrue(hasElements(sampleList, "Mary had little lamb"));
   }

   @Test
   public void removeHead() 
   {
      ListIterator iter = sampleList.listIterator();
      iter.next();
      iter.remove();
      assertTrue(hasElements(sampleList, "had a little lamb"));
   }

   @Test
   public void removeTail() 
   {
      ListIterator iter = sampleList.listIterator();
      iter.next();
      iter.next();
      iter.next();
      iter.next();
      iter.next();
      iter.remove();
      assertTrue(hasElements(sampleList, "Mary had a little"));
      assertEquals("little", sampleList.getLast());
   }

   /**
    * Helper methods to check whether a LinkedList of strings has a given contents
    * @param lst a linked list
    * @param elements the expected string, separated by spaces
    * @return true iff the list contains exactly the given elements in the same order and no others
    */
   public boolean hasElements(LinkedList lst, String expected) 
   {
      String[] elements = expected.split(" ");
      ListIterator iter = lst.listIterator();
      int count = 0;
      while (iter.hasNext()) 
      {
         if (count == elements.length) return false;
         if (!iter.next().equals(elements[count])) return false;
         count++;
      }
      return count == elements.length;
   }
}
