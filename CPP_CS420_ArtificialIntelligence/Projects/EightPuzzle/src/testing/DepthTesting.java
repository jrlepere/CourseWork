package testing;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import algorithm.AStar;
import algorithm.ISearchAlgorithm;
import eight_puzzle.Problem;

public class DepthTesting {
	
	public DepthTesting() {
		tests = new LinkedList<>();
		tests.add(new DepthExpectation(4, new int[]{3,1,0,4,5,2,6,7,8}));
		tests.add(new DepthExpectation(4, new int[]{3,2,0,4,1,5,6,7,8}));
		tests.add(new DepthExpectation(8, new int[]{4,3,1,5,0,2,6,7,8}));
		tests.add(new DepthExpectation(8, new int[]{3,2,5,4,0,8,6,1,7}));
		tests.add(new DepthExpectation(12, new int[]{5,4,1,6,3,2,0,7,8}));
		tests.add(new DepthExpectation(12, new int[]{3,5,8,4,0,2,6,1,7}));
		tests.add(new DepthExpectation(16, new int[]{5,4,1,6,0,3,7,8,2}));
		tests.add(new DepthExpectation(16, new int[]{3,5,8,6,0,2,1,4,7}));
		tests.add(new DepthExpectation(20, new int[]{5,4,1,7,6,3,8,2,0}));
		tests.add(new DepthExpectation(20, new int[]{5,8,0,3,6,2,1,4,7}));
	}
	
	@Test
	public void heuristicOneTest() {
		int heuristicNum = 1;
		Problem p;
		for (DepthExpectation test : tests) {
			p = new Problem(test.sequence, heuristicNum);
			assertEquals(test.depth, aStar.execute(p).getTotalDepth());
		}
	}
	
	@Test
	public void heuristicTwoTest() {
		int heuristicNum = 2;
		Problem p;
		for (DepthExpectation test : tests) {
			p = new Problem(test.sequence, heuristicNum);
			assertEquals(test.depth, aStar.execute(p).getTotalDepth());
		}
	}
	
	class DepthExpectation {
		public DepthExpectation(int depth, int[] sequence) {
			this.depth = depth;
			this.sequence = sequence;
		}
		
		private int depth;
		private int[] sequence;
	}
	
	private ISearchAlgorithm aStar = new AStar();
	private List<DepthExpectation> tests;

}