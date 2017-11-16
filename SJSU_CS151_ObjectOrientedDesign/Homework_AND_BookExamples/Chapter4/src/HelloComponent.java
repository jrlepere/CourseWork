import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JComponent;

public class HelloComponent extends JComponent {
	
	private int width;
	private int height;
	
	public HelloComponent(int width, int height) {
		setPreferredSize(new Dimension(width,height));;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Random rand = new Random();
		int red = rand.nextInt(256);
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		g2.setColor(new Color(red,green,blue));
		g2.fill(new Rectangle2D.Double(0,0,width,height));
	}
	
}
