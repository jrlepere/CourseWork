import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class creates a view with three buttons, Red Green and Blue.
 * When the button is pressed, a circle is changed to the color pressed.
 * @author JLepere2
 *
 */
public class RGBButtonTester {

	private static int FRAME_SIZE = 300;
	public static String RED = "Red";
	public static String GREEN = "Green";
	public static String BLUE = "Blue";
	private static RGBCircleIconComponent[] circles;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("RGB");
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		
		circles = new RGBCircleIconComponent[4];
		circles[0] = new RGBCircleIconComponent(RED);
		circles[1] = new RGBCircleIconComponent(RED);
		circles[2] = new RGBCircleIconComponent(RED);
		circles[3] = new RGBCircleIconComponent(RED);
		
		JButton redButton = new JButton(RED);
		redButton.addActionListener(colorListener(RED));
		
		JButton greenButton = new JButton(GREEN);
		greenButton.addActionListener(colorListener(GREEN));
		
		JButton blueButton = new JButton(BLUE);
		blueButton.addActionListener(colorListener(BLUE));
		
		frame.setLayout(new FlowLayout());
		
		frame.add(circles[0]);
		frame.add(redButton);
		frame.add(circles[1]);
		frame.add(greenButton);
		frame.add(circles[2]);
		frame.add(blueButton);
		frame.add(circles[3]);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private static ActionListener colorListener(String color) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < circles.length; i ++) {
					circles[i].changeColor(color);
					circles[i].repaint();
				}
			}
		};
	}
	
}
