import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This program displays two buttons that write to a textField. These buttons are hello and goodbye.
 * @author JLepere2
 *
 */
public class HelloGoodByeTester {

	/**
	 * Main method for this program.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		JTextField textField = new JTextField(TEXTFIELD_SIZE);
		textField.setText(TEXTFIELD_DEFAULT_TEXT);
		
		JButton helloButton = new JButton(HELLO_BUTTON_TEXT);
		helloButton.addActionListener(new 
				ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText(HELLO_BUTTON_TEXT);
					}
				});
		
		JButton goodbyeButton = new JButton(GOODBYE_BUTTON_TEXT);
		goodbyeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(GOODBYE_BUTTON_TEXT);
			}
		});
		
		frame.setLayout(new FlowLayout());
		
		frame.add(helloButton);
		frame.add(goodbyeButton);
		frame.add(textField);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private static final int FRAME_WIDTH = 200;
	private static final int FRAME_HEIGHT = 100;
	private static final String HELLO_BUTTON_TEXT = "Hello, World!";
	private static final String GOODBYE_BUTTON_TEXT = "Goodbye, World!";
	private static final String TEXTFIELD_DEFAULT_TEXT = "Press any button.";
	private static final int TEXTFIELD_SIZE = 20;
	
}
