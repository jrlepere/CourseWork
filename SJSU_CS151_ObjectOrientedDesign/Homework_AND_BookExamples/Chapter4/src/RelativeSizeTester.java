import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class RelativeSizeTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Resize Tester");
		frame.setSize(100, 100);
		
		frame.add(new JComponent() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				int width = this.getWidth();
				int height = this.getHeight();
				Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
				g2.setColor(Color.BLUE);
				g2.fill(circle);
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
