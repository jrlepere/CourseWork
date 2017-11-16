import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InvoiceTester {
	
	public static void main(String[] args) {
		
		final Invoice invoice = new Invoice();
		
		final JTextArea textArea = new javax.swing.JTextArea(20, 40);
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textArea.setText(invoice.toString());
			}
		};
		invoice.addChangeListener(listener);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = "Test";
				invoice.addItem(item);
			}
		});
		
		JFrame frame = new JFrame("Invoice Tester");
		frame.setSize(200, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(textArea,BorderLayout.CENTER);
		panel.add(addButton, BorderLayout.SOUTH);
		
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}
