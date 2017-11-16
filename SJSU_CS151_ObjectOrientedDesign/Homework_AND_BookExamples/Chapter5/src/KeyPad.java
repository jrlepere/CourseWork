import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class represents the keypad on a phone.
 * @author JLepere2
 * Version 1.1
 */
public class KeyPad extends JPanel {
	
	/**
	 * Constructs a normal Key Pad with 10 buttons.
	 */
	public KeyPad() {
		this.setLayout(new GridLayout(4, 3));
		for (int i = 1; i < 12; i ++) {
			if (i <= 9) {
				this.add(new JButton("" + i));
			} else if (i == 10 || i == 12) {
				this.add(new JLabel(""));
			} else {
				this.add(new JButton("0"));
			}
		}
	}
	
	/**
	 * Constructs a key pad that updates a text field based on keys touched.
	 * @param textField the text field to interact with.
	 */
	public KeyPad(JTextField textField) {
		this.setLayout(new GridLayout(4, 3));
		for (int i = 1; i < 12; i ++) {
			int buttonText;
			if (i <= 9) {
				buttonText = i;
			} else if (i == 10 || i == 12) {
				buttonText = -1;
			} else {
				buttonText = 0;
			}
			if (buttonText >= 0) {
				JButton numberButton = new JButton(buttonText + "");
				numberButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String text = textField.getText();
						text += numberButton.getText();
						textField.setText(text);
					}
				});
				this.add(numberButton);
			} else {
				this.add(new JLabel());
			}
		}
	}
	
}
