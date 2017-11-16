import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * A scene components for the components
 * @author JLepere2
 * Version 1.1
 */
public class SceneComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<SceneShape> shapes;
	
	public SceneComponent() {
		shapes = new ArrayList<>();
	}
	
	public void addShape(SceneShape shape) {
		shapes.add(shape);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (SceneShape shape : shapes) {
			shape.drawShape(g2);
		}
	}
	
}
