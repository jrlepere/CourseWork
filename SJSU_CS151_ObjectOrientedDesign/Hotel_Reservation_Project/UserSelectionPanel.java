import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The UserSelection state panel. The first state of the application.
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class UserSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1256416L;
	private static String identifier = "UserSelectionPanel";

	/**
	 * Creates a UserSelectionPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel.
	 */
	public UserSelectionPanel(final MainCardPanel mainCardPanel) {
		this.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel(new GridLayout(1, 2, 50, 50));
		headerPanel.setBorder(new EmptyBorder(200, 150, 250, 150));

		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, ManagerPanel.getStateIdentifier());
			}
		});
		JButton guestButton = new JButton("Guest");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, GuestLogInPanel.getStateIdentifier());
			}
		});

		headerPanel.add(managerButton);
		headerPanel.add(guestButton);
		this.add(headerPanel, BorderLayout.CENTER);
	}

	/**
	 * Gets the state identifier of the panel.
	 * 
	 * @return the state identifier of the panel.
	 */
	public static String getStateIdentifier() {
		return identifier;
	}

}