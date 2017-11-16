import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A frame for the calendar grid to select a date.
 * 
 * @author JLepere2 
 * Version 1.1
 */
public class CalendarGridFrame extends JFrame {

	private static final long serialVersionUID = 11531L;
	private static int FRAME_SIZE = 300;
	private static String FRAME_TITLE = "Select Date";

	/**
	 * Creates a CalendarGridFrame.
	 * 
	 * @param theTextField
	 *            the text field clicked to set the date.
	 * @param otherTextField
	 *            the other text field (To or From).
	 * @param settingFrom
	 *            true if setting the from text field, false if setting the to
	 *            text field.
	 */

	public CalendarGridFrame(final JTextField theTextField, final JTextField otherTextField,
			final boolean settingFrom) {

		// ----Panel Characteristics
		this.setTitle(FRAME_TITLE);
		this.setSize(FRAME_SIZE, FRAME_SIZE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);

		// ---- Current Day Label
		final JLabel currentDayLabel = new JLabel();

		// ----Grid Component

		final CalendarGridComponent.Guest gridComp = new CalendarGridComponent.Guest(currentDayLabel);
		// -------SOUTH PANEL-------//
		JPanel southPanel = new JPanel(new BorderLayout());
		currentDayLabel.setText(gridComp.getCurrentDayLong());
		currentDayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JButton enterButton = new JButton("ENTER");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (settingFrom) {
					if (shouldSetOther(gridComp.getCurrentDayShort(), otherTextField, settingFrom)) {
						otherTextField.setText(gridComp.getCurrentDayShort());
						otherTextField.repaint();
					}
				} else {
					if (shouldSetOther(gridComp.getCurrentDayShort(), otherTextField, settingFrom)) {
						otherTextField.setText(gridComp.getCurrentDayShort());
						otherTextField.repaint();
					}
				}
				theTextField.setText(gridComp.getCurrentDayShort());
				theTextField.repaint();
				dispatch();
			}
		});
		southPanel.add(currentDayLabel, BorderLayout.NORTH);
		southPanel.add(enterButton, BorderLayout.SOUTH);

		// ------NORTH PANEL------//
		JPanel northPanel = new JPanel(new FlowLayout());
		JButton previousMonthButton = new JButton("   <   ");
		previousMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(false);
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
			}
		});
		JButton nextMonthButton = new JButton("   >   ");
		nextMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(true);
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
			}
		});
		northPanel.add(previousMonthButton);
		northPanel.add(nextMonthButton);

		// ----Add Panels
		this.add(northPanel, BorderLayout.NORTH);
		this.add(gridComp, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Dispatch the frame.
	 */
	private void dispatch() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Checks if the previously updated date over rid the other date
	 * 
	 * @param theTextFieldText
	 *            the newly updated text field
	 * @param otherTextField
	 *            the other text field
	 * @param settingFrom
	 *            setting the from field or the to field
	 * @return true if the other text field should be set to the newly updated
	 *         text field.
	 */
	private boolean shouldSetOther(String theTextFieldText, JTextField otherTextField, boolean settingFrom) {
		if (settingFrom) {
			if (new MyDate(theTextFieldText).compareTo(new MyDate(otherTextField.getText())) > 0) {
				return true;
			}
		} else {
			if (new MyDate(theTextFieldText).compareTo(new MyDate(otherTextField.getText())) < 0) {
				return true;
			}
		}
		return false;
	}

}
