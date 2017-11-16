import java.awt.Rectangle;

public class BetterRectangle extends Rectangle
{
	/**
	 * Constructs a better rectangle
	 * @param x the top left x coord
	 * @param y the top left y coord
	 * @param width the width
	 * @param height the height
	 */
	public BetterRectangle(int x, int y, int width, int height)
	{
		super(x,y,width,height);
		//super.setLocation(x, y);
		//super.setSize(width, height);
	}
	
	/**
	 * Gets the area 
	 * @return the area
	 */
	public int getArea()
	{
		return (width * height);
	}
	
	/**
	 * Gets the perimeter
	 * @return the perimeter
	 */
	public int getPerimeter()
	{
		return (2*(width + height));
	}
}
