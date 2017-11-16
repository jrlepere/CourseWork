import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * The operating system for abstracting away the hardware components.
 * @author JLepere2
 * @date 11/7/2017
 */
public class OS {
	
	// Static variables
	public static final int VIRTUAL_MEMORY_ADDRESS_SIZE = 16;
	public static final int PHYSICAL_MEMORY_ADDRESS_SIZE = 12;
	public static final int OFFSET_SIZE = 8;
	public static final byte SOFT_MISS_FLAG = -1;
	public static final int NOTIFY_OS_AFTER_X_INSTRUCTIONS = 5;
	public static final int NUMBER_OF_ENTRIES_IN_TLB = 8;
	
	// Components
	private CPU cpu;
	private TLB tlb;
	private MMU mmu;
	private PhysicalMemory ram;
	private VirtualPageTable virtualPageTable;
	
	// Replacement data structures
	private PageReplacementClock prc;
	private Queue<Integer> tlbEntryQueue;
	
	// Variables to hold the csv data
	private static String address;
	private static int readOrWrite;
	private static int softMiss;
	private static int hardMiss;
	private static int hit;
	private static int dirtyBitSetOnEviction;
	private static int dataReadOrWritten;
	private static int totalInstructions;
	private static int softMissCount;
	private static int hardMissCount;
	private static int hitCount;
	private static int hardDiskReads;
	private static int hardDiskWrites;
	
	/**
	 * Creates an OS object.
	 */
	public OS(){
		// Components
		ram = new PhysicalMemory();
		virtualPageTable = new VirtualPageTable();
		mmu = new MMU(this,virtualPageTable,ram);
		tlb = new TLB();
		cpu = new CPU(mmu,tlb);
		
		// Replacement data structures
		prc = new PageReplacementClock(this);
		tlbEntryQueue = new LinkedList<>();
		
		// CSV data vars
		address = "";
		readOrWrite = -1;
		softMiss = -1;
		hardMiss = -1;
		hit = -1;
		dirtyBitSetOnEviction = 0;
		dataReadOrWritten = -1;
		totalInstructions = 0;
		softMissCount = 0;
		hardMissCount = 0;
		hitCount = 0;
		hardDiskReads = 0;
		hardDiskWrites = 0;
	}
	
	/**
	 * Runs the program.
	 * @param program the program to run.
	 */
	public void run(Scanner program) {
		cpu.execute(program);
	}
	
	/**
	 * Trap call to the OS after a hard miss has occurred.
	 * @param virtualPageNumber the virtual page number.
	 * @param virtualPageTableEntry the virtual
	 * @return the updated page table entry. Pass back to the MMU so it does not have to search for it again.
	 */
	public VirtualPageTableEntry trapHardMiss(int virtualPageNumber, VirtualPageTableEntry virtualPageTableEntry) {
		/*
		 * 1) Add the virtual page table entry to the clock. The clock will evict if needed.
		 * 2) Set virtualPageTableArray[virtualPageNumber] = virtualPageTableEntry
		 * 3) Load the page into memory.
		 * Note that the page replacement algorithm does a lot of the heavy lifting as far as:
		 *  - Evicting pages.
		 *  - Setting/Removing referenced bits.
		 *  - Setting valid bits
		 *  - Trapping to the OS if evicting a dirty page
		 */
		virtualPageTableEntry = prc.add(virtualPageTableEntry);
		virtualPageTable.setVirtualPageTableEntry(virtualPageNumber, virtualPageTableEntry);
		loadPageIntoMemory(virtualPageNumber, virtualPageTableEntry);
		return virtualPageTableEntry;
	}
	
