import java.util.Arrays;

import problem.Action;
import problem.State;

/**
 * An Action class for the N Queens problem
 * @author JLepere2
 * @date 02/06/2018
 */
public class NQueensAction implements Action {

	public NQueensAction(int queenRow, int newQueenColumn) {
		this.queenRow = queenRow;
		this.newQueenColumn = newQueenColumn;
	}
	
	public boolean canExecute(State s) {
		return ((NQueensState) s).getQueenLocations()[this.queenRow] != this.newQueenColumn;
	}

	public State execute(State s) {
		int[] originalQueenLocations = ((NQueensState) s).getQueenLocations();
		int[] newQueenLocations = Arrays.copyOf(originalQueenLocations, originalQueenLocations.length);
		newQueenLocations[this.queenRow] = newQueenColumn;
		return new NQueensState(newQueenLocations);
	}

	public int getCost() {
		return 1;
	}
	
	private int queenRow;
	private int newQueenColumn;

}
