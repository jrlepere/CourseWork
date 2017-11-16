import org.junit.*;
import static org.junit.Assert.*;

public class Test1
{
   class Box
   {
      int n;
      public Box(int n) { this.n = n; }
      public int hashCode() { return Math.abs(n) % 10; }
      public boolean equals(Object otherObject) 
      { 
         return otherObject != null 
            && otherObject.getClass() == Box.class
            && n == ((Box) otherObject).n; 
      }
      public String toString() { return "" + n; }
   }

   @Test public void test1()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(12));
      assertEquals("{1=[1], 2=[12]}", set.buckets.toString());
      assertEquals(2, set.size());
      assertTrue(set.contains(new Box(12)));
      assertFalse(set.contains(new Box(2)));
   }

   @Test public void test2()
   {
      Set set = new Set();
      set.add(new Box(1));
      set.add(new Box(11));
      assertEquals("{1=[11, 1]}", set.buckets.toString());
      assertEquals(2, set.size());
      assertTrue(set.contains(new Box(1)));
      assertTrue(set.contains(new Box(11)));
      assertFalse(set.contains(12));
   }

   @Test public void test3()
   {
      Set set = new Set();
      set.add(1);
      set.add(1);
      set.add(12);
      assertEquals("{1=[1], 12=[12]}", set.buckets.toString());
   }
}