import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Another approach the the RGB button question
 * @author JLepere2
 * Version 1
 */
public class RGBButtonTester2 {

	private static int FRAME_SIZE = 300;
	private static String RED = "Red";
	private static String GREEN = "Green";
	private static String BLUE = "Blue";
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("RGB");
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		
		MyCircle circle = new MyCircle(RED);
		
		JButton redButton = new JButton(RED);
		redButton.addActionListener(colorChange(RED, circle));
		
		JButton greenButton = new JButton(GREEN);
		greenButton.addActionListener(colorChange(GREEN, circle));
		
		JButton blueButton = new JButton(BLUE);
		blueButton.addActionListener(colorChange(BLUE, circle));
		
		frame.setLayout(new FlowLayout());
		
		frame.add(redButton);
		frame.add(greenButton);
		frame.add(blueButton);
		frame.add(circle);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public static ActionListener colorChange(String color, MyCircle circle) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				circle.setColor(color);
				circle.repaint();
			}
		};
	}
	
	public static class MyCircle extends JComponent {
		
		private String color;
		private int PREFFERED_SIZE = 50;
		
		public MyCircle(String color) {
			this.color = color;
			setPreferredSize(new Dimension(PREFFERED_SIZE, PREFFERED_SIZE));
		}
		
		public void setColor(String color) {
			this.color = color;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			if (this.color.equals(RED)) {
				g2.setColor(Color.RED);
			} else if (this.color.equals(GREEN)) {
				g2.setColor(Color.GREEN);
			} else if (this.color.equals(BLUE)) {
				g2.setColor(Color.BLUE);
			}
			Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, PREFFERED_SIZE, PREFFERED_SIZE);
			g2.fill(circle);
		}
	}
	
}
