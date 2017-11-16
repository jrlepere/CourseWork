import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class SelectionTest
{
   @Test
   public void testStringMinPos()
   {
      String[] strings = {"Mary", "had", "a", "little", "lamb"};
      Comparator<String> comp = (s, t) -> s.compareTo(t);
      // Java 8 shortcut for an object of a class that implements 
      // Comparator and whose compare method has parameters s, t 
      // and whose body is { return s.compareTo(t) }

      int p = SelectionSorter.minimumPosition(strings, 1, comp);
      assertEquals(2, p);
      p = SelectionSorter.minimumPosition(strings, 0, comp);
      assertEquals(0, p);
      p = SelectionSorter.minimumPosition(strings, 3, comp);
      assertEquals(4, p);
   }

   @Test 
   public void testStringLengthMinPos()
   {
      String[] strings = {"Mary", "had", "a", "little", "lamb"};
      Comparator<String> comp = (s, t) -> s.length() - t.length();
      int p = SelectionSorter.minimumPosition(strings, 0, comp);
      assertEquals(2, p);
   }

   @Test 
   public void testIntegerMinPos()
   {
      Integer[] ints = { new Integer(7), new Integer(3), new Integer(8), new Integer(2) };
      Comparator<Integer> comp = (a, b) -> Integer.compare(a, b);
      int p = SelectionSorter.minimumPosition(ints, 0, comp);
      assertEquals(3, p);
   }
}
