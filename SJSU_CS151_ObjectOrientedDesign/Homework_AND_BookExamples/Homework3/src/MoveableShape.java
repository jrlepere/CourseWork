import java.awt.*;

/**
   A shape that can move.
*/
public interface MoveableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
    * Moves the shape.
    * @param dx the distance in the x direction
    * @param dy the distance in the y direction
    */
   void move(int dx, int dy);
   /**
    * Resets the shape to the initial position.
    */
   void reset();
   /**
    * Checks if the shape is out of frame.
    * @param frameWidth the frame width
    * @return true if the shape is outside the width, false otherwise
    */
   boolean isOffScreen(int frameWidth);
}
