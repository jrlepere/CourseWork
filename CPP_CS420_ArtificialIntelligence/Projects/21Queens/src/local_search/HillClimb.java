package local_search;

import java.util.Comparator;
import java.util.PriorityQueue;

import problem.Action;
import problem.Problem;
import problem.State;
import search.IResultObject;
import search.LocalSearch;

/**
 * Local search Hill Climb algorithm.
 * @author JLepere2
 * @date 02/01/2018
 */
public class HillClimb extends LocalSearch {

	public HillClimb(ObjectiveFunction function) {
		super(function);
		this.searchCost = 0;
	}
	
	public IResultObject execute(Problem p) {
		State currentState = p.getInitialState();
		searchCost ++;
		while (true) {
			PriorityQueue<State> successors = new PriorityQueue<>(new Comparator<State>() {
				public int compare(State o1, State o2) {
					return function.execute(o2) - function.execute(o1);
				}
			});
			for (Action action : p.getActions()) {
				if (!action.canExecute(currentState)) continue;
				successors.add(action.execute(currentState));
				searchCost ++;
			}
			if (successors.size() == 0 || (this.function.execute(currentState) >= this.function.execute(successors.peek()))) {
				return new LocalSearchResult(currentState, searchCost);
			} else {
				currentState = successors.remove();
			}
		}
	}
	
	private int searchCost;

}
