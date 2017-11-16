import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Testing with the MVC Pattern
 * @author JLepere2
 * Version 1.1
 */
public class MVCTester {

	public static void main(String[] args) {
		
		// MODEL
		Model<String> model = new Model<>();
		
		// FRAME
		JFrame frame = new JFrame("MVC TESTER");
		frame.setSize(300, 500);
		
		// VIEW
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		// INPUT FIELD
		JTextField textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
					model.addData(textField.getText());
					textField.setText("");
					textField.repaint();
				}
			}
		});
		
		// CONTROLLER
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				model.addData(text);
				textField.setText("");
				textField.repaint();
			}
		});
		
		model.addListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textArea.setText(model.getDataString());
				textArea.repaint();
			}
		});
		
		// South Panel
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(textField, BorderLayout.NORTH);
		southPanel.add(addButton, BorderLayout.SOUTH);
		
		// Add to Frame
		frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
