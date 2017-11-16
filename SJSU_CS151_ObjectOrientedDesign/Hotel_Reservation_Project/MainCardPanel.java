import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A panel to hold the different state panels of the application.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class MainCardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GuestAccount currentAccount;
	private JFrame mainFrame;

	/**
	 * Creates a new MainCardPanel.
	 * 
	 * @param theMainFrame
	 *            the main frame of this panel.
	 */
	public MainCardPanel(final JFrame theMainFrame) {
		this.mainFrame = theMainFrame;
		this.setLayout(new CardLayout());
	}

	/**
	 * Sets the current guest account of the application.
	 * 
	 * @param theGuestAccount
	 *            the current guest account.
	 */
	public void setCurrentAccount(GuestAccount theGuestAccount) {
		currentAccount = theGuestAccount;
	}

	/**
	 * Gets the current guest account of the application
	 * 
	 * @return the current guest account of the application
	 */
	public GuestAccount getCurrentAccount() {
		return currentAccount;
	}

	/**
	 * Quits the application.
	 */
	public void quitApplication() {
		mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Saves the data.
	 * 
	 * @param hotelManager
	 *            the manager containing the data
	 */
	public void saveData(HotelManager hotelManager) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(new File(HotelReservationSystem.savedDataSerializationFile)));
			out.writeObject(hotelManager);
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
