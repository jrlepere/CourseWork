import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public abstract class CompoundShape extends SelectableShape {

	private GeneralPath path;
	
	public CompoundShape() {
		this.path = new GeneralPath();
	}
	
	public void draw(Graphics2D g2) {
		g2.draw(path);
	}
	
	public void translate(int dx, int dy) {
		path.transform(AffineTransform.getTranslateInstance(dx, dy));
	}
	
	public boolean contains(Point2D x) {
		return path.contains(x);
	}
	
	/**
	 * Add a Shape to
	 * @param s
	 */
	public void add(Shape s) {
		path.append(s, false);
	}
	
}
