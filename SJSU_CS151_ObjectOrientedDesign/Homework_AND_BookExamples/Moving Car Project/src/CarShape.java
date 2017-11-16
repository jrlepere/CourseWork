import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CarShape implements MoveableShape {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public CarShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics2D g2) {
		
		final double HEIGHT_OF_BODY_RATIO = 0.5;
		final double HEIGHT_OF_HOOD_RATIO = 0.25;
		final double SIZE_OF_TIRES_RATIO = 0.25;
		final double WIDTH_OF_HOOD_RATIO = 0.75;
		
		// Draw the hood
		double heightOfHood = height * HEIGHT_OF_HOOD_RATIO;
		double widthOfHood = (width*WIDTH_OF_HOOD_RATIO);
		double remainingWidthOfHood = width - widthOfHood;
		Point2D.Double topLeftPoint = new Point2D.Double(x + remainingWidthOfHood/2, y);
		Point2D.Double topRightPoint = new Point2D.Double(x + remainingWidthOfHood/2+widthOfHood, y);
		g2.draw(new Line2D.Double(new Point2D.Double(x, heightOfHood), topLeftPoint));
		g2.draw(new Line2D.Double(topLeftPoint, topRightPoint));
		g2.draw(new Line2D.Double(topRightPoint, new Point2D.Double(x + width,heightOfHood)));
		
		// Draw the body
		double heightOfBody = height * HEIGHT_OF_BODY_RATIO;
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + heightOfHood, width, heightOfBody);
		g2.draw(body);
		
		// Draw the tires
		double sizeOfTires = width * SIZE_OF_TIRES_RATIO;
		Ellipse2D.Double tire1 = new Ellipse2D.Double(x, heightOfHood + heightOfBody, sizeOfTires, sizeOfTires);
		Ellipse2D.Double tire2 = new Ellipse2D.Double(x + width - sizeOfTires, heightOfHood + heightOfBody, sizeOfTires, sizeOfTires);
		g2.draw(tire1);
		g2.draw(tire2);
		
	}

	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}

}