	/**
	 * Traps to the OS when a TLB miss occurs.
	 * @param virtualPageNumber the virtual page number
	 * @param virtualPageTableEntry the virtual page table entry
	 */
	public void trapSoftMiss(int virtualPageNumber, VirtualPageTableEntry virtualPageTableEntry) {
		TLBEntry newTLBEntry = new TLBEntry();
		newTLBEntry.setVirtualPageNumber(virtualPageNumber);
		newTLBEntry.setValid(virtualPageTableEntry.isValid());
		newTLBEntry.setReferenced(virtualPageTableEntry.isReferenced());
		newTLBEntry.setDirty(virtualPageTableEntry.isDirty());
		newTLBEntry.setPhysicalPageNumber(virtualPageTableEntry.getPhysicalPageNumber());
		if (tlbEntryQueue.size() < NUMBER_OF_ENTRIES_IN_TLB) {
			// ADD Entry to the TLB
			tlb.addEntry(tlbEntryQueue.size(), newTLBEntry);
			tlbEntryQueue.add(tlbEntryQueue.size());
		} else {
			// REMOVE and ADD Entry to the TLB
			int removedTLBEntryIndex = tlbEntryQueue.remove();
			tlb.addEntry(removedTLBEntryIndex, newTLBEntry);
			tlbEntryQueue.add(removedTLBEntryIndex);
		}
	}
	
	/**
	 * Traps to the OS to remove the valid bit on a TLB Entry if 
	 * we are evicting an entry from the virtual page table that
	 * is also present in the TLB.
	 * @param virtualPageTableEntry
	 */
	public void trapConsistentTLBEviction(VirtualPageTableEntry virtualPageTableEntry) {
		int virtualPageNumber = virtualPageTable.getAddressOfEntry(virtualPageTableEntry);
		tlb.setInValid(virtualPageNumber);
	}
	
