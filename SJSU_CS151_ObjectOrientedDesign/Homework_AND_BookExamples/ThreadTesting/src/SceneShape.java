import java.awt.Graphics2D;

/**
 * A shape that can go on a scene. SLideable and Runnable
 * @author JLepere2
 * Version 1.1
 */
public interface SceneShape extends SlideableShape, Runnable {
	
	public static int objectCount = 0;
	
	public void drawShape(Graphics2D g2);
	
}
