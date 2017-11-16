import java.util.Stack;

/**
 * This program creates a binary search
 * @author JLepere2
 */
public class BST<AnyType extends Comparable<? super AnyType>> {
	private Node<AnyType> root;
	
	/**
	 * Constructs a bst with initial root of null
	 */
	public BST() {
		root = null;
	}
	
	/**
	 * Adds an element to the bst
	 * @param element the element to add
	 */
	public void add(AnyType element){
		Node<AnyType> newNode = new Node<>(element, null, null);
		newNode.data = element;
		newNode.left = null;
		newNode.right = null;
		if (root == null) {
			root = newNode;
		} else {
			root.add(newNode);
		}
	}
	
	/**
	 * Gets the number of nodes on the bst
	 * @return the number
	 */
	public int numberOfNodes() {
		return numberOfNodes(root);
	}
	
	private int numberOfNodes(Node<AnyType> n) {
		if (n == null) {
			return 0;
		}
		return 1 + numberOfNodes(n.left) + numberOfNodes(n.right);
	}
	
	/**
	 * Gets the height of the bst
	 * @return the height
	 */
	public int height() {
		return height(root);
	}
	
	private int height(Node<AnyType> n) {
		if (n == null) {
			return -1;
		}
		return 1 + Math.max(height(n.left), height(n.right));
	}
	
	/**
	 * Gets the number of leaves on the bst
	 * @return the number of leaves
	 */
	public int numberOfLeaves() {
		return numberOfLeaves(root);
	}
	
	private int numberOfLeaves(Node<AnyType> n) {
		if (n == null) {
			return 0;
		}
		if (n.left == null && n.right == null) {
			return 1;
		}
		return numberOfLeaves(n.left) + numberOfLeaves(n.right);
	}
	
	/**
	 * Checks whether this bst is similar to a given bst
	 * @param <T>
	 * @param other the other bst
	 * @return whether or not the bsts are equal
	 */
	public <T extends Comparable<? super T>> boolean isSimilar(BST<T> other) {
		
		Stack<Node<AnyType>> s1 = new Stack<>();
		Stack<Node<T>> s2 = new Stack<>();
		s1.push(root);
		s2.push((Node<T>) other.root);
		
		while(!s1.isEmpty() && !s2.isEmpty()) {
			Node<AnyType> node1 = s1.pop();
			Node<T> node2 = s2.pop();
			if (node1.right != null) {
				if (node2.right == null) {
					return false;
				}
				s1.push(node1.right);
				s2.push(node2.right);
			}
			if (node1.left != null) {
				if (node2.left == null) {
					return false;
				}
				s1.push(node1.left);
				s2.push(node2.left);
			}
		}
		if (!s1.isEmpty()) {
			return false;
		}
		if (!s2.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private class Node<AnyType extends Comparable<? super AnyType>> {
		private AnyType data;
		private Node<AnyType> left;
		private Node<AnyType> right;
		
		public Node(AnyType element, Node<AnyType> left, Node<AnyType> right) {
			this.data = element;
			this.left = left;
			this.right = right;
		}
		
		public void add(Node<AnyType> n) {
			int comp = n.data.compareTo(data);
			if (comp < 0) {
				if (left == null) {
					left = n;
				} else {
					left.add(n);
				}
			} else if (comp > 0) {
				if (right == null) {
					right = n;
				} else {
					right.add(n);
				}
			} else {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		BST<Integer> b1 = new BST<>();
		b1.add(20);
		b1.add(100);
		b1.add(80);
		b1.add(4);
		b1.add(2);
		b1.add(30);
		b1.add(25);
		b1.add(120);
		b1.add(115);
		
		BST<Integer> b2 = new BST<>();
		b2.add(55);
		b2.add(154);
		b2.add(19);
		b2.add(135);
		b2.add(4);
		b2.add(3);
		b2.add(135);
		b2.add(551);
		b2.add(12);
		
		BST<String> b3 = new BST<>();
		b3.add("Jake");
		b3.add("Professor M");
		b3.add("Sally");
		b3.add("Billy");
		
		BST<Double> b4 = new BST<>();
		b4.add(15.135);
		b4.add(3.141);
		b4.add(21.5);
		b4.add(10951.5135);
		
		System.out.println("b1 similar to b2?: " + b1.isSimilar(b2));
		System.out.println("b3 similar to b4?: " + b3.isSimilar(b4));
	}
}
