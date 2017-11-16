import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ShapeIcon implements Icon {
	private int width;
	private int height;
	private MoveableShape shape;
	
	public ShapeIcon(MoveableShape shape, int w, int h) {
		this.shape = shape;
		this.width = w;
		this.height = h;
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
	
}
