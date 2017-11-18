import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Custom laser object of death
 */
public class Laser implements FallingObject {

	
	int x,y,length,width;
	
	int velocity;
	
	Timer startMoveDownTimer;
	Timer moveDownTimer;
	
	private Box parentBox;
	
	/**
	 * Constructor for the laser object
	 * @param startTime, the start Time of when it spawns
	 * @param velocity, the velocity
	 * @param position, the position where it spawns
	 * @param maxY, the highest y value
	 * @param parent, the parent box
	 */
	public Laser(int startTime, int velocity, int position, int maxY, Box parent) {
		this.x = 600;
		this.y = position * 30 + 20;
		this.length = 10;
		this.width = 50;
		
		this.parentBox = parent;
		
		this.velocity = velocity;
		
		ActionListener start = new StartMoveDownTimer();
		this.startMoveDownTimer = new Timer(startTime,start);
		this.startMoveDownTimer.start();
		
		ActionListener moveDown = new MoveDownTimer();
		this.moveDownTimer = new Timer(100, moveDown);
	}
	
	class MoveDownTimer implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			x -= velocity;
			if (isDead()) {
				System.out.println("Dead");
				parentBox.dead();
			}
		}
	}
	
	/**
	 * timer for the travel of the laser
	 */
	class StartMoveDownTimer implements ActionListener {
		
		/**
		 * starts the timer that controls the speed it moves
		 */
		public void actionPerformed(ActionEvent e) {
			moveDownTimer.start();
			startMoveDownTimer.stop();
		}
	}
	
	/**
	 * draws the laser
	 */
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle icicle = new Rectangle(x,y,width,length);
		
		g2.setColor(Color.green);
		g2.fill(icicle);
		
	}

	/**
	 * stops the laser
	 */
	public void stop() {
		if (startMoveDownTimer.isRunning()) {
			startMoveDownTimer.stop();
		}
	}

	/**
	 * checks to see if the moveDownTimer is running
	 * @returns true if it is, false if it is not
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
	 * gets the name of the object
	 * @returns "Icicle"
	 */
	public String getName() {
		return "Icicle";
	}

	/**
	 * checks to see if the player is dead
	 * @return true if player is dead, false otherwise
	 */
	public boolean isDead() {
		int boxX = parentBox.x;
		int boxY = parentBox.y;
		int boxLength = parentBox.LENGTH;
	
		if ((boxY == y) && boxX < x + width && boxX > x) {
			return true;
		}
		return false;
	}
	
	/**
	 * stops the movement and removes the object
	 */
	public void stopAndRemove() {
		startMoveDownTimer.stop();
		moveDownTimer.stop();
	}
	
}
