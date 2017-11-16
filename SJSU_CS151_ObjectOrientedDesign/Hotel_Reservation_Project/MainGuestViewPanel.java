import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * The main guest view panel for the application.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class MainGuestViewPanel extends JPanel {

	private static final long serialVersionUID = 1215316L;
	private static final String identifier = "MainGuestViewPanel";
	public static final String makeReservationId = "MakeReservation";
	public static final String viewReservationId = "ViewReservation";

	/**
	 * Creates a MainGuestViewPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel.
	 * @param hotelManager
	 *            the hotel manager.
	 */
	public MainGuestViewPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

		this.setLayout(new BorderLayout());

		final DateRangeReservationModel dateRangeModel = new DateRangeReservationModel(hotelManager);

		// ----CENTER MAKE RES PANEL ---//
		JPanel centerMakeReservationPanel = new JPanel(new BorderLayout());
		GuestAvailabilityPanel availabilityPanel = new GuestAvailabilityPanel(mainCardPanel, dateRangeModel,
				hotelManager);
		GuestSelectionPanel selectionPanel = new GuestSelectionPanel(dateRangeModel, availabilityPanel);
		selectionPanel.setVisible(false);
		availabilityPanel.setVisible(false);
		centerMakeReservationPanel.add(selectionPanel, BorderLayout.NORTH);
		centerMakeReservationPanel.add(availabilityPanel, BorderLayout.CENTER);

		// --- CENTER VIEW RES PANEL --- //
		GuestViewReservationsPanel viewResPanel = new GuestViewReservationsPanel(hotelManager);

		// Card Panel
		JPanel cardPanel = new JPanel(new CardLayout());
		cardPanel.add(centerMakeReservationPanel, makeReservationId);
		cardPanel.add(viewResPanel, viewReservationId);

		// ----HEADER----//
		GuestHeaderPanel headerPanel = new GuestHeaderPanel(mainCardPanel, hotelManager, selectionPanel,
				availabilityPanel, viewResPanel, cardPanel);

		this.add(headerPanel, BorderLayout.NORTH);
		this.add(cardPanel, BorderLayout.CENTER);

	}

	/**
	 * Gets the state identifier for this state.
	 * 
	 * @return the state identifier.
	 */
	public static String getStateIdentifier() {
		return identifier;
	}
}
