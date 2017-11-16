import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * A square component that is runnable.
 * @author JLepere2
 * Version 1.1
 */
public class Square extends AbstractSceneShape {
	
	/**
	 * Creates a new Square Component
	 */
	public Square(SceneComponent theSceneComponent) {
		super(theSceneComponent);
	}

	public void drawShape(Graphics2D g2) {
		g2.draw(new Rectangle2D.Double(x, y, SIZE, SIZE));
	}
	
}
