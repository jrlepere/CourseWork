import java.io.IOException;
import java.io.Reader;

/**
 * This class reads encrypted characters using the Caesar Cipher. This may not be something
 * good to inform to users, but for the sake of the assignment I will. The Caesar Cipher
 * shifts the alphabet by three characters.
 * @author JLepere2
 * Version 1.1
 */
public class DecryptingReader extends Reader {

	private Reader reader; 
	
	/**
	 * Constructs an EncrptingReader that reads the encryption from the reader.
	 * @param reader the reader to read un-encrypted.
	 */
	public DecryptingReader(Reader reader) {
		this.reader = reader;
	}
	
	public int read(char[] cbuf, int off, int len) throws IOException {
		//http://www.horstmann.com/oodp2/solutions/Ch5/Ex13/DecryptingReader.java
		// Took idea of professor Hostmann's sourcecode to take the result first
		// and then return the result after decoding the character array.
		// Original code was:
		/*
		    for (int i = off; i < off + len; i++) {
				char unencryptedC = cbuf[i];
				unencryptedC += -3;
				cbuf[i] = unencryptedC;
			}
			return reader.read(cbuf, off, len);
		}
		*/
		int result = reader.read(cbuf, off, len); 
		for (int i = off; i < off + len; i++) {
			char unencryptedC = cbuf[i];
			unencryptedC += -3;
			cbuf[i] = unencryptedC;
		}
		return result;
	}

	public void close() throws IOException {
		reader.close();
	}

}
