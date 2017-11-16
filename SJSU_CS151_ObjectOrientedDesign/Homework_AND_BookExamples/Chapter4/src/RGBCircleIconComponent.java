import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class RGBCircleIconComponent extends JComponent {

	private RGBCircleIcon icon;
	
	public RGBCircleIconComponent(String color) {
		setPreferredSize(new Dimension(50, 50));
		this.icon = new RGBCircleIcon(color);
	}
	
	public void changeColor(String newColor) {
		icon.changeColor(newColor);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		icon.draw(g2, width, height);
		
	}
	
}
