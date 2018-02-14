import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import problem.Action;
import problem.Problem;
import problem.State;

/**
 * A project class for the N Queens problem
 * @author JLepere2
 * @date 02/06/2018
 */
public class NQueensProblem implements Problem {

	/**
	 * Creates an instance of the N Queens problem
	 * @param n the size of the board
	 */
	public NQueensProblem(int n) {
		this.n = n;
	}
	
	public State getInitialState() {
		int[] queenLocations = new int[n];
		for (int i = 0; i < n; i ++) {
			queenLocations[i] = -1;
		}
		for (int i = 0; i < n; i ++) {
			int v = gen.nextInt(n);
			boolean valid = true;
			for (int j = i + 1; j < n; j ++) {
				if (queenLocations[j] == v) {
					valid = false;
				}
			}
			if (valid) queenLocations[i] = v;
			else i --;
		}
		return new NQueensState(queenLocations);
	}

	public List<Action> getActions() {
		List<Action> actions = new LinkedList<>();
		for (int r = 0; r < n; r ++) {
			for (int c = 0; c < n; c ++) {
				actions.add(new NQueensAction(r,c));
			}
		}
		return actions;
	}

	public boolean isGoalState(State s) {
		int[] queenLocations = ((NQueensState) s).getQueenLocations();
		for (int row = 0; row < n; row ++) {
			int diagOffset = 1;
			for (int col = row + 1; col < n; col ++) {
				if (queenLocations[row] == queenLocations[col]) return false;
				else if (queenLocations[row] == queenLocations[col] + diagOffset) return false;
				else if (queenLocations[row] == queenLocations[col] - diagOffset) return false;
				diagOffset += 1;
			}
		}
		return true;
	}
	
	Random gen = new Random();
	private int n;

}
