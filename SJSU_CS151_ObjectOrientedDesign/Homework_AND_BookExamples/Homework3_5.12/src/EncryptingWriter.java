import java.io.IOException;
import java.io.Writer;

/**
 * This class encrypts characters using the Caesar Cipher. This may not be something
 * good to inform to users, but for the sake of the assignment I will. The Caesar Cipher
 * shifts the alphabet by three characters.
 * @author JLepere2
 * Version 1.1
 */
public class EncryptingWriter extends Writer {

	private Writer writer;
	
	/**
	 * Constructs an EncrpytingWriter to decorate the provided writer.
	 * @param writer the writer to decorate.
	 */
	public EncryptingWriter(Writer writer) {
		this.writer = writer;
	}
	
	public void write(char[] cbuf, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			char encryptedC = cbuf[i];
			encryptedC += 3;
			cbuf[i] = encryptedC;
		}
		writer.write(cbuf, off, len);
	}

	public void flush() throws IOException {
		writer.flush();
	}

	public void close() throws IOException {
		writer.close();
	}

}
