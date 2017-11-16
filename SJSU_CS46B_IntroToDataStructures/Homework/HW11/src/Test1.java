import java.util.NoSuchElementException;

import org.junit.*;
import static org.junit.Assert.*;

public class Test1
{
   @Test public void test1()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");

      LinkedList.Pointer p = names.listPointer();
      assertEquals("Peter", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertEquals("Paul", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertEquals("Mary", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertFalse(p.hasNext());
   }

   @Test public void test2()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.next();
      p.set("Tom");

      p = names.listPointer();
      assertEquals("Peter", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertEquals("Tom", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertEquals("Mary", p.get());
      assertTrue(p.hasNext());
      p.next();
      assertFalse(p.hasNext());
   }

   @Test(expected=NoSuchElementException.class) public void test3()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.next();
      p.next();
      assertTrue(p.hasNext());
      p.next();
      p.set("Fred");
   }
}
