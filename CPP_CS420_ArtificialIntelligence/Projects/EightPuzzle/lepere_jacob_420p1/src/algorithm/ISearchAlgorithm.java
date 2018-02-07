package algorithm;
import java.util.List;

import action.IAction;
import eight_puzzle.Problem;
import node.Node;

/**
 * A search algorithm interface
 * @author JLepere2
 * @date 01/25/2018
 */
public interface ISearchAlgorithm {
	
	/**
	 * Searches up from the found goal node for a list of actions to solve the problem.
	 * @param gaol the goal Node
	 * @return a list of actions
	 */
	public List<IAction> search(Node goal);
	
	/**
	 * Executes the algorithm.
	 * @param p the problem
	 * @return the goal node
	 */
	public ResultObject execute(Problem p);
}
