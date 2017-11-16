import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class HashSetTest2
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
      HashSet set5 = set4.remove(new Box(1));

      Iterator iter = set5.iterator();
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
      HashSet set5 = set4.remove(new Box(3));

      Iterator iter = set5.iterator();
      assertTrue(iter.hasNext());
      assertEquals(new Box(1), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(13), iter.next());
      assertFalse(iter.hasNext());      
      assertEquals(3, set4.size());

   }

   @Test public void test3()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.remove(new Box(13));

      Iterator iter = set5.iterator();
      assertTrue(iter.hasNext());
      assertEquals(new Box(1), iter.next());
      assertTrue(iter.hasNext());
      assertEquals(new Box(3), iter.next());
      assertFalse(iter.hasNext());      
      assertEquals(3, set4.size());
   }

   @Test public void test4()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.remove(new Box(1));

      assertEquals(set5.buckets[3], set4.buckets[3]);
      assertNull(set5.buckets[1]);
   }

   @Test public void test5()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.remove(new Box(3));

      assertEquals(set5.buckets[1], set4.buckets[1]);
      assertFalse(set5.buckets[3] == set4.buckets[3]);
   }

   @Test public void test6()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.remove(new Box(13));

      assertEquals(set5.buckets[1], set4.buckets[1]);
      assertEquals(set5.buckets[3], set4.buckets[3].next);
   }

   @Test public void test7()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.add(new Box(1));
      assertEquals(set4, set5);
   }

   @Test public void test8()
   {
      HashSet set = new HashSet(10);
      HashSet set2 = set.add(new Box(1));
      HashSet set3 = set2.add(new Box(3));
      HashSet set4 = set3.add(new Box(13));
      HashSet set5 = set4.remove(new Box(14));
      assertEquals(set4, set5);
   }

   @Test public void test9()
   {
      HashSet set1 = new HashSet(10);
      for (int i = 0; i < 100; i++)
         set1 = set1.add(new Box(i));

      /*
        [0]: 90 80 70 60 50 40 30 20 10 0
        [1]: 91 81 71 61 51 41 31 21 11 1
        [2]: 92 82 72 62 52 42 32 22 12 2
        ...
        [9]: 99 89 79 69 59 49 39 29 19 9
      */
      HashSet set2 = set1;
      for (int i = 0; i < 100; i += 11)
         set2 = set2.remove(new Box(i));

      /*
        [0]: new 90 80 70 60 50 40 30 20 10
        [1]: new 91 81 71 61 51 41 31 21 shared 1
        [2]: new 92 82 72 62 52 42 32 shared 12 2
        ...
        [9]: shared 89 79 69 59 49 39 29 19 9

        1 + 2 + ... + 9 = 45 shared nodes
      */

      int shared = 0;
      java.util.HashSet<HashSet.Node> nodes = new java.util.HashSet<>();
      for (int i = 0; i < set1.buckets.length; i++)
         for (HashSet.Node n = set1.buckets[i]; n != null; n = n.next)
            nodes.add(n);
      for (int i = 0; i < set2.buckets.length; i++)
         for (HashSet.Node n = set2.buckets[i]; n != null; n = n.next)
            if (nodes.contains(n)) shared++;
      assertEquals(45, shared);
   }
}

