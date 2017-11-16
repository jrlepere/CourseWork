import java.awt.*;
import javax.swing.*;

/**
 * An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon {
	
	/**
	 * Creates a Shape Icon.
	 * @param shape the Growable Shape.
	 * @param width the width.
	 * @param height the height.
	 */
	public ShapeIcon(GrowableShape shape, int width, int height) {
		this.shape = shape;
		this.width = width;
		this.height = height;
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}

	private int width;
	private int height;
	private GrowableShape shape;
}
