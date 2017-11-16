import java.awt.FlowLayout;

import javax.swing.*;

public class HelloFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8788455458217686779L;
	private static final int FRAME_HEIGHT = 500;
	private static final int FRAME_WIDTH = 500;
	
	public HelloFrame() {
		setTitle("Hello");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//JPanel helloPanel = new HelloPanel();
		setLayout(new FlowLayout());
		//add(helloPanel);
		//pack();
		//JComponent helloComponent = new HelloComponent();
		//add(helloComponent);
		//pack();
		for (int i = 0; i < 5; i ++) {
			add(new HelloComponent(i*10, i*10));
		}
		pack();
	}
	
}
