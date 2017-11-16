import static org.junit.Assert.*;
import org.junit.*;

public class MyBagTest {

	@Test public void test1()
	{
		Bag bag = new Bag();
		assertEquals(0, bag.contains("apple"));
	}

}
