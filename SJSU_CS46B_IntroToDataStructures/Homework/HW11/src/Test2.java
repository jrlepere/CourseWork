import org.junit.*;
import static org.junit.Assert.*;

public class Test2
{
   @Test public void test1()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");

      LinkedList.Pointer p = names.listPointer();
      while (p.hasNext()) p.next();
      p.previous();
      assertEquals("Mary", p.get());
      assertTrue(p.hasPrevious());
      p.previous();
      assertEquals("Paul", p.get());
      assertTrue(p.hasPrevious());
      p.previous();
      assertEquals("Peter", p.get());
      assertFalse(p.hasPrevious());
   }

   @Test public void test2()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.next();
      p.add("Tom");
      p = names.listPointer();
      assertEquals("Peter", p.get());
      p.next();
      assertEquals("Tom", p.get());
      p.next();
      assertEquals("Paul", p.get());
      p.next();
      assertEquals("Mary", p.get());      
      p.previous();
      assertEquals("Paul", p.get());
      p.previous();
      assertEquals("Tom", p.get());
      p.previous();
      assertEquals("Peter", p.get());
   }

   @Test public void test3()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.add("Tom");
      p = names.listPointer();
      assertEquals("Tom", p.get());
      p.next();
      assertEquals("Peter", p.get());
      p.next();
      assertEquals("Paul", p.get());
      p.next();
      assertEquals("Mary", p.get());      
      p.previous();
      assertEquals("Paul", p.get());
      p.previous();
      assertEquals("Peter", p.get());
      p.previous();
      assertEquals("Tom", p.get());
   }

   @Test public void test4()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.next();
      p.next();
      p.next();
      p.add("Tom");
      p = names.listPointer();
      assertEquals("Peter", p.get());
      p.next();
      assertEquals("Paul", p.get());
      p.next();
      assertEquals("Mary", p.get());      
      p.next();
      assertEquals("Tom", p.get());
      p.previous();
      assertEquals("Mary", p.get());
      p.previous();
      assertEquals("Paul", p.get());
      p.previous();
      assertEquals("Peter", p.get());
   }

   @Test public void test5()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.remove();
      assertEquals("Paul", names.getFirst());
      p = names.listPointer();
      p.next();
      assertEquals("Mary", p.get());      
      p.previous();
      assertEquals("Paul", p.get());
   }

   @Test public void test6()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.next();
      p.remove();
      p = names.listPointer();
      assertEquals("Peter", p.get());
      p.next();
      assertEquals("Mary", p.get());      
      p.previous();
      assertEquals("Peter", p.get());
   }

   @Test public void test7()
   {
      LinkedList names = new LinkedList();
      names.addLast("Peter");
      names.addLast("Paul");
      names.addLast("Mary");
      LinkedList.Pointer p = names.listPointer();
      p.remove();
      p.next();
      p.remove();
      assertEquals("Paul", names.getFirst());
      assertEquals("Paul", names.getLast());
   }
}
