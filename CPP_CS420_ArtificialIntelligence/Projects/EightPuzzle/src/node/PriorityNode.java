package node;
import action.IAction;
import eight_puzzle.State;

public class PriorityNode extends Node implements Comparable<PriorityNode> {

	public PriorityNode(State theState, Node theParent, IAction theAction, int theEvaluationFunctionValue) {
		super(theState, theParent, theAction);
		this.evaluationFunctionValue = theEvaluationFunctionValue;
	}
	
	public int compareTo(PriorityNode o) {
		return this.evaluationFunctionValue - o.evaluationFunctionValue;
	}
	
	public boolean equals(Object o) {
		return this.getState().equals(((PriorityNode) o).getState());
	}
	
	/**
	 * Gets the value of f(n) the evaluation function
	 * @return the value of f(n)
	 */
	public int getEvaluationFunctionValue() {
		return evaluationFunctionValue;
	}
	
	private int evaluationFunctionValue;

}
