import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class BagTest2
{
   @Test public void testRemoveSolitary()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(3);

      Iterator iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(3, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      assertTrue(iter.hasNext());
      iter.remove();
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
      iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(3, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }

   @Test public void testRemoveRepeated()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);

      Iterator iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(3, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      iter.remove();
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
      iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(3, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }

   @Test public void removeFirst()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      

      Iterator iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(3, iter.next());
      iter.remove();
      iter = bag.iterator();
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(2, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }

   @Test public void removeLast()
   {
      Bag bag = new Bag();
      bag.add(3);
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(1);      

      Iterator iter = bag.iterator();
      assertEquals(2, iter.next());
      assertEquals(2, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertEquals(3, iter.next());
      iter.remove();
      iter = bag.iterator();
      assertEquals(2, iter.next());
      assertEquals(2, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }

   @Test(expected=IllegalStateException.class) public void callRemoveTwice()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      

      Iterator iter = bag.iterator();
      iter.next();
      iter.next();
      iter.remove();
      iter.remove();
   }

   @Test(expected=NoSuchElementException.class) public void callNextAtEnd()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      

      Iterator iter = bag.iterator();
      assertEquals(3, iter.next());
      assertEquals(2, iter.next());
      assertEquals(2, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
      iter.next(); // Should throw exception
   }

   @Test(expected=ConcurrentModificationException.class) public void concurrentMod1()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      
   
      Iterator iter = bag.iterator();
      assertEquals(3, iter.next());
      bag.add(4);
      iter.next(); 
   }   

   @Test(expected=ConcurrentModificationException.class) public void concurrentMod2()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      
   
      Iterator iter = bag.iterator();
      assertEquals(3, iter.next());
      bag.add(4);
      iter.remove(); 
   }   

   @Test(expected=ConcurrentModificationException.class) public void concurrentMod3()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      
   
      Iterator iter = bag.iterator();
      Iterator iter2 = bag.iterator();
      assertEquals(3, iter.next());
      assertEquals(3, iter2.next());
      iter.remove();
      iter2.next(); 
   }   

   @Test(expected=ConcurrentModificationException.class) public void concurrentMod4()
   {
      Bag bag = new Bag();
      bag.add(1);
      bag.add(2);
      bag.add(1);
      bag.add(2);
      bag.add(3);
      bag.add(1);      
   
      Iterator iter = bag.iterator();
      Iterator iter2 = bag.iterator();
      assertEquals(3, iter.next());
      assertEquals(3, iter2.next());
      iter.remove();
      iter2.remove(); 
   }   
   
}