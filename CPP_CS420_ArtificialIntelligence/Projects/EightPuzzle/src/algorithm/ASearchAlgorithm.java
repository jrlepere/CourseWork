package algorithm;
import java.util.LinkedList;
import java.util.List;

import action.IAction;
import node.Node;

public abstract class ASearchAlgorithm implements ISearchAlgorithm {

	public List<IAction> search(Node goal) {
		List<IAction> searchSequence = new LinkedList<>();
		Node n = goal;
		while (n.getAction() != null) {
			searchSequence.add(0, n.getAction());
			n = n.getParent();
		}
		return searchSequence;
	}
	
}