import java.awt.geom.Ellipse2D;

public class CircleShape extends CompoundShape {

	/**
	 * Creates a triangle.
	 * @param x the top left x coord
	 * @param y the top left y coord
	 * @param width the width
	 */
	public CircleShape(int x, int y, int width) {
		this.add(new Ellipse2D.Double(x, y, width, width));
	}

}
