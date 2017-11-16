import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 * Tests the Key Pad
 * @author JLepere2
 * Version 1.1
 */
public class KeyPadTester {
	
	private static int FRAME_SIZE = 300;
	
	/**
	 * The main method. Draws the KeyPad.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Key Pad Tester");
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		
		frame.setLayout(new BorderLayout());
		frame.add(new DialScreen());
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
