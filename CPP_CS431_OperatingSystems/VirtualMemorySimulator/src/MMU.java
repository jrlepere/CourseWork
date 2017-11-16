/**
 * A class for the memory management unit. Manages the memory.
 * @author JLepere2
 *
 */
public class MMU {

	private OS os;
	private VirtualPageTable virtualPageTable;
	private PhysicalMemory ram;
	private int instructionsProcessedCount;
	private int hardMissCount;
	
	/**
	 * Creates an MMU object.
	 * @param theOS the Operating System. OS object is passed so we can trap if a page is missing from physical memory.
	 * @param theVirtualPageTable the Virtual Page Table.
	 * @param theRAM the Physical Memory.
	 */
	public MMU(OS theOS, VirtualPageTable theVirtualPageTable, PhysicalMemory theRAM){
		os = theOS;
		virtualPageTable = theVirtualPageTable;
		ram = theRAM;
		instructionsProcessedCount = 0;
		hardMissCount = 0;
	}
	
	/**
	 * Gets the virtual page table entry.
	 * @param tlbTableEntry the tlb table entry. Null if TLB miss.
	 * @param virtualPageNumber the virtual page number.
	 * @return the virtual page table entry.
	 */
	private VirtualPageTableEntry getValidVirtualPageTableEntry(TLBEntry tlbTableEntry, int virtualPageNumber) {
		/*
		 * If TLB entry not null: 
		 *   TLB HIT: Cast tlbEntry to virtualPageEntry
		 * Else:
		 *   Get virtual page entry from virtual page table
		 *   If result is invalid:
		 *   	HARD MISS: trap to OS and get from disk.
		 *   else:
		 *   	SOFT MISS
		 */
		VirtualPageTableEntry virtualPageTableEntry;
		if (tlbTableEntry != null) {
			// TLB HIT
			virtualPageTableEntry = (VirtualPageTableEntry) tlbTableEntry;
			if (!virtualPageTableEntry.isValid()) {
				/*
				 * HARD MISS
				 * VirtualPageTableEntry is not VALID. Trap to the OS.
				 */
				virtualPageTableEntry = os.trapHardMiss(virtualPageNumber,virtualPageTableEntry);
				OS.setCSVSoftMiss(0);
				OS.setCSVHardMiss(1);
				OS.setCSVHit(0);
				
				// Initializes the TLB
				hardMissCount += 1;
				if (hardMissCount <= OS.NUMBER_OF_ENTRIES_IN_TLB) {
					os.trapSoftMiss(virtualPageNumber, virtualPageTableEntry);
				}
			} else {
				OS.setCSVSoftMiss(0);
				OS.setCSVHardMiss(0);
				OS.setCSVHit(1);
			}
		} else {
			// TLB Miss
			virtualPageTableEntry = virtualPageTable.getVirtualPageTableEntry(virtualPageNumber);
			if (!virtualPageTableEntry.isValid()) {
				/*
				 * HARD MISS
				 * VirtualPageTableEntry is not VALID. Trap to the OS.
				 */
				virtualPageTableEntry = os.trapHardMiss(virtualPageNumber,virtualPageTableEntry);
				OS.setCSVSoftMiss(0);
				OS.setCSVHardMiss(1);
				OS.setCSVHit(0);
				
				// Initializes the TLB
				hardMissCount += 1;
				if (hardMissCount <= OS.NUMBER_OF_ENTRIES_IN_TLB) {
					os.trapSoftMiss(virtualPageNumber, virtualPageTableEntry);
				}
			} else {
				/*
				 * SOFT MISS
				 */
				os.trapSoftMiss(virtualPageNumber, virtualPageTableEntry);
				OS.setCSVSoftMiss(1);
				OS.setCSVHardMiss(0);
				OS.setCSVHit(0);
			}
		}
		return virtualPageTableEntry;
	}
	
	/**
	 * Prints the byte data from the address specified.
	 * @param tlbTableEntry the tlb table entry, null if there was a TLB miss.
	 * @param virtualPageNumber the virtual page number.
	 * @param offset the offset.
	 */
	public void readByte(TLBEntry tlbTableEntry, int virtualPageNumber, int offset) {
		/*
		 * 1) Get the virtual page table entry
		 * 2) Get the physical page number from the entry
		 * 3) Get the data from RAM
		 * 4) Set the REFERENCED BIT
		 * 5) Print the data
		 * 6) Notify the OS after X instructions.
		 */
		VirtualPageTableEntry virtualPageTableEntry = getValidVirtualPageTableEntry(tlbTableEntry, virtualPageNumber);
		int physicalPageNumber = virtualPageTableEntry.getPhysicalPageNumber();
		int data = ram.readData(physicalPageNumber, offset);
		virtualPageTableEntry.setReferenced(true);
		System.out.println(data);
		OS.setCSVData(data);
		OS.flushCSV();
		instructionsProcessedCount += 1;
		if (instructionsProcessedCount % OS.NOTIFY_OS_AFTER_X_INSTRUCTIONS == 0) {
			os.notifyAfterXInstructions();
		}
	}
	
	/**
	 * Write data management.
	 * @param tlbTableEntry the tlb table entry, null if there was a TLB miss.
	 * @param virtualPageNumber the virtual page number.
	 * @param offset the offset.
	 * @param dataToWrite the data to write.
	 */
	public void writeByte(TLBEntry tlbTableEntry, int virtualPageNumber, int offset, int dataToWrite) {
		/*
		 * 1) Get the virtual page table entry
		 * 2) Get the physical page number from the entry
		 * 3) Write the data to RAM
		 * 4) Set the REFERENCED BIT
		 * 5) Set the DIRTY BIT
		 * 6) Notify the OS after X instructions.
		 */
		VirtualPageTableEntry virtualPageTableEntry = getValidVirtualPageTableEntry(tlbTableEntry, virtualPageNumber);
		int physicalPageNumber = virtualPageTableEntry.getPhysicalPageNumber();
		ram.writeData(physicalPageNumber, offset, dataToWrite);
		virtualPageTableEntry.setReferenced(true);
		virtualPageTableEntry.setDirty(true);
		if (tlbTableEntry != null) {
			os.setVirtualPageEntryDirty(virtualPageNumber, true);
		}
		OS.setCSVData(dataToWrite);
		OS.flushCSV();
		instructionsProcessedCount += 1;
		if (instructionsProcessedCount % OS.NOTIFY_OS_AFTER_X_INSTRUCTIONS == 0) {
			os.notifyAfterXInstructions();
		}
	}
	
}
