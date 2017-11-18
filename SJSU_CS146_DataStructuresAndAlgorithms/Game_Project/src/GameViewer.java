import java.awt.Color;

import javax.swing.JFrame;

/**
 * main class
 */
public class GameViewer extends JFrame {
	
	/**
	 * main, runs the game
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("BOX GAME");
		frame.setSize(360, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		
		Box box = new Box();
		
		frame.add(box);
		
		frame.setVisible(true);	
	}
	
    
}
