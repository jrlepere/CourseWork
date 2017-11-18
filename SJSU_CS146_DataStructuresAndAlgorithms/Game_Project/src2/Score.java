import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Custom score class
 */
public class Score extends JComponent {
	int x,y,score;
	
	Box parentBox;
	
	/**
	 * Constructor for Score object
	 * @param x, the x value
	 * @param y, the y value
	 * @param parentBox, the parent box
	 */
	public Score(int x, int y, Box parentBox) {
		this.x = x;
		this.y = y;
		this.score = 0;
		
		this.parentBox = parentBox;
	}
	
	/**
	 * increments the score
	 * @param scoreIncrement, the amount to increment
	 */
	public void incrementScore(int scoreIncrement) {
		this.score += scoreIncrement;
	}
	
	/**
	 * draws the score 
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial Bold", Font.BOLD, 12));
		g2.drawString("Score: " + score, x, y);
		
	}
	
	/**
	 * draws the dead screen text
	 * @param g
	 */
	public void drawDead(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial Bold", Font.BOLD, 25));
		g2.drawString("Score: " + score, 75, 150);
		
		if (parentBox.canRestart) {
			g2.setFont(new Font("Arial Bold", Font.BOLD, 25));
			g2.drawString("Press space to restart", 50, 200);
		}
	}
}
