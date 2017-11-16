import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A log in panel for the guest.
 * 
 * @author Jake Lepere 
 * Version 1.1
 */
public class GuestLogInPanel extends JPanel {

	private static final long serialVersionUID = 115643L;
	private static String signInId = "SignIn";
	private static String signUpId = "SignUp";
	private static String identifier = "GuestLogInPanel";
	private static String firstNameText = "First Name";
	private static String lastNameText = "Last Name";
	private static String accountIdText = "Account Id";

	/**
	 * Creates a GuestLogInPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel.
	 * @param hotelManager
	 *            the hotel manager.
	 */
	public GuestLogInPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

		this.setLayout(new BorderLayout());

		// -------FRAME CENTER PANEL------//
		final JPanel frameCenterPanel = new JPanel(new CardLayout());
		frameCenterPanel.setVisible(false);

		// -----SIGN UP CARD PANEL-----//
		JPanel signUpCardPanel = new JPanel(new GridLayout(8, 1));
		signUpCardPanel.setBorder(new EmptyBorder(100, 100, 0, 100));

		final JTextField firstNameTextField = new JTextField(firstNameText);
		firstNameTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				firstNameTextField.setText("");
			}
		});
		final JTextField lastNameTextField = new JTextField(lastNameText);
		lastNameTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lastNameTextField.setText("");
			}
		});
		JButton userSignUpButton = new JButton("Sign Up");
		userSignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				GuestAccount account = hotelManager.addGuestAccount(firstName, lastName);
				JOptionPane.showMessageDialog(null, firstName + " " + lastName + ", you have successfully signed up!\n"
						+ "" + "Your ID number is: " + (GuestAccount.getCount()));
				mainCardPanel.setCurrentAccount(account);
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, MainGuestViewPanel.getStateIdentifier());
				firstNameTextField.setText(lastNameText);
				lastNameTextField.setText(firstNameText);
			}
		});
		signUpCardPanel.add(firstNameTextField);
		signUpCardPanel.add(lastNameTextField);
		signUpCardPanel.add(userSignUpButton);

		// -----SIGN IN CARD PANEL------//
		JPanel signInCardPanel = new JPanel(new GridLayout(8, 1));
		signInCardPanel.setBorder(new EmptyBorder(100, 100, 0, 100));
		final JTextField userIdTextField = new JTextField("Account Id");
		userIdTextField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				userIdTextField.setText("");
			}
		});
		JButton userSignInButton = new JButton("Sign In");
		userSignInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idNumber = Integer.parseInt(userIdTextField.getText());
					GuestAccount account = hotelManager.validIdForLogIn(idNumber);
					if (account != null) {
						JOptionPane.showMessageDialog(null,
								"Welcome back, " + account.getFirstName() + " " + account.getLastName() + "!");
						mainCardPanel.setCurrentAccount(account);
						((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel,
								MainGuestViewPanel.getStateIdentifier());
						userIdTextField.setText(accountIdText);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid ID");
					}

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid ID");
				}
			}
		});
		signInCardPanel.add(userIdTextField);
		signInCardPanel.add(userSignInButton);

		frameCenterPanel.add(signUpCardPanel, signUpId);
		frameCenterPanel.add(signInCardPanel, signInId);

		// -------HEADER PANEL---------//
		JPanel headerPanel = new JPanel(new GridLayout(1, 3));
		headerPanel.setPreferredSize(new Dimension(0, 50));
		headerPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) mainCardPanel.getLayout()).show(mainCardPanel, UserSelectionPanel.getStateIdentifier());
				frameCenterPanel.setVisible(false);
				firstNameTextField.setText(firstNameText);
				lastNameTextField.setText(lastNameText);
				userIdTextField.setText(accountIdText);
			}
		});
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frameCenterPanel.getLayout()).show(frameCenterPanel, signUpId);
				frameCenterPanel.setVisible(true);
			}
		});
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frameCenterPanel.getLayout()).show(frameCenterPanel, signInId);
				frameCenterPanel.setVisible(true);
			}
		});
		headerPanel.add(backButton);
		headerPanel.add(signUpButton);
		headerPanel.add(signInButton);

		this.add(frameCenterPanel, BorderLayout.CENTER);
		this.add(headerPanel, BorderLayout.NORTH);

	}

	/**
	 * Gets the state identifier for this state panel.
	 * 
	 * @return the state identifier.
	 */
	public static String getStateIdentifier() {
		return identifier;
	}

}
