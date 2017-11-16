import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;

/**
 * The system manager for the program. Contains the main method to run the
 * program.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class HotelReservationSystem {

	public static String savedDataSerializationFile = "Saved.dat";
	private static int FRAME_WIDTH = 800;
	private static int FRAME_HEIGHT = 600;
	private static String FRAME_TITLE = "HOTEL RESERVATION SYSTEM";

	/**
	 * The main method to run the hotel reservation program.
	 * 
	 * @param args
	 *            the command line arguments. Not used.
	 */
	public static void main(String[] args) {

		// Load the Data
		File f = new File(savedDataSerializationFile);
		HotelManager manager = new HotelManager();
		try {
			if (f.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				manager = (HotelManager) in.readObject();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GuestAccount.setGuestAccountCount(manager.getGuestAccountSize());

		// The Main Application Frame
		JFrame mainApplicationFrame = new JFrame();
		mainApplicationFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainApplicationFrame.setTitle(FRAME_TITLE);
		mainApplicationFrame.setLocationRelativeTo(null);
		mainApplicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final HotelManager theManager = manager;
		mainApplicationFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream(new File(HotelReservationSystem.savedDataSerializationFile)));
					out.writeObject(theManager);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// The Main Card Panel
		MainCardPanel cards = new MainCardPanel(mainApplicationFrame);
		cards.add(new UserSelectionPanel(cards), UserSelectionPanel.getStateIdentifier());
		cards.add(new GuestLogInPanel(cards, manager), GuestLogInPanel.getStateIdentifier());
		cards.add(new MainGuestViewPanel(cards, manager), MainGuestViewPanel.getStateIdentifier());
		cards.add(new ManagerPanel(cards, manager), ManagerPanel.getStateIdentifier());

		mainApplicationFrame.add(cards);
		mainApplicationFrame.setVisible(true);
	}

}
