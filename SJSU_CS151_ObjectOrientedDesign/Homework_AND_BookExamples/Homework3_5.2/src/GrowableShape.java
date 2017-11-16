import java.awt.*;

/**
   A shape that can grow.
*/
public interface GrowableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
    * Grows the shape.
    * @param newWidth the new width of the shape.
    */
   void grow(int newWidth);
}
