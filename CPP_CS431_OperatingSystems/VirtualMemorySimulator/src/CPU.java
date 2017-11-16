import java.util.Scanner;

/**
 * A CPU class for processing the input text file.
 * @author JLepere2
 *
 */
public class CPU {
	
	private static final String READ_FLAG = "0";
	private static final String WRITE_FLAG = "1";
	private MMU mmu;
	private TLB tlb;
	
	/**
	 * Creates a CPU object for executing the input program. 
	 */
	public CPU(MMU theMMU, TLB theTLB) {
		mmu = theMMU;
		tlb = theTLB;
	}
	
	/**
	 * Executes a program on the CPU.
	 * @param program the program to execute.
	 */
	public void execute(Scanner program) {
		/*
		 * Program records must be in one of the following two formats.
		 * 1) - Printing Data - 
		 *   virtual address
		 *   0
		 * 2) - Writing Data -
		 *   virtual address
		 *   1
		 *   data to write
		 */
		
		int lineNumber = 0;
		while (program.hasNextLine()) {
			lineNumber += 1;
			String readWriteFlag = program.nextLine().trim();
			if (!(readWriteFlag.equals(READ_FLAG) || readWriteFlag.equals(WRITE_FLAG))) {
				System.out.println("Illegal Input Program @ Line " + lineNumber + ". Read Write Flag, " + readWriteFlag + ", is not 0 or 1.");
				break;
			}
			if (program.hasNextLine()) {
				lineNumber += 1;
				String virtualAddress = program.nextLine().trim();
				if (readWriteFlag.equals(WRITE_FLAG)) {
					if (program.hasNextLine()) {
						lineNumber += 1;
						String dataToWrite = program.nextLine().trim();
						// PROCESS WRITING
						processWriting(virtualAddress,dataToWrite);
					} else {
						System.out.println("Illegal Input Program @ Line " + lineNumber + ". Data to write is missing.");
						break;
					}
				} else {
					// PROCESS READING
					processReading(virtualAddress);
				}
			} else {
				System.out.println("Illegal Input Program @ Line " + lineNumber + ". r/w must be followed by a virtual address.");
				break;
			}
		}
	}
	
	/**
	 * Processes reading of data.
	 * @param virtualAddress the virtual address.
	 */
	private void processReading(String virtualAddress) {
		OS.setCSVAddress(virtualAddress);
		OS.setCSVReadOrWrite(0);
		OS.incrementTotalInstructions();
		int[] res = decodeVirtualAddress(virtualAddress);
		int virtualPageNumber = res[0];
		int offset = res[1];
		TLBEntry tlbTableEntry = checkTLB(virtualPageNumber);
		mmu.readByte(tlbTableEntry, virtualPageNumber, offset);
	}
	
	/**
	 * Processes writing of data.
	 * @param virtualAddress the virtual address.
	 * @param dataToWrite the data to write.
	 */
	private void processWriting(String virtualAddress, String dataToWrite) {
		OS.setCSVAddress(virtualAddress);
		OS.setCSVReadOrWrite(1);
		OS.incrementTotalInstructions();
		int[] res = decodeVirtualAddress(virtualAddress);
		int virtualPageNumber = res[0];
		int offset = res[1];
		TLBEntry tlbTableEntry = checkTLB(virtualPageNumber);
		int data = Integer.parseInt(dataToWrite);
		mmu.writeByte(tlbTableEntry, virtualPageNumber, offset, data);
	}
	
	/**
	 * Check if the virtual page number is present in the TLB.
	 * @param virtualPageNumber the virtual page number.
	 * @return the TLBEntry if present, null otherwise.
	 */
	private TLBEntry checkTLB(int virtualPageNumber) {
		return tlb.get(virtualPageNumber);
	}
	
	/**
	 * Decodes the virtual address into the virtual page number and offset.
	 * @param virtualAddress the virtual address.
	 * @return a byte array of size 2 where the first element is the virtual page number and the second is the offset.
	 */
	private int[] decodeVirtualAddress(String virtualAddress) {
		int[] res = new int[2];
		int virtualAddressInteger = Integer.parseInt(virtualAddress,16);
		int virtualPageNumber = 0xFF & ((virtualAddressInteger << (32 - OS.VIRTUAL_MEMORY_ADDRESS_SIZE)) >> (32 - OS.OFFSET_SIZE));
		int offset = 0xFF & ((virtualAddressInteger << (32 - OS.OFFSET_SIZE)) >> (32 - OS.OFFSET_SIZE));
		res[0] = virtualPageNumber;
		res[1] = offset;
		return res;
	}
	
}
