import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This program tests the MVC design.
 * Note for grader:
 *   Model: lineCollection
 *   View: textArea
 *   Controllers: textField, addButton
 * I added a key listener action because, well, I found that pressing the Add button was tedious.
 * @author JLepere2
 * Version 1.1
 */
public class MVCTester {

	private static int FRAME_HEIGHT = 400;
	private static int FRAME_WIDTH = 400;
	
	/**
	 * The main method for this program.
	 * @param args the command line arguments. Not Used.
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("MVC Tester");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//--------MODEL---------//
		LineCollection lineCollection = new LineCollection();
		
		//--------TextField: CONTROLLER--------//
		JTextField textField = new JTextField();
		textField.setEditable(true);
		
		//--------AddButton: CONTROLLER--------//
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lineCollection.add(textField.getText());
				textField.setText("");
			}
		});
		
		//--------TextArea: VIEW--------//
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textArea.setText(lineCollection.toString());
			}
		};
		lineCollection.attachListener(changeListener);
		
		//-------KeyListener--------//
			// User can press 'Enter' to submit a line.
		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					lineCollection.add(textField.getText());
					textField.setText("");
				}
			}
		});
		
		frame.setLayout(new BorderLayout());
		frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.add(textField, BorderLayout.SOUTH);
		frame.add(addButton, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
