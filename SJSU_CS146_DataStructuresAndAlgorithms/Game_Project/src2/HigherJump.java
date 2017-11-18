import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * class for the HigherJump powerup
 */
public class HigherJump extends JComponent implements PowerUp {

	Timer startPowerUpTimer;
	
	int x,y,length;
	boolean canDraw;
	Box parentBox;
	
	/**
	 * HigherJump constructor
	 * @param x, the x location
	 * @param y, the y location
	 * @param delay, the delay
	 * @param parentBox, the parent
	 */
	public HigherJump(int x, int y, int delay, Box parentBox) {
		this.x = x;
		this.y = y;
		this.length = 15;
		
		StartAction startAction = new StartAction();
		startPowerUpTimer = new Timer(delay, startAction);
		startPowerUpTimer.start();
		
		canDraw = false;
		
		this.parentBox = parentBox;
	}
	
	/**
	 * draws the powerup
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (canDraw) {
			Graphics2D g2 = (Graphics2D) g;
			
			Rectangle powerUpBorder = new Rectangle(x,y,15,15);
			g2.setColor(Color.BLUE);
			g2.fill(powerUpBorder);
			Rectangle powerUpMiddle = new Rectangle(x+2,y+2,11,11);
			g2.setColor(Color.WHITE);
			g2.fill(powerUpMiddle);
			Rectangle powerUpCenter = new Rectangle(x+4,y+4,7,7);
			g2.setColor(Color.RED);
			g2.fill(powerUpCenter);
			
		}
	}	
	
	/**
	 * hides the powerup
	 */
	public void hide() {
		canDraw = false;
	}
	
	/**
	 * checks to see if can draw the powerup at location
	 * @returns true, if you can, false if you can not
	 */
	public boolean canDraw() {
		return canDraw;
	}
	
	/**
	 * starts the spawn for the powerup
	 */
	class StartAction implements ActionListener {

		/**
		 * starts the timer and sets canDraw to true
		 */
		public void actionPerformed(ActionEvent e) {
			
			startPowerUpTimer.stop();
			canDraw = true;
		}
	}
	
	/**
	 * checks to see if the player is at the powerup
	 */
	public boolean atPowerUp() {
		
		int boxX = parentBox.x;
		int boxY = parentBox.y;
		int boxLength = Box.LENGTH;
		
		if (boxX > x && boxX < (x + length) && boxY > y && boxY < (y + length)) {
			return true;
		}
		if ((boxX + boxLength) > x && (boxX + boxLength) < (x + length) && boxY > y && boxY < (y + length)) {
			return true;
		}
		if ((boxX + boxLength) > x && (boxX + boxLength) < (x + length) && (boxY + boxLength) > y && (boxY + boxLength) < (y + length)) {
			return true;
		}
		if (boxX > x && boxX < (x + length) && (boxY + boxLength) > y && (boxY + boxLength) < (y + length)) {
			return true;
		}
		return false;
	}
	
	/**
	 * activates the powerup, sets the players jump to 15
	 */
	public void activate() {
		parentBox.currentMaxJumpVelocity = 15;
	}
	
}
