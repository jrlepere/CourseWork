import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;


public class ClockTester {
	
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
		
		JButton startButton = createTimerManagerButton(t, true);
		JButton stopButton = createTimerManagerButton(t, false);
		JButton resetButton = createResetButton(secondsText, minutesText, hoursText);
		
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
	
	public static JButton createResetButton(JTextField secondsText, JTextField minutesText, JTextField hoursText) {
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondsText.setText("0");
				minutesText.setText("0");
				hoursText.setText("0");
			}
		});
		return resetButton;
	}
	
	public static JButton createTimerManagerButton(Timer t, Boolean start) {
		JButton startButton = new JButton();
		if (start) {
			startButton.setText("Start");
		} else {
			startButton.setText("Stop");
		}
		if (start) {
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t.start();
				}
			});
		} else {
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t.stop();
				}
			});
		}
		return startButton;
	}
	
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 100;
	private static final String FRAME_TITLE = "Press Start to Begin";
	private static final int TIME_TEXT_SIZE = 10;
}
