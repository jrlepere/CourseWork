import java.awt.Rectangle;
/**
 * A subclass of the class Rectangle
 */
public class BetterRectangle extends Rectangle
{
    /**
     * Creates a BetterRectangle object with given parameters
     * @param x the top left x coord of the rectangle
     * @param y the top left y coord of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public BetterRectangle(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }
    
    /**
     * Gets the perimeter of the rectangle
     * @return the perimeter
     */
    public int getPerimeter()
    {
        return 2 * (width + height);
    }
    
    /**
     * Gets the area of the rectangle
     * @return the area
     */
    public int getArea()
    {
        return width * height;
    }
}