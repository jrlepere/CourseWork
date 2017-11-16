/**
 * This class represents the page replacement clock algorithm for evicting and adding a page.
 * @author JLepere2
 *
 */
public class PageReplacementClock {

	private Node currentNode;
	private int nodeCount;
	private OS os;
	
	/**
	 * Creates a page replacement clock.
	 * @param os the OS, used for trapping.
	 */
	public PageReplacementClock(OS os) {
		this.os = os;
		currentNode = null;
		nodeCount = 0;
	}
	
	/**
	 * Adds a virtual page table entry to the clock.
	 * Evicts if needed.
	 * @param virtualPageTableEntry the virtual page table entry.
	 * @return the updated virtual page table entry (bits set) so MMU does not need to search again.
	 */
	public VirtualPageTableEntry add(VirtualPageTableEntry virtualPageTableEntry) {
		/*
		 * Create a new node.
		 */
		Node newNode = new Node(virtualPageTableEntry);
		if (nodeCount == (Math.pow(2,OS.PHYSICAL_MEMORY_ADDRESS_SIZE - OS.OFFSET_SIZE))) {
			/*
			 * Max number of nodes in the clock.
			 * Need to EVICT.
			 */
			while (currentNode.virtualPageTableEntry.isReferenced()) {
				currentNode.virtualPageTableEntry.setReferenced(false);
				currentNode = currentNode.next;
			}
			evict(newNode);
		} else {
			if (currentNode == null) {
				/*
				 * First call to add.
				 * Set the next node to the new node to itself.
				 * Set the current node to this new node.
				 */
				newNode.next = newNode;
				newNode.previous = newNode;
				currentNode = newNode;
			} else {
				/*
				 * Add to new node to the clock.
				 * The the next node of the new node to the next node of the current node.
				 * Set the next node of the current node to the new node.
				 * Set the new node to the current node.
				 */
				newNode.next = currentNode;
				currentNode.previous.next = newNode;
				newNode.previous = currentNode.previous;
				currentNode.previous = newNode;
			}
			
			// Set new node information
			int physicalPageNumber = nodeCount;
			newNode.virtualPageTableEntry.setPhysicalPageNumber(physicalPageNumber);
			newNode.virtualPageTableEntry.setReferenced(true);
			newNode.virtualPageTableEntry.setValid(true);
			newNode.virtualPageTableEntry.setDirty(false);
			nodeCount += 1;
			
		}
		
		return currentNode.previous.virtualPageTableEntry;
		
	}
	
	/**
	 * Evicts the current node from the clock.
	 * @param currentNode the current node.
	 * @param previosNode the previous node.
	 * @param newNode the new node to add.
	 */
	private void evict(Node newNode) {
		
		/*
		 * Evicted Node
		 * 	- Set valid to false.
		 *  - Get the physical page number.
		 *  - If dirty, write to memory.
		 */
		currentNode.virtualPageTableEntry.setValid(false);
		int physicalPageNumber = currentNode.virtualPageTableEntry.getPhysicalPageNumber();
		if (currentNode.virtualPageTableEntry.isDirty()) {
			os.trapDirtyEviction(currentNode.virtualPageTableEntry);
		}
		
		// Trap to the OS to see if the evicting entry is present in the TLB.
		os.trapConsistentTLBEviction(currentNode.virtualPageTableEntry);
		
		// Set new node information
		newNode.virtualPageTableEntry.setPhysicalPageNumber(physicalPageNumber);
		newNode.virtualPageTableEntry.setReferenced(true);
		newNode.virtualPageTableEntry.setValid(true);
		newNode.virtualPageTableEntry.setDirty(false);
		
		// Relink clock.
		currentNode.previous.next = newNode;
		currentNode.next.previous = newNode;
		newNode.previous = currentNode.previous;
		newNode.next = currentNode.next;
		currentNode = newNode.next;
		
	}
	
	/**
	 * A node class for creating links between nodes.
	 * @author JLepere2
	 *
	 */
	private class Node {
		
		private Node next;
		private Node previous;
		private VirtualPageTableEntry virtualPageTableEntry;
		
		/**
		 * Creates a Node.
		 * @param theVirtualPageTableEntry the virtual page table entry.
		 */
		public Node(VirtualPageTableEntry theVirtualPageTableEntry) {
			virtualPageTableEntry = theVirtualPageTableEntry;
			next = null;
			previous = null;
		}
		
	}
	
}
