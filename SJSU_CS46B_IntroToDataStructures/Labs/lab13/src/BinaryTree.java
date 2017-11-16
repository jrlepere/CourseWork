
public class BinaryTree 
{
	private Node root;
	
	public BinaryTree()
	{
		root = null;
	}
	
	public BinaryTree(Object rootData, BinaryTree left, BinaryTree right)
	{
		root = new Node();
		root.data = rootData;
		root.left = left.root;
		root.right = right.root;
	}
	
	public BinaryTree(Object rootData) 
	{
		root = new Node();
		root.data = rootData;
		root.left = null;
		root.right = null;
	}
	
	public int height()
	{
		return height(root);
	}
	private static int height(Node n)
	{
		if (n == null) {return 0;}
		else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
	}
	
	public int countNodesWithOneChild()
	{
		return countNodesWithOneChild(root);
	}
	private int countNodesWithOneChild(Node n)
	{
		if (n == null) {
			return 0;
		} else {
			if ((n.left == null && n.right != null) || (n.right == null && n.left != null)) {
				return 1 + countNodesWithOneChild(n.left) + countNodesWithOneChild(n.right);
			} else {
				return countNodesWithOneChild(n.left) + countNodesWithOneChild(n.right);
			}
		}
	}
	
	public void print()
	{
		print(root);
		System.out.println();
	}
	
	private static void print(Node parent)
	{
		if (parent == null) {
			return;
		}
		print(parent.left);
		System.out.print(parent.data + " ");
		print(parent.right);
	}
	
	class Node
	{
		public Object data;
		public Node left;
		public Node right;
		
		public int height()
		{
			int leftHeight = 0;
			if (left != null) {
				leftHeight = left.height();
			}
			int rightHeight = 0;
			if (right != null) {
				rightHeight = right.height();
			}
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}
}
