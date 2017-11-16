import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The header panel for the main guest view.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class GuestHeaderPanel extends JPanel {

	private static final long serialVersionUID = 1531L;

	/**
	 * Creates a GuestHeaderPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel.
	 * @param hotelManager
	 *            the hotel manager.
	 * @param selectionPanel
	 *            the selection panel.
	 * @param availabilityPanel
	 *            the availability panel.
	 * @param viewReservationsPanel
	 *            the view reservation panel.
	 * @param cardPanel
	 *            the card panel to switch between view and create reservation.
	 */
	public GuestHeaderPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager,
			final GuestSelectionPanel selectionPanel, final GuestAvailabilityPanel availabilityPanel,
			final GuestViewReservationsPanel viewReservationsPanel, final JPanel cardPanel) {

		this.setLayout(new GridLayout(1, 3));
		this.setPreferredSize(new Dimension(0, 50));
		this.setBorder(new EmptyBorder(0, 50, 0, 50));

		JButton backButton = new JButton("Sign Out");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
				selectionPanel.setVisible(false);
				availabilityPanel.setVisible(false);
				viewReservationsPanel.setVisible(false);
				availabilityPanel.removeAllFromQueue();
			}
		});
		JButton makeReservationButton = new JButton("Make a Reservation");
		makeReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionPanel.setVisible(true);
				selectionPanel.repaint();
				((CardLayout) cardPanel.getLayout()).show(cardPanel, MainGuestViewPanel.makeReservationId);
			}
		});
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewReservationsPanel.setCurrentAccount(mainCardPanel.getCurrentAccount());
				((CardLayout) cardPanel.getLayout()).show(cardPanel, MainGuestViewPanel.viewReservationId);
			}
		});
		this.add(backButton);
		this.add(makeReservationButton);
		this.add(viewOrCancelReservationButton);

	}

}
