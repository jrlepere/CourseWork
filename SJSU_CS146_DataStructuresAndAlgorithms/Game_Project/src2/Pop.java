import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Pop font that comes up when you beat the level
 */
public class Pop extends JComponent {

	int x,y;
	
	/**
	 * Constructor for pop object
	 * @param x, the x value
	 * @param y, the y value
	 */
	public Pop(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * draws the pop text
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.RED);
		g2.setFont(new Font("Calibri", Font.ITALIC, 12));
		g2.drawString("POP", x, y);
		
	}
	
}
