import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The frame for the calendar application. Contains all of the components.
 * @author JLepere2
 * Version 1.1
 */
public class CalendarFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int FRAME_WIDTH = 550;
	private static int FRAME_HEIGHT = 700;
	private static String FILENAME = "events.txt";
	
	/**
	 * Creates a CalendarFrame object.
	 * @param calendar the calendar model
	 */
	public CalendarFrame(MyCalendar calendar) {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Calendar GUI");
		this.setLocationRelativeTo(null);
		
		//-----TOP PANEL
		JPanel topPanel = new JPanel();
		JButton quitButton = new JButton("SAVE & QUIT");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndQuit(calendar);
			}
		});
		JButton nextDayButton = new JButton(">");
		nextDayButton.setFocusPainted(false);
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar.nextDay(true);
			}
		}); 
		JButton previousDayButton = new JButton("<");
		previousDayButton.setFocusPainted(false);
		previousDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar.nextDay(false);
			}
		}); 
		JButton createButton = new JButton("CREATE");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEventFrame createEventFrame = new CreateEventFrame(calendar);
				createEventFrame.setVisible(true);
			}
		});
		topPanel.setLayout(new FlowLayout());
		topPanel.add(quitButton);
		topPanel.add(previousDayButton);
		topPanel.add(nextDayButton);
		topPanel.add(createButton);
		
		//---Main Top Panel
		JPanel mainTopPanel = new JPanel(new BorderLayout());
		mainTopPanel.add(topPanel, BorderLayout.CENTER);
		mainTopPanel.add(new CurrentMonthLabel(calendar), BorderLayout.NORTH);
		
		//-----CENTER GRID
		JPanel centerPanel = new JPanel(new GridBagLayout());
		CalendarGrid calendarGrid = new CalendarGrid(calendar);
		GridBagConstraints gridCons = new GridBagConstraints();
		gridCons.gridx = 0;
		gridCons.gridy = 0;
		gridCons.gridwidth = 1;
		gridCons.gridheight = 1;
		gridCons.fill = GridBagConstraints.BOTH;
		gridCons.weightx = 1;
		gridCons.weighty = 0.35;
		centerPanel.add(calendarGrid, gridCons);
		CalendarViewComponent calendarViewComp = new CalendarViewComponent(calendar, FRAME_WIDTH, FRAME_HEIGHT);
		GridBagConstraints viewCons = new GridBagConstraints();
		viewCons.gridx = 0;
		viewCons.gridy = 1;
		viewCons.gridwidth = 1;
		viewCons.gridheight = 1;
		viewCons.fill = GridBagConstraints.BOTH;
		viewCons.weightx = 1;
		viewCons.weighty = 0.65;
		centerPanel.add(new JScrollPane(calendarViewComp), viewCons);
		
		//-----MAIN CENTER PANEL
		JPanel mainCenterPanel = new JPanel(new BorderLayout());
		mainCenterPanel.add(centerPanel, BorderLayout.CENTER);
		
		this.add(mainTopPanel,BorderLayout.NORTH);
		this.add(mainCenterPanel, BorderLayout.CENTER);
		this.add(new EventsLabel(calendar), BorderLayout.SOUTH);
		
	}
	
	/**
	 * Saves the data in the events.txt file.
	 * @param calendar the calendar model
	 */
	private void saveAndQuit(MyCalendar calendar) {
		
		String dataToSave = calendar.getEventsToSave();
		
		try {
			
			FileWriter writer = new FileWriter(new File(FILENAME));
			writer.write(dataToSave);
			writer.close();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