	/**
	 * Loads a page into memory.
	 * @param virtualPageTableEntry the virtual page table entry.
	 */
	private void loadPageIntoMemory(int virtualPageNumber, VirtualPageTableEntry virtualPageTableEntry) {
		try {
			
			int physicalPageNumber = virtualPageTableEntry.getPhysicalPageNumber();
			
			// Get the page file
			String hex = Integer.toHexString(virtualPageNumber);
			if (hex.length() == 1) {
				hex = "0" + hex;
			} else if (hex.length() != 2) {
				throw new NumberFormatException("HEX ERROR: " + hex);
			}
			String pageFilename = hex.toUpperCase() + ".pg";
			String pageFileAbsolutePath = Paths.get(Main.getHardDiskLocation(),pageFilename).toString();
			File pageFile = new File(pageFileAbsolutePath);
			Scanner pageFileScanner = new Scanner(pageFile);
			
			// Read each line of data in the file and write to ram
			int offset = 0;
			while (pageFileScanner.hasNextLine()) {
				String dataString = pageFileScanner.nextLine().trim();
				int data = Integer.parseInt(dataString);
				ram.writeData(physicalPageNumber, offset, data);
				offset += 1;
			}
			
			pageFileScanner.close();
			OS.hardDiskReads += 1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Notify the OS after number of instructions.
	 * This is used to trap tell the OS so it ran reset the reference bits of virtual memory entries.
	 */
	public void notifyAfterXInstructions() {
		virtualPageTable.resetReferencedAll();
	}
	
	/**
	 * Sets the dirty bit of a tlb entry.
	 * @param virtualPageTableEntry the virtual page table entry.
	 */
	public void setTLBDirty(VirtualPageTableEntry virtualPageTableEntry) {
		int virtualPageNumber = virtualPageTable.getAddressOfEntry(virtualPageTableEntry);
		tlb.setDirty(virtualPageNumber);
	}
	
	/**
	 * Sets a virtual page entry to dirty.
	 * @param virtualPageNumber the virtual page number.
	 * @param isDirty true if is dirty, false otherwise.
	 */
	public void setVirtualPageEntryDirty(int virtualPageNumber, boolean isDirty) {
		virtualPageTable.setEntryDirty(virtualPageNumber, isDirty);
	}
	
	/**
	 * Traps to the OS if evictions is going to occur on a dirty virtual page table entry.
	 * PageReplacementClock is the caller of this.
	 * @param virtualPageTableEntry the virtual page table entry.
	 */
	public void trapDirtyEviction(VirtualPageTableEntry virtualPageTableEntry) {
		
		// Get Page Information
		int virtualPageNumber = virtualPageTable.getAddressOfEntry(virtualPageTableEntry);
		int physicalPageNumber = virtualPageTableEntry.getPhysicalPageNumber();
		int[] physicalPageData = ram.getPageData(physicalPageNumber);
		
		try {
			
			// Get the page file
			String hex = Integer.toHexString(virtualPageNumber);
			if (hex.length() == 1) {
				hex = "0" + hex;
			} else if (hex.length() != 2) {
				throw new NumberFormatException("HEX ERROR: " + hex);
			}
			String pageFilename = hex.toUpperCase() + ".pg";
			String pageFileAbsolutePath = Paths.get(Main.getHardDiskLocation(),pageFilename).toString();
			File pageFile = new File(pageFileAbsolutePath);
			FileWriter fw = new FileWriter(pageFile);

			// Write each line of data to the page
			for (int b : physicalPageData) {
				fw.write((b) + "\n");
			}
			
			// Clean up
			virtualPageTableEntry.setDirty(false);
			OS.hardDiskWrites += 1;
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		OS.setCSVDirtyBitSetOnEviction(1);
		
	}
	
	/**
	 * Sets the address for csv writing.
	 * @param theAddress the address
	 */
	public static void setCSVAddress(String theAddress) {
		address = theAddress;
	}
	
	/**
	 * Sets read or write.
	 * @param rw 0 for read, 1 for write.
	 */
	public static void setCSVReadOrWrite(int rw) {
		readOrWrite = rw;
	}
	
	/**
	 * Sets soft miss.
	 * @param soft 0 for not soft miss, 1 for soft miss.
	 */
	public static void setCSVSoftMiss(int soft) {
		softMiss = soft;
		if (soft == 1) {
			softMissCount += 1;
		}
	}
	
	/**
	 * Sets hard miss.
	 * @param hard 0 for not hard miss, 1 for hard miss.
	 */
	public static void setCSVHardMiss(int hard) {
		hardMiss = hard;
		if (hard == 1) {
			hardMissCount += 1;
		}
	}
	
	/**
	 * Sets hit.
	 * @param aHit 0 for not hit, 1 for hit.
	 */
	public static void setCSVHit(int aHit) {
		hit = aHit;
		if (aHit == 1) {
			hitCount += 1;
		}
	}
	
	/**
	 * Sets the csv dirty bit on eviction column.
	 * @param dirty 0 for not dirty eviction, 1 for dirty eviction.
	 */
	public static void setCSVDirtyBitSetOnEviction(int dirty) {
		dirtyBitSetOnEviction = dirty;
	}
	
	/**
	 * Set the data for the csv.
	 * The data read from a read instruction or the data written with a write instruction.
	 * @param data the data read or written.
	 */
	public static void setCSVData(int data) {
		dataReadOrWritten = data;
	}
	
	/**
	 * Increments the total instruction count
	 */
	public static void incrementTotalInstructions() {
		OS.totalInstructions += 1;
	}
	
	/**
	 * Flushes the csv data for the current instruction.
	 */
	public static void flushCSV() {
		/*
		 * 1) Get data to write.
		 * 2) Write data.
		 * 3) Reset all.
		 */
		
		String data = address + ",";
		data += readOrWrite + ",";
		data += softMiss + ",";
		data += hardMiss + ",";
		data += hit + ",";
		data += dirtyBitSetOnEviction + ",";
		data += dataReadOrWritten;
		
		Main.addCSVData(data);
		
		address = "";
		readOrWrite = -1;
		softMiss = -1;
		hardMiss = -1;
		hit = -1;
		dirtyBitSetOnEviction = 0;
		dataReadOrWritten = -1;
	}
	
	/**
	 * Adds the final total rows to the csv.
	 */
	public static void flushTotalCSV() {
		String finalData = "\n";
		finalData += "TOTAL_INSTRUCTIONS,SOFT_MISS_COUNT,HARD_MISS_COUNT,HIT_COUNT,TOTAL_DISK_READ,TOTAL_DISK_WRITE,TOTAL_DISK_ACCESSES\n";
		finalData += totalInstructions + "," + softMissCount + "," + hardMissCount + "," + hitCount + "," + hardDiskReads + "," + hardDiskWrites + "," + (hardDiskReads + hardDiskWrites);
		Main.addCSVData(finalData);
	}
	
}
