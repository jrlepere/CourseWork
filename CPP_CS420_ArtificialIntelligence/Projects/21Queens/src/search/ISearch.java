package search;

import problem.Problem;

/**
 * A search algorithm interface
 * @author JLepere2
 * @date 02/01/2018
 */
public interface ISearch {
	
	/**
	 * Executes the search algorithm on the passed Problem.
	 * @param p the Problem p
	 * @return the result of executing the search algorithm
	 */
	public IResultObject execute(Problem p);
	
}
