package local_search;

import java.util.TreeMap;

import problem.State;
import search.AResultObject;

public class LocalSearchResult extends AResultObject {

	public LocalSearchResult(State goalState, int searchCost) {
		super(createMap(goalState, searchCost));
	}
	
	private static TreeMap<String, Object> createMap(State goalState, int searchCost) {
		TreeMap<String,Object> map = new TreeMap<>();
		map.put(GOAL_STATE, goalState);
		map.put(SEARCH_COST, searchCost);
		return map;
	}
	
	public static final String GOAL_STATE = "GOAL_STATE";
	public static final String SEARCH_COST = "SEARCH_COST";
	
}
