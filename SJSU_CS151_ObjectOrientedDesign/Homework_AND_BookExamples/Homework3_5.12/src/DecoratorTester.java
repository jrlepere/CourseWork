import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program tests the EncryptingWriter and EncryptingReader classes.
 * @author JLepere2
 * Version 1.1
 */
public class DecoratorTester {

	private static String stringToWrite = "Everything is going as planned to rig the 2016 presidential election.\n"
			+ "In 10 days, we will succeed.\n"
			+ "See you on the other side.";
	private static String testerFileName = "writerTesterFileName";
	
	/**
	 * The main method for this program.
	 * @param args the command line arguments. Not used.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//----------Encrypts the string and writes to a file------------//
		EncryptingWriter encryptionWriter = new EncryptingWriter(new FileWriter(new File(testerFileName)));
		encryptionWriter.write(stringToWrite);
		encryptionWriter.close();
		
		//--------Reads the File without decryption---------//
		FileReader r = new FileReader(new File(testerFileName));
		Scanner sr = new Scanner(r);
		while (sr.hasNextLine()) {
			System.out.println(sr.nextLine());
		}
		sr.close();
		r.close();
		
		//--------Reads the File with decryption---------//
		DecryptingReader er = new DecryptingReader(new FileReader(new File(testerFileName)));
		Scanner ser = new Scanner(er);
		while (ser.hasNextLine()) {
			System.out.println(ser.nextLine());
		}
		ser.close();
		er.close();
	}
	
}
