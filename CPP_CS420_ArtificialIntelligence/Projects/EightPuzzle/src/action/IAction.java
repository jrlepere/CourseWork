package action;
import eight_puzzle.State;

/**
 * An Action Interface.
 * @author JLepere2
 * @date 01/25/2018
 */
public interface IAction {

	/**
	 * Tests if the action will result in a new state.
	 * @param s the state
	 * @return true if the action will result in a new state, false otherwise.
	 */
	public boolean canExecute(State s);
	
	/**
	 * Executes the action on the state.
	 * @param s the state
	 * @return a new State
	 */
	public State execute(State s);
	
	/**
	 * Gets the cost of executing the action.
	 * @return the cost of the action.
	 */
	public int getCost();
	
}
