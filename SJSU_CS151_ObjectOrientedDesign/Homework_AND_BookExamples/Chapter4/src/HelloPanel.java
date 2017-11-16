import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * A panel displaying the text Hello.
 * @author JLepere2
 *
 */
public class HelloPanel extends JPanel {

	private static final long serialVersionUID = 5601472137546611221L;
	private static String TEXT = "Hello!";
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = g2.getFont().getStringBounds(TEXT, context);
		double x = (getWidth() - bounds.getWidth()) / 2;
	    double y = (getHeight() - bounds.getHeight()) / 2;
		g2.drawString(TEXT, (int) x, (int) y);
		setPreferredSize(new Dimension((int) bounds.getWidth(), (int) bounds.getHeight()));;
	}
	
}
