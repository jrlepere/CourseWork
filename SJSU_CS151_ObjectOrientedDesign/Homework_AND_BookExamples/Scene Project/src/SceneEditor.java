import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * An editor for the scene. Contains the main method for the program.
 * @author JLepere2
 * Version 1.1
 */
public class SceneEditor {

	private static int FRAME_WIDTH = 800;
	private static int FRAME_HEIGHT = 400;
	
	/**
	 * The main method for to run the scene.
	 * @param args the command line arguments. Not used.
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Scene");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//----The Scene Component
		final SceneComponent sceneComponent = new SceneComponent();
		frame.add(sceneComponent, BorderLayout.CENTER);
		
		//-----The control panel at the top of the frame
		JPanel controlPanel = new JPanel(new FlowLayout());
		JButton addSquareButton = new JButton("Square");
		addSquareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceneComponent.add(new SquareShape(20,20,40));
			}
		});
		JButton addCircleButton = new JButton("Circle");
		addCircleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceneComponent.add(new CircleShape(20, 20, 40));
			}
		});
		JButton removeSelectedButton = new JButton("Remove Selected");
		removeSelectedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceneComponent.removeSelected();
			}
		});
		JButton selectAllButton = new JButton("Select All");
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceneComponent.selectAll(true);
			}
		});
		JButton deSelectAllButton = new JButton("DeSelect All");
		deSelectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sceneComponent.selectAll(false);
			}
		});
		controlPanel.add(addSquareButton);
		controlPanel.add(addCircleButton);
		controlPanel.add(removeSelectedButton);
		controlPanel.add(selectAllButton);
		controlPanel.add(deSelectAllButton);
		frame.add(controlPanel, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
