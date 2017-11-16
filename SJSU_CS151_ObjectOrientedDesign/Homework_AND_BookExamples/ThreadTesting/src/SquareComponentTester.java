import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Test with Threads
 * @author JLepere2
 * Version 1.1
 */
public class SquareComponentTester {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 300;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("THREAD TESTER");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		SceneComponent sceneComponent = new SceneComponent();
		ArrayList<Runnable> runnables = new ArrayList<>();
		
		final int count = 5;
		for (int i = 0; i < count; i ++) {
			Square s = new Square(sceneComponent);
			sceneComponent.addShape(s);
			runnables.add(s);
		}
		
		for (int i = 0; i < count; i ++) {
			Thread t = new Thread(runnables.get(i));
			t.start();
		}
		
		frame.add(sceneComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
