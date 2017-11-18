import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * Custom Block object class
 */
public class Block extends JComponent implements FallingObject {

	int x,y, length, maxY, velocity, pos;
	public static int totalRunning = 0;
	Timer startMoveDownTimer, moveDownTimer;
	private Box parentBox;
	Color boxColor;
	
	/**
	 * creates a block
	 * @param startTime, when it drops
	 * @param velocity, speed it drops at
	 * @param position, where it drops at
	 * @param maxY, the position
	 * @param parent, the main box
	 */
	public Block(int startTime, int velocity, int position, int maxY, Box parent) {
		
		this.x = 30*position;
		this.y = 0;
		this.length = 30;
		this.velocity = velocity;
		this.maxY = maxY;
		this.parentBox = parent;
		this.pos = position;
		
		ActionListener start = new StartMoveDownTimer();
		this.startMoveDownTimer = new Timer(startTime,start);
		this.startMoveDownTimer.start();
		
		ActionListener moveDown = new MoveDownTimer();
		this.moveDownTimer = new Timer(100, moveDown);
		
		Random gen = new Random();
		this.boxColor = new Color(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));
	}
	
	
	/**
	 * Start the timer for the block to move down
	 */
	class MoveDownTimer implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (y >= maxY - velocity) {
				y = maxY;
				moveDownTimer.stop();
				parentBox.maxYatPos[pos] = maxY;
				if (pos > 0) {
					parentBox.maxXatHeight[maxY/30][pos - 1] = (pos * 30) ;
				}
				if (pos < 11) {
					parentBox.minXatHeight[maxY/30][pos + 1] = (pos + 1) * 30;
				}
			} else {
				y += velocity;
			}
			if (isDead()) {
				parentBox.dead();
			}
			if (smash()) {
				parentBox.stopJumping();
				parentBox.y = y + length;
			}
		}
	}
	
	/**
	 * starts the timer to drop the block
	 */
	class StartMoveDownTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			moveDownTimer.start();
			startMoveDownTimer.stop();
			totalRunning++;
		}
	}
	
	/**
	 * method to draw the blocks
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle block = new Rectangle(x,y,length,length);
		
		g2.setColor(boxColor);
		g2.fill(block);
		
		Line2D.Double line1 = new Line2D.Double(x,y,x,y+length);
		Line2D.Double line2 = new Line2D.Double(x+length,y,x+length,y+length);
		Line2D.Double line3 = new Line2D.Double(x,y,x+length,y);
		Line2D.Double line4 = new Line2D.Double(x,y+length,x+length,y+length);
		Line2D.Double line5 = new Line2D.Double(x+(length/3),y,x+(length/3),y+length);
		Line2D.Double line6 = new Line2D.Double(x+(2*(length)/3),y,x+(2*(length)/3),y+length);
		Line2D.Double line7 = new Line2D.Double(x,y+(length/3),x+length,y+(length/3));
		Line2D.Double line8 = new Line2D.Double(x,y+(2*(length/3)),x+length,y+(2*(length/3)));
		
		g2.setColor(Color.white);
		g2.draw(line1);
		g2.draw(line2);
		g2.draw(line3);
		g2.draw(line4);
		g2.draw(line5);
		g2.draw(line6);
		g2.draw(line7);
		g2.draw(line8);
		
	}
	
	/**
	 * stops the timer
	 */
	public void stop() {
		if (startMoveDownTimer.isRunning()) {
			startMoveDownTimer.stop();
		}
	}
	
	/**
	 * stops timer and gets ready to remove all the blocks
	 */
	public void stopAndRemove() {
		startMoveDownTimer.stop();
		moveDownTimer.stop();
	}
	
	/**
	 * checks to see if the startTimer is running
	 * @return true if the startTimer is running, false otherwise
	 */
	public boolean startTimerIsRunning() {
		return startMoveDownTimer.isRunning();
	}
	
	/**
	 * stops the startTimer
	 */
	public void stopStartTimer() {
		startMoveDownTimer.stop();
	}
	
	/**
	 * checks to see if the hero is dead
	 * @return true if he's dead, false otherwise
	 */
	public boolean isDead() {
		int boxX = parentBox.x;
		int boxY = parentBox.y;
		int boxLength = parentBox.LENGTH;
		
		if (boxX < x + length && boxX >= x && y == maxY && maxY + length > boxY && y + 10 < boxY) {
			return true;
		}
		return false;
	}
	
	/**
	 * checks to see if player is about to get smashed by a block
	 * @return true if player's about to get smashed, false otherwise
	 */
	public boolean smash() {
	
		int boxX = parentBox.x;
		int boxY = parentBox.y;
		int boxLength = parentBox.LENGTH;
	
		if (boxY < y+20 && boxY > y && boxX < x + length && boxX >= x) {
			return true;
		}
		
		return false;
	}

	/**
	 * gets the name of the object
	 * @return "Block"
	 */
	public String getName() {
		return "Block";
	}
}
