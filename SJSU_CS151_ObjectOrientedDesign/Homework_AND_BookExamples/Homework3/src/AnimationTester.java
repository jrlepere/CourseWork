import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This program moves a car across the screen and resets when it reached out of the frame.
 */
public class AnimationTester {

	private static final int FRAME_HEIGHT = 200;
	private static final int FRAME_WIDTH = 400;
	private static final int CAR_WIDTH = 100;
	
	/**
	 * The main method for the program.
	 * @param args the command line arguments. Not Used.
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Slider Tester");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		MoveableShape car = new CarShape(0, 0, CAR_WIDTH);
		JLabel label = new JLabel(new ShapeIcon(car, FRAME_WIDTH, FRAME_HEIGHT));
		
		final int timerDelay = 5;
		final int dx = 1;
		Timer t = new Timer(timerDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.move(dx, 0);
				if (car.isOffScreen(frame.getWidth())) {
					car.reset();
				}
				label.repaint();
			}
		});
		t.start();
		
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
