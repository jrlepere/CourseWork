import org.junit.*;
import static org.junit.Assert.*;

public class BagTest2
{
   @Test public void test1()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.remove("apple");
      assertEquals(0, bag.contains("apple"));
   }

   @Test public void test2()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("apple");
      bag.remove("apple");
      assertEquals(1, bag.contains("apple"));
   }

   @Test public void test3()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("apple");
      bag.add("pear");
      bag.remove("apple");
      assertEquals(1, bag.contains("apple"));
      assertEquals(1, bag.contains("pear"));
   }

   @Test public void test4()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("pear");
      bag.remove("apple");
      bag.remove("apple");
      assertEquals(0, bag.contains("apple"));
      assertEquals(1, bag.contains("pear"));
   }

   @Test public void test5()
   {
      Bag bag = new Bag();
      bag.add("apple");
      assertEquals("[apple]", bag.toString());
   }

   @Test public void test6()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("pear");
      assertEquals("[pear, apple]", bag.toString());
   }

   @Test public void test7()
   {
      Bag bag = new Bag();
      bag.add("tomato");
      bag.add("apple");
      bag.add("pear");
      bag.add("apple");
      bag.add("apple");
      bag.add("pear");
      assertEquals("[pear, pear, apple, apple, apple, tomato]", bag.toString());
   }

   @Test public void test8()
   {
      Bag bag = new Bag();
      assertEquals("[]", bag.toString());
   }

   @Test public void test9()
   {
      Bag bag = new Bag();
      bag.add("apple");
      bag.add("banana");
      bag.add("banana");
      bag.add("pear");
      bag.add("apple");
      bag.add("apple");
      bag.add("pear");
      bag.add("tomato");
      bag.remove("banana");
      bag.remove("banana");
      Bag.Node n = bag.first;
      assertEquals("tomato", n.data);
      assertEquals(1, n.count);
      assertNotNull(n.next);
      n = n.next;
      assertEquals("pear", n.data);
      assertEquals(2, n.count);
      assertNotNull(n.next);
      n = n.next;
      assertEquals("apple", n.data);
      assertEquals(3, n.count);
      assertNull(n.next);
   }
}