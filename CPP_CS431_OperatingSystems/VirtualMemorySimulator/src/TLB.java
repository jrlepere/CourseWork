/**
 * A TLB for fast page table lookup.
 * @author JLepere2
 *
 */
public class TLB {

	private TLBEntry[] table = new TLBEntry[OS.NUMBER_OF_ENTRIES_IN_TLB];
	
	/**
	 * Creates a TLB object.
	 */
	public TLB() {
		for (int i = 0; i < table.length; i ++) {
			table[i] = new TLBEntry();
		}
	}
	
	/**
	 * Adds a tlb entry to the tlb.
	 * @param index the index to add to the tlb 
	 * @param tlbEntry the tlb entry to add
	 */
	public void addEntry(int index, TLBEntry tlbEntry) {
		if (index < 0) {
			throw new IllegalArgumentException("TLB Index Error! " + index + " is less than 0!");
		} else if (index >= OS.NUMBER_OF_ENTRIES_IN_TLB) {
			throw new IllegalArgumentException("TLB Index Error! " + index + " is greater the size of the tlb!");
		}
		table[index] = tlbEntry;
	}
	
	/**
	 * Gets the TLBEntry for the virtualPageNumber.
	 * @param virtualPageNumber the virtual page number
	 * @return the TLB Entry or Null is the TLB Entry is not present in the TLB.
	 */
	public TLBEntry get(int virtualPageNumber) {
		for (TLBEntry e : table) {
			if (e.getVirtualPageNumber() == virtualPageNumber) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Sets the valid bit to False.
	 * @param virtualPageNumber the virtual page number
	 */
	public void setInValid(int virtualPageNumber) {
		for (TLBEntry e : table) {
			if (e.getVirtualPageNumber() == virtualPageNumber) {
				e.setValid(false);;
			}
		}
	}
	
	/**
	 * Sets the dirty bit to False.
	 * @param virtualPageNumber the virtual page number
	 */
	public void setDirty(int virtualPageNumber) {
		for (TLBEntry e : table) {
			if (e.getVirtualPageNumber() == virtualPageNumber) {
				e.setDirty(false);;
			}
		}
	}
	
}
