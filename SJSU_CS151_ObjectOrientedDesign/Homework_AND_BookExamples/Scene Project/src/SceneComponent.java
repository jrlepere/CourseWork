import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * A Scene component acting as a canvas for the SceneShape
 * @author JLepere2
 * Version 1.1
 */
public class SceneComponent extends JComponent {

	private ArrayList<SceneShape> shapes;
	private Point mousePoint;
	
	/**
	 * Creates a Scene Component without any initial Shapes.
	 */
	public SceneComponent() {
		this.shapes = new ArrayList<>();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mousePoint = e.getPoint();
				for (SceneShape s : shapes) {
					if (s.contains(mousePoint)) {
						s.setSelected(!s.isSelected());
					}
				}
				repaint();
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point lastMousePoint = mousePoint;
				mousePoint = e.getPoint();
				double dx = mousePoint.getX() - lastMousePoint.getX();
				double dy = mousePoint.getY() - lastMousePoint.getY();
				for (SceneShape s : shapes) {
					if (s.isSelected()) {
						s.translate((int) dx, (int) dy);
					}
				}
				repaint();
			}
		});
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for (SceneShape s : shapes) {
			if (s.isSelected()) {
				s.drawSelection(g2);
			} else {
				s.draw(g2);
			}
		}
	}
	
	/**
	 * Adds a shape to the scene.
	 * @param s the shape to add to the scene.
	 */
	public void add(SceneShape s) {
		shapes.add(s);
		repaint();
	}
	
	/**
	 * Removes the selected shapes from the scene.
	 */
	public void removeSelected() {
		for (int i = shapes.size()-1; i >= 0; i --) {
			if (shapes.get(i).isSelected()) {
				shapes.remove(i);
			}
		}
		repaint();
	}
	
	/**
	 * Selects or deselects all of the shapes in the scene.
	 * @param selected true to select, false to deselect.
	 */
	public void selectAll(boolean selected) {
		for (SceneShape s : shapes) {
			if (selected) {
				s.setSelected(true);
			} else {
				s.setSelected(false);
			}
		}
		repaint();
	}

}
