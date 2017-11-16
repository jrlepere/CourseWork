import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This program makes use of the slider. The car grows larger/smaller as the slider moves.
 */
public class SliderTester {

	private static final int FRAME_HEIGHT = 200;
	private static final int FRAME_WIDTH = 296;
	
	/**
	 * The main method for the program.
	 * @param args the command line arguments. Not Used.
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Slider Tester");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		int initialCarWidth = FRAME_WIDTH/2;
		GrowableShape car = new CarShape(0, 0, initialCarWidth);
		JLabel label = new JLabel(new ShapeIcon(car, FRAME_WIDTH, FRAME_HEIGHT/2));
		
		JSlider slider = new JSlider(0, FRAME_WIDTH, initialCarWidth);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				car.grow(((JSlider) e.getSource()).getValue());
				label.repaint();
			}
		});
		
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.add(slider, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
