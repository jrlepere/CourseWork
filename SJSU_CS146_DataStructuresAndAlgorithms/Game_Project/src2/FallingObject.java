import java.awt.Graphics;


/**
 * FallingObject interface
 */
public interface FallingObject {
	//Timer startMoveDownTimer;
	//Timer moveDownTimer;
	
	//int pos;
	
	public void draw(Graphics g);
	public void stop();
	public boolean startTimerIsRunning();
	public void stopStartTimer(); 
	public void stopAndRemove();
	
	public String getName();
}
