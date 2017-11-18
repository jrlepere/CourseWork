import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Custom icicle object of falling death
 */
public class Icicle implements FallingObject {

	int x,y,length,width;
	
	int velocity;
	
	Timer startMoveDownTimer;
	Timer moveDownTimer;
	
	private Box parentBox;
	
	/**
	 * Constructor for the icicle object
	 * @param startTime, when it will fall
	 * @param velocity, the speed it falls at
	 * @param position, where it will fall
	 * @param maxY, height it will fall
	 * @param parent, parent box
	 */
	public Icicle(int startTime, int velocity, int position, int maxY, Box parent) {
		
		this.x = position*30 + 10;
		this.y = -50;
		this.length = 50;
		this.width = 10;
		
		this.parentBox = parent;
		
		this.velocity = 0;
		
		ActionListener start = new StartMoveDownTimer();
		this.startMoveDownTimer = new Timer(startTime,start);
		this.startMoveDownTimer.start();
		
		ActionListener moveDown = new MoveDownTimer();
		this.moveDownTimer = new Timer(100, moveDown);
	}
	
	/**
	 * draws the icicle
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle laser = new Rectangle(x,y,width,length);
		
		g2.setColor(Color.blue);
		g2.fill(laser);
		
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
	 * stops the timer and removes the object
	 */
	public void stopAndRemove() {
		startMoveDownTimer.stop();
		moveDownTimer.stop();
	}

	/**
	 * check if the timer is running
	 * @return true if it is running, false if it is not
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
	 * class to let the icicle fall
	 */
	class MoveDownTimer implements ActionListener {
		
		/**
		 * method that allows the icicle to fall
		 */
		public void actionPerformed(ActionEvent e) {
			y += velocity;
			if (isDead()) {
				System.out.println("Dead");
				parentBox.dead();
			}
			velocity ++;
		}
	}
	
	/**
	 * starts the timer for the dropping icicle
	 */
	class StartMoveDownTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			moveDownTimer.start();
			startMoveDownTimer.stop();
		}
	}
	
	/**
	 * checks to see if the player is dead
	 * @return true if they are, false if they are not
	 */
	public boolean isDead() {
		int boxX = parentBox.x;
		int boxY = parentBox.y;
		int boxLength = parentBox.LENGTH;
	
		if (boxX == x && boxY < y + length && boxY > y) {
			return true;
		}
		return false;
	}

	/**
	 * gets the name of the object
	 * @return "Laser"
	 */
	public String getName() {
		return "Laser";
	}
	
}
