import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * A panel for the guest to view their reservations
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class GuestViewReservationsPanel extends JPanel {

	private static final long serialVersionUID = 12363L;
	private GuestAccount currentAccount;
	private Reservation selectedReservation;
	private HotelManager hotelManager;

	public GuestViewReservationsPanel(HotelManager hotelManager) {

		this.hotelManager = hotelManager;

		this.setLayout(new BorderLayout());

	}

	public void setCurrentAccount(GuestAccount theCurrentAccount) {
		this.currentAccount = theCurrentAccount;

		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}

		// Table
		final JTable reservationTable = new JTable();
		JPanel reservationTablePretty = new JPanel(new GridLayout(1, 1));
		reservationTablePretty.setBorder(new EmptyBorder(20, 20, 10, 20));
		reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final String[] columnTitles = { "Reservations" };

		// Resevation Text Area
		final JTextArea reservationInfoTextArea = new JTextArea();

		// Cancel Reservation Button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedReservation != null) {
					int result = JOptionPane.showOptionDialog(null, "Are you sure you want to cancel this reservation?",
							"Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					if (result == JOptionPane.YES_OPTION) {
						currentAccount.cancelReservation(selectedReservation);
						hotelManager.removeReservation(selectedReservation);
						ArrayList<Reservation> reservations = currentAccount.getReservations();
						if (reservations.size() > 0) {
							Collections.sort(reservations);
							TableModel tableModel = new DefaultTableModel(columnTitles, reservations.size());
							for (int i = 0; i < reservations.size(); i++) {
								tableModel.setValueAt(reservations.get(i).getTime(), i, 0);
							}
							reservationTable.setModel(tableModel);
							reservationTable.repaint();
							reservationTable.addRowSelectionInterval(0, 0);
							setSelectedRow(reservationInfoTextArea, reservations.get(0));
						} else {
							for (Component comp : getComponents()) {
								remove(comp);
							}
							JLabel noReservationsLabel = new JLabel("No Reservations Made!");
							noReservationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
							add(noReservationsLabel);
							revalidate();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Reservation Selected");
				}
			}
		});

		// Update reservations
		final ArrayList<Reservation> reservations = currentAccount.getReservations();

		if (reservations.size() > 0) {
			// Table
			Collections.sort(reservations);
			final int rowHeight = 40;
			TableModel tableModel = new DefaultTableModel(columnTitles, reservations.size());
			for (int i = 0; i < reservations.size(); i++) {
				tableModel.setValueAt(reservations.get(i).getTime(), i, 0);
			}
			reservationTable.setModel(tableModel);
			reservationTable.setRowHeight(rowHeight);
			reservationTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					JTable source = (JTable) e.getSource();
					int selectedRow = source.getSelectedRow();
					setSelectedRow(reservationInfoTextArea, reservations.get(selectedRow));
				}
			});
			reservationTable.addRowSelectionInterval(0, 0);
			setSelectedRow(reservationInfoTextArea, reservations.get(0));

			// South Panel
			JPanel southPanel = new JPanel(new BorderLayout());
			JPanel reservationInfoTextAreaPretty = new JPanel(new GridLayout(1, 1));
			JPanel cancelButtonPretty = new JPanel(new GridLayout(1, 1));
			reservationInfoTextAreaPretty.setBorder(new EmptyBorder(0, 20, 20, 20));
			cancelButtonPretty.setBorder(new EmptyBorder(0, 0, 20, 20));

			reservationInfoTextAreaPretty.add(reservationInfoTextArea);
			cancelButtonPretty.add(cancelButton);

			southPanel.add(reservationInfoTextAreaPretty, BorderLayout.CENTER);
			southPanel.add(cancelButtonPretty, BorderLayout.EAST);

			// Add Components
			reservationTablePretty.add(new JScrollPane(reservationTable));
			this.add(reservationTablePretty, BorderLayout.CENTER);
			this.add(southPanel, BorderLayout.SOUTH);

		} else {
			JLabel noReservationsLabel = new JLabel("No Reservations Made!");
			noReservationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(noReservationsLabel);
		}

	}

	/**
	 * Sets the newly selected reservation.
	 * 
	 * @param reservationInfoTextArea
	 *            the reservation information text area.
	 * @param reservation
	 *            the new reservation.
	 */
	private void setSelectedRow(JTextArea reservationInfoTextArea, Reservation reservation) {
		this.selectedReservation = reservation;

		String resevationInfoText = "Room Type: " + selectedReservation.getHotelRoom().getRoomType() + "\n"
				+ "Room Number: " + selectedReservation.getHotelRoom().getRoomNumber() + "\n" + "Room Price: $"
				+ selectedReservation.getReservationPrice() + "\n" + "Length of Reservation: "
				+ selectedReservation.getTime();

		reservationInfoTextArea.setText(resevationInfoText);
		reservationInfoTextArea.repaint();

	}

}
