import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * A frame for displaying the receipts.
 * 
 * @author Ziwei Wu 
 * Version 1.1
 */
public class ReceiptFrame extends JFrame {

	private static final long serialVersionUID = 113625L;
	private ReceiptFormat receiptFormat;

	/**
	 * Creates a ReceiptFrame.
	 * 
	 * @param currentAccount
	 *            the current account.
	 * @param reservations
	 *            the reservations that were JUST processed.
	 */
	public ReceiptFrame(final GuestAccount currentAccount, final ArrayList<Reservation> reservations) {
		// ----Instance Variables
		this.receiptFormat = new SimpleReceipt();

		// -----Frame Variables
		final String receiptFrameTitle = "RECEIPT";
		final int receiptFrameWidth = 350;
		final int receiptFrameHeight = 400;

		// -----Frame

		this.setTitle(receiptFrameTitle);
		this.setSize(receiptFrameWidth, receiptFrameHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		// ----Receipt Text Area
		final JTextArea receiptTextArea = new JTextArea(receiptFormat.getReceipt(currentAccount, reservations));
		receiptTextArea.setEditable(false);

		// -----Format panel
		JPanel formatPanel = new JPanel(new FlowLayout());
		final String simpleButtonText = "Simple Format";
		final String compButtonText = "Comprehensive Format";
		JButton simpleButton = new JButton(simpleButtonText);
		simpleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiptFormat = new SimpleReceipt();
				receiptTextArea.setText(receiptFormat.getReceipt(currentAccount, reservations));
				receiptTextArea.repaint();
			}
		});
		JButton compButton = new JButton(compButtonText);
		compButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiptFormat = new ComprehensiveReceipt();
				receiptTextArea.setText(receiptFormat.getReceipt(currentAccount, reservations));
				receiptTextArea.repaint();
			}
		});
		formatPanel.add(simpleButton);
		formatPanel.add(compButton);

		// -----Done Button
		final String doneButtonText = "Done";
		JButton doneButton = new JButton(doneButtonText);
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservations.clear();
				dispose();
			}
		});

		// ----SouthPanel
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(formatPanel, BorderLayout.NORTH);
		southPanel.add(doneButton, BorderLayout.SOUTH);

		// ----Add Components
		this.add(new JScrollPane(receiptTextArea), BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

	}

}
