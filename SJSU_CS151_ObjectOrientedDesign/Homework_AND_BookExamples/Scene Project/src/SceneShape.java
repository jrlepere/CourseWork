import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * An interface for shapes that can be in the scene
 * @author JLepere2
 * Version 1.1
 */
public interface SceneShape {

	void setSelected(boolean b);
	boolean isSelected();
	void draw(Graphics2D g2);
	void drawSelection(Graphics2D g2);
	void translate(int dx, int dy);
	boolean contains(Point2D aPoint);
	
}
