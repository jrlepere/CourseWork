import local_search.ObjectiveFunction;
import problem.State;

/**
 * Objective Function implementation for the NQueens problem
 * @author JLepere2
 * @date 02/07/2018
 */
public class NQueensObjectiveFunction implements ObjectiveFunction {
	
	/**
	 * Creates an NQueensObjectiveFunction object.
	 * @param n the size of the NQueens problem
	 */
	public NQueensObjectiveFunction(int n) {
		this.n = n;
		this.maxErrors = ((n-1)*(n))/2;
	}
	
	public int execute(State s) {
		int errorCount = 0;
		int[] queenLocations = ((NQueensState) s).getQueenLocations();
		for (int row = 0; row < n; row ++) {
			int diagOffset = 1;
			for (int col = row + 1; col < n; col ++) {
				if (queenLocations[row] == queenLocations[col]) errorCount ++;
				else if (queenLocations[row] == queenLocations[col] + diagOffset) errorCount ++;
				else if (queenLocations[row] == queenLocations[col] - diagOffset) errorCount ++;
				diagOffset += 1;
			}
		}
		return maxErrors - errorCount;
	}
	
	private int n;
	private int maxErrors;
}
