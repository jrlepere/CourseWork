package local_search;

import problem.Problem;
import problem.State;
import search.IResultObject;
import search.LocalSearch;

/**
 * Limited Random Restart Hill Climbing to find a goal node.
 * @author JLepere2
 * @date 02/08/2018
 */
public class LimitedRandomRestartHillClimb extends LocalSearch {

	public LimitedRandomRestartHillClimb(ObjectiveFunction fun, int maxIterations) {
		super(fun);
		this.maxIterations = maxIterations;
		this.searchCost = 0;
	}

	public IResultObject execute(Problem p) {
		for (int i = 0; i < maxIterations - 1; i++) {
			IResultObject res = new HillClimb(this.function).execute(p);
			searchCost += (Integer) res.getObject(LocalSearchResult.SEARCH_COST);
			State s = (State) res.getObject(LocalSearchResult.GOAL_STATE);
			if (p.isGoalState(s)) {
				return new LocalSearchResult(s, searchCost);
			}
		}
		return (new HillClimb(this.function)).execute(p);
	}
	
	private int maxIterations;
	private int searchCost;

}
