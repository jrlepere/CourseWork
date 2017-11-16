import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * A Frame for the user to create an event.
 * @author JLepere2
 * Version 1.1
 */
public class CreateEventFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int FRAME_WIDTH = 450;
	private static int FRAME_HEIGHT = 120;
	private DateTextDesign eventDateDesign;
	
	/**
	 * Creates a calendar event frame.
	 * @param calendar the calendar model
	 */
	public CreateEventFrame(MyCalendar calendar) {
		
		this.setTitle("CREATE EVENT");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		eventDateDesign = new ShortDateDesign();
		
		JTextField eventTextField = new JTextField();
		
		JPanel southPanel = new JPanel();
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		JTextField dateTextField = new JTextField(eventDateDesign.getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
		JTextField startTimeTextField = new JTextField("12:00am");
		JLabel toLabel = new JLabel("to");
		JTextField endTimeTextField = new JTextField("12:00am");
		JButton saveButton = new SaveEventButton(eventTextField, dateTextField, startTimeTextField, endTimeTextField, calendar, this);
		southPanel.add(cancelButton);
		southPanel.add(dateTextField);
		southPanel.add(startTimeTextField);
		southPanel.add(toLabel);
		southPanel.add(endTimeTextField);
		southPanel.add(saveButton);
		
		this.add(new JScrollPane(eventTextField), BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
}
