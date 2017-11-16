import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class for the project. Run this class from the command line.
 * @author JLepere2
 *
 */
public class Main {

	// PATHS
	private static final String ORIGINAL_PAGE_FILE_RELATIVE_PATH = "original_hard_disk";
	private static final String TEMP_PAGE_FILE_RELATIVE_PATH = "temp_hard_disk";
	
	// DATA
	private static final List<String> data = new ArrayList<>(); 
	
	/**
	 * Runs the operating system.
	 * @param args the command line arguments. The filename of a test program txt file for reading and writing data.
	 */
	public static void main(String[] args) {
		
		/*
		 * Check command line argument is present.
		 */
		if (args.length == 0) {
			System.out.println("Missing command line argument. Must pass a text file as input.");
			return;
		}
		
		/*
		 * Copy the original page files to a temporary location
		 * 	so we do not overwrite the original files.
		 */
		try {
			// Working Directory
			String workingDir = System.getProperty("user.dir");
			// Source Information
			String originalPagesDirectory = Paths.get(workingDir, ORIGINAL_PAGE_FILE_RELATIVE_PATH).toString();
			File originalPagesDirectoryFile = new File(originalPagesDirectory);
			// Destination Information
			String tempPagesDirectory = Paths.get(workingDir, TEMP_PAGE_FILE_RELATIVE_PATH).toString();
			// Copy all the page files to the temp directory
			for (File pageFile : originalPagesDirectoryFile.listFiles()) {
				if (pageFile.isFile() && pageFile.getName().endsWith(".pg")) {
					String pageFilename = pageFile.getName();
					Path sourcePath = Paths.get(originalPagesDirectory, pageFilename);
					Path destinationPath = Paths.get(tempPagesDirectory, pageFilename);
					Files.copy(sourcePath, destinationPath, REPLACE_EXISTING);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		/*
		 * Read the command line argument and pass a scanner to the CPU.
		 */
		String filename = "";
		OS os = new OS();
		try {
			
			// Get the program
			filename = args[0];
			//filename = "/Users/JLepere2/Desktop/CalPolyPomona/2017_FALL/CS431/Projects/VirtualMemorySimulator/files/test_files/my_test_1.txt";
			File f = new File(filename);
			Scanner program = new Scanner(f);
			
			// run the program on the OS
			Main.addCSVData("ADDRESS,READ_OR_WRITE,SOFT_MISS,HARD_MISS,HIT,DIRTY_EVICTION,DATA");
			os.run(program);
			OS.flushTotalCSV();
			
			// write data to csv
			FileWriter fw = new FileWriter(new File(filename + ".csv"));
			for (String l : data) {
				fw.write(l + "\n");
			}
			fw.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found for file: " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the location of the 'hard disk' containing the page files.
	 * @return the location of the page files.
	 */
	public static String getHardDiskLocation() {
		/*
		 * The current working directory and the temporary hard disk folder name.
		 */
		return Paths.get(System.getProperty("user.dir"), TEMP_PAGE_FILE_RELATIVE_PATH).toString();
	}
	
	/**
	 * Adds a row of data to the csv.
	 * @param data the row of data.
	 */
	public static void addCSVData(String theData) {
		data.add(theData);
	}
	
}
