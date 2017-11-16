import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * This program shows a clock that is updated once per second.
 * @author JLepere2
 *
 */
public class TimerTester {

	public static void main(String [] args) {
		
		JFrame frame = new JFrame();
		
		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		
		frame.setLayout(new FlowLayout());
		frame.add(textField);
		
		ActionListener listener = new 
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Date now = new Date();
						textField.setText(now.toString());
					}
				};
				
		final int DELAY = 1000;
		Timer t = new Timer(DELAY, listener);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
