import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class BagTest1
{
   @Test public void test1()
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
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }

   @Test public void test2()
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
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertTrue(iter.hasNext());
      assertEquals(1, iter.next());
      assertFalse(iter.hasNext());
   }
}