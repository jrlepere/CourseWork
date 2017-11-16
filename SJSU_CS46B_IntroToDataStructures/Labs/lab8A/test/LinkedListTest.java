import static org.junit.Assert.*;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedListTest 
{
   private LinkedList sampleList;   

   @BeforeClass
   public static void start() 
   {
      Logger.global.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
   }

   @Before
   public void setUp() 
   {
      sampleList = new LinkedList();
      sampleList.addFirst("lamb");
      sampleList.addFirst("little");
      sampleList.addFirst("a");
      sampleList.addFirst("had");
      sampleList.addFirst("Mary");
   }

   @Test
   public void testGet() 
   {
      Object result = "little";
      assertEquals(result, sampleList.get(3));
   }
   
   @Test(expected=IndexOutOfBoundsException.class) public void outOfBoundsLess() {
       sampleList.get(-1);
   }
   
   @Test(expected=IndexOutOfBoundsException.class) public void outOfBoundsMore() {
       sampleList.get(6);
    }
   
   @Test
   public void addTest()
   {
	   sampleList.add(4, "lazy");
	   assertEquals("little", sampleList.get(3));
	   assertEquals("lazy", sampleList.get(4));
	   assertEquals("lamb", sampleList.get(5));
   }
   
   @Test
   public void addZero()
   {
	   sampleList.add(0, "Mrs.");
	   assertEquals("Mrs.", sampleList.get(0));
   }
}
