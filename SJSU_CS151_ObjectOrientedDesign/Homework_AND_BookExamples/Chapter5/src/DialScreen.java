import java.awt.BorderLayout;

import javax.swing.*;

/**
 * This class represents a Dial Screen with a keypad.
 * @author JLepere2
 * Version 1.1
 */
public class DialScreen extends JPanel {

	private JTextField textField;
	
	public DialScreen() {
		
		textField = new JTextField("");
		this.setLayout(new BorderLayout());
		this.add(textField, BorderLayout.NORTH);
		this.add(new KeyPad(textField), BorderLayout.CENTER);
		this.add(new DialButton(), BorderLayout.SOUTH);
		
	}
	
}
