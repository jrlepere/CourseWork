import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panel for the guest availability view portion of the guest view panel
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class GuestAvailabilityPanel extends JPanel {

	private static final long serialVersionUID = 113546L;
	private ArrayList<Reservation> reservationQueue;
	private ArrayList<HotelRoom> availableRooms;
	private static String queueHeader = "Reservation Queue:";

	/**
	 * Create a MainGuestAvailabilityPanel
	 * 
	 * @param mainCardPanel
	 *            the main card panel. Used to access the current account.
	 * @param dateRangeModel
	 *            the date range model.
	 * @param hotelManager
	 *            the hotel manager.
	 */
	public GuestAvailabilityPanel(final MainCardPanel mainCardPanel, final DateRangeReservationModel dateRangeModel,
			final HotelManager hotelManager) {

		this.availableRooms = new ArrayList<>();
		this.reservationQueue = new ArrayList<>();

		this.setLayout(new GridLayout(1, 1));
		this.setBorder(new EmptyBorder(0, 20, 20, 20));
		JPanel layoutPretty = new JPanel(new BorderLayout());

		final int maxDays = 60;

		// ----Reservation Text Area Queue----//
		final JTextArea reservationQueueTextArea = new JTextArea(queueHeader);

		// ----AVAILABILITY PANEL
		JPanel availabilityPanel = new JPanel(new BorderLayout());
		JPanel roomsAvailablePanelPretty = new JPanel(new GridLayout(2, 1));
		JPanel emptyPanel = new JPanel(new GridLayout(1, 1));
		emptyPanel.setBorder(new EmptyBorder(0, 0, 120, 0));
		final JPanel roomsAvailablePanel = new JPanel(new GridLayout(2, 5, 10, 10));

		roomsAvailablePanelPretty.setBorder(new StrokeBorder(new BasicStroke(1)));
		final JLabel availableTextLabel = new JLabel(dateRangeModel.getDateRangeHeader());
		roomsAvailablePanelPretty.add(roomsAvailablePanel);
		roomsAvailablePanelPretty.add(emptyPanel);
		availabilityPanel.add(availableTextLabel, BorderLayout.NORTH);
		availabilityPanel.add(roomsAvailablePanelPretty, BorderLayout.CENTER);

		// -----ROOM NUMBER PANEL-----//
		JPanel roomNumberPanel = new JPanel();
		JLabel roomNumberLabel = new JLabel("Enter the room number to reserve:");
		final JTextField roomNumberTextField = new JTextField("");
		roomNumberTextField.setPreferredSize(new Dimension(30, 20));
		roomNumberPanel.add(roomNumberLabel);
		roomNumberPanel.add(roomNumberTextField);

		// ------BUTTONS PANEL-----//
		JPanel buttonsPanel = new JPanel();
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int roomSelected = Integer.parseInt(roomNumberTextField.getText().trim());
					boolean valid = false;
					for (HotelRoom r : availableRooms) {
						if (r.getRoomNumber() == roomSelected) {
							valid = true;
							MyDate fromDate = dateRangeModel.getDateFrom();
							MyDate toDate = dateRangeModel.getDateTo();
							System.out.println(fromDate.totalDays(toDate));
							if (fromDate.totalDays(toDate) > maxDays) {
								JOptionPane.showMessageDialog(null,
										"Max Reservation Stay of " + maxDays + " Days is Exceded.");
							} else {
								Reservation res = new Reservation(mainCardPanel.getCurrentAccount(), r, fromDate,
										toDate);
								mainCardPanel.getCurrentAccount().addReservation(res);
								hotelManager.addReservation(res);
								ArrayList<Reservation> reservations = new ArrayList<>();
								reservations.add(res);
								showReceipt(mainCardPanel.getCurrentAccount(), reservations);
								setAvailabilityPanel(dateRangeModel, availableTextLabel, roomsAvailablePanel,
										roomNumberTextField);
								roomNumberTextField.setText("");
								roomNumberTextField.repaint();
							}
						}
					}
					if (!valid) {
						JOptionPane.showMessageDialog(null, "Invalid Room Number");
					}
					// TODO: receipt
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid Room Number");
				}

			}
		});
		JButton moreReservationsButton = new JButton("More Reservations?");
		moreReservationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int roomSelected = Integer.parseInt(roomNumberTextField.getText().trim());
					boolean valid = false;
					for (HotelRoom r : availableRooms) {
						if (r.getRoomNumber() == roomSelected) {
							valid = true;
							reservationQueue.add(new Reservation(mainCardPanel.getCurrentAccount(), r,
									dateRangeModel.getDateFrom(), dateRangeModel.getDateTo()));
						}
					}
					if (valid) {
						JOptionPane.showMessageDialog(null, "Added Reservation to Queue.");
						String reservationQueueText = queueHeader + "\n";
						for (Reservation r : reservationQueue) {
							reservationQueueText += "\nRoom Type: " + r.getHotelRoom().getRoomType() + "\nRoom Number: "
									+ r.getHotelRoom().getRoomNumber() + "\nReservation Dates: " + r.getTime()
									+ "\nPrice: $" + r.getReservationPrice() + "\n";
						}
						reservationQueueTextArea.setText(reservationQueueText);
						reservationQueueTextArea.repaint();
						roomNumberTextField.setText("");
						roomNumberTextField.repaint();
						setAvailabilityPanel(dateRangeModel, availableTextLabel, roomsAvailablePanel,
								roomNumberTextField);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Room Number");
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid Room Number");
				}
			}
		});
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Reservation r : reservationQueue) {
					mainCardPanel.getCurrentAccount().addReservation(r);
					hotelManager.addReservation(r);
				}
				showReceipt(mainCardPanel.getCurrentAccount(), reservationQueue);
				setAvailabilityPanel(dateRangeModel, availableTextLabel, roomsAvailablePanel, roomNumberTextField);
				reservationQueueTextArea.setText(queueHeader);
				reservationQueueTextArea.repaint();
				roomNumberTextField.setText("");
				roomNumberTextField.repaint();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(moreReservationsButton);
		buttonsPanel.add(doneButton);

		// -----ROOM AND BUTTON PANEL-----//
		JPanel roomAndButtonPanel = new JPanel(new GridLayout(2, 1));
		roomAndButtonPanel.add(roomNumberPanel);
		roomAndButtonPanel.add(buttonsPanel);

		// ---- COMBO PANEL -----//
		JPanel comboPanel = new JPanel(new BorderLayout());
		comboPanel.add(roomAndButtonPanel, BorderLayout.NORTH);
		comboPanel.add(new JScrollPane(reservationQueueTextArea), BorderLayout.CENTER);

		// -----ADD TO PANEL----//

		layoutPretty.add(availabilityPanel, BorderLayout.CENTER);
		layoutPretty.add(comboPanel, BorderLayout.EAST);

		this.add(layoutPretty);

		dateRangeModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setAvailabilityPanel(dateRangeModel, availableTextLabel, roomsAvailablePanel, roomNumberTextField);
			}
		});

	}

	/**
	 * Sets the availability text are
	 * 
	 * @param dateRangeModel
	 *            the date range model
	 * @param availableTextLabel
	 *            the availability text area
	 */
	private void setAvailabilityPanel(DateRangeReservationModel dateRangeModel, JLabel availableTextLabel,
			JPanel roomsAvailablePanel, final JTextField roomNumberTextField) {

		// Remove current components
		for (Component c : roomsAvailablePanel.getComponents()) {
			roomsAvailablePanel.remove(c);
		}

		// Update Rooms Available
		availableRooms = dateRangeModel.getAvailableRooms(reservationQueue);

		final int maxRooms = 10;
		for (int i = 1; i <= maxRooms; i++) {
			boolean available = false;
			for (HotelRoom r : availableRooms) {
				if (r.getRoomNumber() == i) {
					available = true;
				}
			}
			JButton roomButton = new JButton(i + "");
			roomButton.setForeground(Color.RED);
			if (available) {
				final String k = i + "";
				roomButton.setForeground(Color.GREEN);
				roomButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						roomNumberTextField.setText(k);
						roomNumberTextField.repaint();
					}
				});
			}
			roomsAvailablePanel.add(roomButton);
		}

		// Update
		roomsAvailablePanel.revalidate();
		availableTextLabel.setText(dateRangeModel.getDateRangeHeader());
		availableTextLabel.repaint();
	}

	/**
	 * Clears all the reservations from the queue
	 */
	public void removeAllFromQueue() {
		reservationQueue.clear();
	}

	/**
	 * Shows the receipt frame.
	 * 
	 * @param currentAccount
	 *            the current guest account
	 * @param reservations
	 *            an array list of reservations that were just processed.
	 */
	private void showReceipt(GuestAccount currentAccount, ArrayList<Reservation> reservations) {
		ReceiptFrame receiptFrame = new ReceiptFrame(currentAccount, reservations);
		receiptFrame.setVisible(true);
	}

}
