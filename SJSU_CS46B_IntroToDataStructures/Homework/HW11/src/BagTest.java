import org.junit.*;
import static org.junit.Assert.*;

public class BagTest
{
   @Test public void test1()
   {
      Bag bag = new Bag();
      bag.add("apple");
      assertEquals(1, bag.contains("apple"));
      assertEquals(0, bag.contains("tomato"));
   }

   @Test public void test2()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("pear");
      bag.add("apple");
      assertEquals(2, bag.contains("apple"));
      assertEquals(1, bag.contains("pear"));
      assertEquals(0, bag.contains("tomato"));
   }

   @Test public void test3()
   {
      Bag bag = new Bag();
      bag.add("tomato");
      bag.add("apple");
      bag.add("pear");
      bag.add("apple");
      bag.add("apple");
      bag.add("pear");
      Bag.Node n = bag.first;
      assertEquals("pear", n.data);
      assertEquals(2, n.count);
      assertNotNull(n.next);
      n = n.next;
      assertEquals("apple", n.data);
      assertEquals(3, n.count);
      assertNotNull(n.next);
      n = n.next;
      assertEquals("tomato", n.data);
      assertEquals(1, n.count);
      assertNull(n.next);
   }
}