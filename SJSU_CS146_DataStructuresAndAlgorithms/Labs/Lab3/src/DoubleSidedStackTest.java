import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class DoubleSidedStackTest {

	@Test
	public void test1() {
		DoubleSidedStack<Integer> dStack = new DoubleSidedStack<>();
		dStack.push1(12);
		dStack.push1(4);
		dStack.push1(-9);
		dStack.push2(9);
		assertEquals(new Integer(-9), dStack.pop1());
		assertEquals(new Integer(4), dStack.pop1());
		assertEquals(new Integer(12), dStack.pop1());
		//assertNull(dStack.pop1());
		assertEquals(new Integer(9), dStack.pop2());
		//assertNull(dStack.pop2());
	}

}
