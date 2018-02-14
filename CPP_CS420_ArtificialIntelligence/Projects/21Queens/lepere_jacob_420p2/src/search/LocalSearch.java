package search;

import local_search.ObjectiveFunction;

/**
 * Generic local search with an objective function.
 * @author JLepere2
 * 02/01/2018
 */
public abstract class LocalSearch implements ISearch {

	public LocalSearch(ObjectiveFunction fun) {
		this.function = fun;
	}
	
	protected ObjectiveFunction function;
	
}
