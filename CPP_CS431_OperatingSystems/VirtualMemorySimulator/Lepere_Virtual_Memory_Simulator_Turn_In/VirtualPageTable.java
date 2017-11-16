/**
 * A virtual page table for mapping virtual address to physical address.
 * @author JLepere2
 *
 */
public class VirtualPageTable {

	private static final int NUMBER_OF_ENTRIES = (int) Math.pow(2, OS.VIRTUAL_MEMORY_ADDRESS_SIZE - OS.OFFSET_SIZE);
	private VirtualPageTableEntry[] virtualPageTable;
	
	/**
	 * Creates a virtual page table object.
	 */
	public VirtualPageTable() {
		virtualPageTable = new VirtualPageTableEntry[NUMBER_OF_ENTRIES];
		for (int i = 0; i < NUMBER_OF_ENTRIES; i ++) {
			virtualPageTable[i] = new VirtualPageTableEntry();
		}
	}
	
	/**
	 * Gets the virtual page table entry from the virtual page table for the virtual page number.
	 * @param virtualPageNumber the virtual page number
	 * @return the virtual page table entry for the passed virtual page number
	 */
	public VirtualPageTableEntry getVirtualPageTableEntry(int pageNumber) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Virtual Page Number Error! " + pageNumber + " is less than 0!");
		} else if (pageNumber >= NUMBER_OF_ENTRIES) {
			throw new IllegalArgumentException("Virtual Page Number Error! " + pageNumber + " is greater the size of the table!");
		}
		return virtualPageTable[pageNumber];
	}
	
	/**
	 * Sets the virtual page entry for the index.
	 * @param pageNumber the virtual page table index
	 * @param theVirtualPageTableEntry the virtual page table entry.
	 */
	public void setVirtualPageTableEntry(int pageNumber, VirtualPageTableEntry theVirtualPageTableEntry) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Virtual Page Number Error! " + pageNumber + " is less than 0!");
		} else if (pageNumber >= NUMBER_OF_ENTRIES) {
			throw new IllegalArgumentException("Virtual Page Number Error! " + pageNumber + " is greater the size of the table!");
		}
		virtualPageTable[pageNumber] = theVirtualPageTableEntry;
	}
	
	/**
	 * Resets the referenced bit for all entries.
	 */
	public void resetReferencedAll() {
		for (VirtualPageTableEntry v : virtualPageTable) {
			v.setReferenced(false);
		}
	}
	
	/**
	 * Gets the address for the passed virtual page table entry.
	 * @param virtualPageTableEntry the virtual page table entry.
	 */
	public int getAddressOfEntry(VirtualPageTableEntry virtualPageTableEntry) {
		for (int i = 0; i < NUMBER_OF_ENTRIES; i ++) {
			if (virtualPageTable[i] == virtualPageTableEntry) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Sets the dirty bit of an entry.
	 * @param pageNumber the page number.
	 * @param isDirty true if dirty, false otherwise.
	 */
	public void setEntryDirty(int pageNumber, boolean isDirty) {
		virtualPageTable[pageNumber].setDirty(isDirty);
	}
	
}
