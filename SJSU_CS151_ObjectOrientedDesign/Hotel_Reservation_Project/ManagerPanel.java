import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A panel for the manager state of the application
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class ManagerPanel extends JPanel {

	private static final long serialVersionUID = 1531L;
	private static String identifier = "ManagerPanel";

	/**
	 * Creates a ManagerPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel.
	 * @param hotelManager
	 *            the hotel manager.
	 */
	public ManagerPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

		this.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel(new GridLayout(1, 3));
		headerPanel.setPreferredSize(new Dimension(0, 50));
		headerPanel.setBorder(new EmptyBorder(0, 50, 0, 50));

		final ManagerViewPanel managerView = new ManagerViewPanel(mainCardPanel, hotelManager);
		managerView.setVisible(false);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managerView.setVisible(false);
				managerView.revalidate();
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, UserSelectionPanel.getStateIdentifier());
			}
		});

		JButton loadViewButton = new JButton("Load/View Reservations");
		loadViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managerView.setVisible(true);
				managerView.revalidate();
			}
		});

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCardPanel.saveData(hotelManager);
			}
		});

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCardPanel.quitApplication();
			}
		});

		headerPanel.add(backButton);
		headerPanel.add(loadViewButton);
		headerPanel.add(saveButton);
		headerPanel.add(quitButton);
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(managerView, BorderLayout.CENTER);

	}

	/**
	 * Gets the state identifier for this panel.
	 * 
	 * @return the state identifier for this panel.
	 */
	public static String getStateIdentifier() {
		return identifier;
	}
}
