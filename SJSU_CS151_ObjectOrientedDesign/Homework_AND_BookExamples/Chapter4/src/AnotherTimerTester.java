import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A timer that starts on the press of a button
 * @author JLepere2
 *
 */
public class AnotherTimerTester {
	
	/**
	 * The main method for this program.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle(FRAME_TITLE);
		
		JTextField secondsText = new JTextField(TIME_TEXT_SIZE);
		secondsText.setText("0");
		
		JTextField minutesText = new JTextField(TIME_TEXT_SIZE);
		minutesText.setText("0");
		
		JTextField hoursText = new JTextField(TIME_TEXT_SIZE);
		hoursText.setText("0");
		
		Timer t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = secondsText.getText();
				int seconds = Integer.parseInt(s);
				String m = minutesText.getText();
				int minutes = Integer.parseInt(m);
				String h = hoursText.getText();
				int hours = Integer.parseInt(h);
				if (seconds == 59) {
					seconds = 0;
					if (minutes == 59) {
						hours += 1;
						minutes = 0;
					} else {
						minutes += 1;
					}
				} else {
					seconds += 1;
				}
				
				secondsText.setText(""+seconds);
				minutesText.setText(""+minutes);
				hoursText.setText(""+hours);
			}
		});
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.start();
			}
		});
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}
		});
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondsText.setText("0");
				minutesText.setText("0");
				hoursText.setText("0");
			}
		});
		
		frame.setLayout(new FlowLayout());

		frame.add(startButton);
		frame.add(secondsText);
		frame.add(minutesText);
		frame.add(hoursText);
		frame.add(stopButton);
		frame.add(resetButton);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 100;
	private static final String FRAME_TITLE = "Press to start the timer";
	private static final int TIME_TEXT_SIZE = 10;
	
}
