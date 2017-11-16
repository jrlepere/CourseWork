/**
 * An entry for the TLB, which is an extension of the virtual page table entry.
 * @author JLepere2
 *
 */
public class TLBEntry extends VirtualPageTableEntry {

	private int virtualPageNumber;
	
	/**
	 * Creates a TLB entry.
	 */
	public TLBEntry() {
		super();
		virtualPageNumber = -1;
	}
	
	/**
	 * Gets the virtual page number for this TLB entry.
	 * @return the virtual page number.
	 */
	public int getVirtualPageNumber() {
		return virtualPageNumber;
	}
	
	/**
	 * Sets the virtual page number.
	 * @param theVirtualPageNumber the virtual page number.
	 */
	public void setVirtualPageNumber(int theVirtualPageNumber) {
		this.virtualPageNumber = theVirtualPageNumber;
	}
	
}
