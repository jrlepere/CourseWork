/**
 * An entry for the virtual page table.
 * @author JLepere2
 *
 */
public class VirtualPageTableEntry {

	private boolean validBit;
	private boolean referenceBit;
	private boolean dirtyBit;
	private int physicalPageNumber;
	
	/**
	 * Creates a virtual page table entry.
	 */
	public VirtualPageTableEntry() {
		validBit = false;
		referenceBit = false;
		dirtyBit = false;
		physicalPageNumber = 0;
	}
	
	/**
	 * Gets whether or not this entry is valid.
	 * @return True if the entry is valid, false otherwise.
	 */
	public boolean isValid() {
		return validBit;
	}
	
	/**
	 * Gets whether or not this entry is valid.
	 * @return True if the entry is read only, false otherwise
	 */
	public boolean isReferenced() {
		return referenceBit;
	}
	
	/**
	 * Gets whether or not this entry is dirty.
	 * @return True if the entry is dirty, false otherwise.
	 */
	public boolean isDirty() {
		return dirtyBit;
	}
	
	/**
	 * Gets the physical page number.
	 * @return the physical page number.
	 */
	public int getPhysicalPageNumber() {
		return physicalPageNumber;
	}
	
	/**
	 * Sets the valid bit.
	 * @param isValid True if the entry is valid, false otherwise.
	 */
	public void setValid(Boolean isValid) {
		this.validBit = isValid;
	}
	
	/**
	 * Sets the referenced bit.
	 * @param referenced True if referenced, false to unset.
	 */
	public void setReferenced(Boolean isReferenced) {
		this.referenceBit = isReferenced;
	}
	
	/**
	 * Sets the dirty bit.
	 * @param isDirty true if dirty, false otherwise.
	 */
	public void setDirty(Boolean isDirty) {
		this.dirtyBit = isDirty;
	}
	
	/**
	 * Sets the physical page number.
	 * @param thePhysicalPageNumber the physical page number.
	 */
	public void setPhysicalPageNumber(int thePhysicalPageNumber) {
		this.physicalPageNumber = thePhysicalPageNumber;
	}
	
}
