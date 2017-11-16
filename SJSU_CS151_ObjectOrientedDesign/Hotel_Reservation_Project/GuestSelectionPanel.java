import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The MainGuestSelectionPanel for the GuestView
 * 
 * @author Jake Lepere
 * Version 1.1
 */
public class GuestSelectionPanel extends JPanel {

	private static final long serialVersionUID = 11531L;

	/**
	 * Creates a MainGuestSelectionPanel.
	 * 
	 * @param dateRangeModel
	 *            the date range model.
	 * @param availabilityPanel
	 *            the availability panel.
	 */
	public GuestSelectionPanel(final DateRangeReservationModel dateRangeModel,
			final GuestAvailabilityPanel availabilityPanel) {

		// -----INPUT DATE PANEL----///
		JPanel textFieldPanel = new JPanel(new GridLayout(4, 2));
		JLabel checkInLabel = new JLabel(" Check in");
		JLabel checkOutLabel = new JLabel(" Check out");
		final JTextField dateFrom = new JTextField((new MyDate(new Date()).getDateString()));
		final JTextField dateTo = new JTextField((new MyDate(new Date()).getDateString()));
		dateFrom.setEditable(false);
		dateFrom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateFrom, dateTo, true);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(true, new MyDate(dateFrom.getText()));
						dateRangeModel.setDate(false, new MyDate(dateTo.getText()));
					}
				});
			}
		});
		dateTo.setEditable(false);
		dateTo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CalendarGridFrame gridFrame = new CalendarGridFrame(dateTo, dateFrom, false);
				gridFrame.setVisible(true);
				gridFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dateRangeModel.setDate(false, new MyDate(dateTo.getText()));
						dateRangeModel.setDate(true, new MyDate(dateFrom.getText()));
					}
				});
			}
		});
		textFieldPanel.add(checkInLabel);
		textFieldPanel.add(checkOutLabel);
		textFieldPanel.add(dateFrom);
		textFieldPanel.add(dateTo);

		// ----ROOM CHOICE PANEL----
		JPanel roomChoicePanel = new JPanel();
		JLabel roomTypeLabel = new JLabel("Room type:");
		JButton luxuriousRoomButton = new JButton("$" + LuxuriousRoom.PRICE);
		luxuriousRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(false, true);
				availabilityPanel.setVisible(true);
				availabilityPanel.repaint();
			}
		});

		JButton economicRoomButton = new JButton("$" + EconomicRoom.PRICE);
		economicRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateRangeModel.setRoomSelected(true, true);
				availabilityPanel.setVisible(true);
				availabilityPanel.repaint();
			}
		});
		roomChoicePanel.add(roomTypeLabel);
		roomChoicePanel.add(luxuriousRoomButton);
		roomChoicePanel.add(economicRoomButton);

		textFieldPanel.add(roomChoicePanel);
		this.add(textFieldPanel, BorderLayout.NORTH);

	}

}
