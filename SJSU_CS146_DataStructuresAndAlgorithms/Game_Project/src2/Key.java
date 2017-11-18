import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JComponent;

public class Key extends JComponent {

	int x,y,length;
	Color keyColor;
	
	Random gen;
	
	boolean canDraw;
	
	public Key() {
		this.length = 16;
		keyColor = Color.yellow;
		
		canDraw = false;
		
		gen = new Random();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (canDraw) {
		
			Ellipse2D.Double key = new Ellipse2D.Double(x,y,length,length);
			
			g2.setColor(keyColor);
			g2.fill(key);
			
			Rectangle keyPin = new Rectangle(x + 5,y+10,6,20);
			g2.fill(keyPin);
			
			Rectangle keyBump = new Rectangle(x + 11, y + 20, 2, 5);
			g2.fill(keyBump);
			
			double sparkleAngle = gen.nextInt(360) + 0.0;
			Line2D.Double sparkle = new Line2D.Double(x + 8, y + 8, x+8 + 16*Math.cos((sparkleAngle/180)* Math.PI), y+8 + 16*Math.sin((sparkleAngle/180)* Math.PI));
			g2.draw(sparkle);
			
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean canDraw() {
		return canDraw();
	}
}
