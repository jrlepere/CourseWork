import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class HashSetTest1
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
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));

      Iterator iter = set4.iterator();
      assertTrue(iter.hasNext());
      assertEquals(new Box(1), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(13), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(3), iter.next());
      assertFalse(iter.hasNext());      
      assertEquals(3, set4.size());
   }

   @Test public void test2()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));

      assertEquals(set3.buckets[1], set2.buckets[1]);
      assertEquals(set4.buckets[3].next, set3.buckets[3]);
   }
}