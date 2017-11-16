import static org.junit.Assert.*;

import org.junit.Test;

public class GreeterTest {

	@Test
	public void test() {
		Greeter worldGreeter = new Greeter("World");
		Greeter jakeGreeter = new Greeter("World");
		jakeGreeter.setNameTo(worldGreeter);
		assertEquals(jakeGreeter.getName(),worldGreeter.getName());
	}

}
