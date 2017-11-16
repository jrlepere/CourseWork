import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class Test2
{
   @Test public void testRemove1()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      //assertTrue(set.remove(new Box(1)));
      Box x = new Box(1);
      set.remove(x);
      assertEquals("{2=[12]}", set.buckets.toString());
      assertEquals(1, set.size());
      assertTrue(set.contains(new Box(12)));
      assertFalse(set.contains(new Box(1)));
      assertFalse(set.remove(new Box(1)));
   }

   @Test public void testRemove2()
   {
      Set set = new Set();
      set.add(new Box(11));
      set.add(new Box(12));
      assertTrue(set.remove(new Box(11)));
      assertEquals("{2=[12]}", set.buckets.toString());
      assertEquals(1, set.size());
      assertTrue(set.contains(new Box(12)));
      assertFalse(set.contains(new Box(11)));
      assertTrue(set.remove(new Box(12)));
      assertEquals("{}", set.buckets.toString());
      assertFalse(set.remove(new Box(12)));
   }

   @Test public void testIteratorNext()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      set.add(new Box(22));
      set.add(new Box(14));
      set.add(new Box(24));
      assertEquals("{1=[1], 2=[22, 12], 4=[24, 14]}", set.buckets.toString());
      Iterator<Object> iter = set.iterator();
      assertTrue(iter.hasNext());
      assertEquals(new Box(1), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(22), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(12), iter.next());
      assertEquals(new Box(24), iter.next());
      assertEquals(new Box(14), iter.next());
      assertFalse(iter.hasNext());
   }

   @Test(expected=NoSuchElementException.class) public void testIteratorEmpty()
   {
      Set set = new Set();
      Iterator<Object> iter = set.iterator();
      assertFalse(iter.hasNext());
      iter.next();
   }

   @Test public void testIteratorRemove1()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      set.add(new Box(22));
      set.add(new Box(14));
      set.add(new Box(24));
      assertEquals("{1=[1], 2=[22, 12], 4=[24, 14]}", set.buckets.toString());
      Iterator<Object> iter = set.iterator();
      iter.next(); // 1
      iter.next(); // 22
      iter.remove();
      assertEquals("{1=[1], 2=[12], 4=[24, 14]}", set.buckets.toString());
   }

   @Test public void testIteratorRemove2()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      set.add(new Box(22));
      set.add(new Box(14));
      set.add(new Box(24));
      assertEquals("{1=[1], 2=[22, 12], 4=[24, 14]}", set.buckets.toString());
      Iterator<Object> iter = set.iterator();
      iter.next(); // 1
      iter.next(); // 22
      iter.remove();
      iter.next(); // 12
      iter.remove();
      assertEquals("{1=[1], 4=[24, 14]}", set.buckets.toString());
   }

   @Test(expected=IllegalStateException.class) public void testIteratorRemove3()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      set.add(new Box(22));
      set.add(new Box(14));
      set.add(new Box(24));
      assertEquals("{1=[1], 2=[22, 12], 4=[24, 14]}", set.buckets.toString());
      Iterator<Object> iter = set.iterator();
      iter.next(); // 1
      iter.next(); // 22
      iter.remove();
      iter.remove();
   }
}