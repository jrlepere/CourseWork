package problem;
import java.util.List;

/**
 * A Problem Interface.
 * @author JLepere2
 * @date: 02/01/2018
 */
public interface Problem {
	
	/**
	 * Gets the initial State.
	 * @return the initial State.
	 */
	public State getInitialState();
	
	/**
	 * Gets all possible actions for this problem
	 * @return a list of actions
	 */
	public List<Action> getActions();
	
	/**
	 * Tests if the passed State is a goal State.
	 * @param s the State
	 * @return true if the State is a goal State, false otherwise
	 */
	public boolean isGoalState(State s);
	
}
