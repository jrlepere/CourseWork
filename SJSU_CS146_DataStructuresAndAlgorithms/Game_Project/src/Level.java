import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Level text that also holds the level
 */
public class Level {

	int level;
	
	/**
	 * Level constructor
	 * @param level, the level the player is on
	 */
	public Level(int level) {
		this.level = level;
	}
	
	/**
	 * draws the Level text
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial Bold", Font.BOLD, 50));
		g2.drawString("Level " + level, 75, 100);
	}
}
