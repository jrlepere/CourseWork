import java.awt.Rectangle;

/**
 * A car shape that can be selected on the scene.
 * @author JLepere2
 * Version 1.1
 */
public class SquareShape extends CompoundShape {
	
	/**
	 * Creates a car with provided coordinates and width.
	 * @param x the x coord of the car
	 * @param y the y coord of the car
	 * @param width the width of the car
	 */
	public SquareShape(int x, int y, int width) {
		this.add(new Rectangle(x, y, width, width));
	}

}
