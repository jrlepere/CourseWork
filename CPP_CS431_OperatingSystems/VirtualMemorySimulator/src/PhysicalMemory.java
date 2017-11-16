/**
 * A data structure for physical memory. Stores data in a two-dimensional array. Page frame # -> Page.
 * @author JLepere2
 *
 */
public class PhysicalMemory {

	private static final int NUMBER_OF_PAGES = (int) Math.pow(2, OS.PHYSICAL_MEMORY_ADDRESS_SIZE - OS.OFFSET_SIZE);
	private static final int PAGE_SIZE = (int) Math.pow(2, OS.OFFSET_SIZE);
	private int[][] ram;
	
	/**
	 * Creates a PhysicalMemory object.
	 */
	public PhysicalMemory() {
		ram = new int[NUMBER_OF_PAGES][PAGE_SIZE];
	}
	
	/**
	 * Gets the byte from the address.
	 * @param address the address of the byte of interest.
	 * @return the byte from the address.
	 */
	public int readData(int pageNumber, int offset) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is less than 0!");
		} else if (pageNumber >= NUMBER_OF_PAGES) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is greater the number of pages!");
		}
		if (offset < 0) {
			throw new IllegalArgumentException("Physical Offset Error! " + offset + " is less than 0!");
		} else if (offset >= Math.pow(2, OS.OFFSET_SIZE)) {
			throw new IllegalArgumentException("Physical Offset Error! " + offset + " is greater than max offset!");
		}
		return ram[pageNumber][offset];
	}
	
	/**
	 * Writes a byte of data to ram.
	 * @param pageNumber the page number.
	 * @param offset the offset.
	 * @param data the data to write
	 */
	public void writeData(int pageNumber, int offset, int data) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is less than 0!");
		} else if (pageNumber >= NUMBER_OF_PAGES) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is greater the number of pages!");
		}
		if (offset < 0) {
			throw new IllegalArgumentException("Physical Offset Error! " + offset + " is less than 0!");
		} else if (offset >= Math.pow(2, OS.OFFSET_SIZE)) {
			throw new IllegalArgumentException("Physical Offset Error! " + offset + " is greater than max offset!");
		}
		ram[pageNumber][offset] = data;
	}
	
	/**
	 * Gets the data from a page. Used to write whole pages to disk.
	 * @param pageNumber the page number.
	 * @return data from the entire page in a byte array.
	 */
	public int[] getPageData(int pageNumber) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is less than 0!");
		} else if (pageNumber >= NUMBER_OF_PAGES) {
			throw new IllegalArgumentException("Physical Page Number Error! " + pageNumber + " is greater the number of pages!");
		}
		return ram[pageNumber];
	}
	
}
