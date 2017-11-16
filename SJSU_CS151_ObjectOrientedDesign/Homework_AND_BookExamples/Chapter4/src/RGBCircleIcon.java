import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class RGBCircleIcon {

	private String color;
	
	public RGBCircleIcon(String color) {
		this.color = color;
	}
	
	public void changeColor(String color) {
		this.color = color;
	}
	
	public void draw(Graphics2D g2, int width, int height) {
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
		if (color.equals(RGBButtonTester.RED)) {
			g2.setColor(Color.RED);
		} else if (color.equals(RGBButtonTester.GREEN)) {
			g2.setColor(Color.GREEN);
		} else if (color.equals(RGBButtonTester.BLUE)) {
			g2.setColor(Color.BLUE);
		}
		g2.fill(circle);
	}
	
}
