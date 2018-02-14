import java.util.Arrays;

import problem.State;

/**
 * A State for the N Queens problem
 * @author JLepere2
 * @date 02/06/2018
 */
public class NQueensState implements State {

	public NQueensState(int[] queenLocations) {
		this.queenLocations = queenLocations;
	}
	
	public int[] getQueenLocations() {
		return queenLocations;
	}
	
	public boolean equals(Object o) {
		return this.queenLocations.equals(((NQueensState) o).queenLocations); 
	}
	
	public String toString() {
		return Arrays.toString(queenLocations);
	}
	
	private int[] queenLocations;
	
}
