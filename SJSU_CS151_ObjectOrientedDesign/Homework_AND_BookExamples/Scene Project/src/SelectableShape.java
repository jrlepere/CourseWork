import java.awt.BasicStroke;
import java.awt.Graphics2D;

/**
 * An abstract class for a selectable shape that can be put of the scene.
 * @author JLepere2
 * Version 1.1
 */
public abstract class SelectableShape implements SceneShape {
	
	private boolean selected;
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void drawSelection(Graphics2D g2) {
		final int newStrokeSize = 3;
		final int originalStrokeSize = 1;
		g2.setStroke(new BasicStroke(newStrokeSize));
		draw(g2);
		g2.setStroke(new BasicStroke(originalStrokeSize));
	}
	
}
