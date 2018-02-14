package local_search;

import problem.State;

/**
 * An interface to implement an objective function for local search.
 * @author JLepere2
 * 02/01/2018
 */
public interface ObjectiveFunction {
	
	public int execute(State s);
	
}
