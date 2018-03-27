import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A JPanel for the Calculator buttons.
 * @author JLepere2
 * @date 01/22/2018
 */
public class ButtonPanel extends JPanel {
	
	/**
	 * Creates a ButtonPanel with a CalculatorUtility.
	 * @param calculatorUtility the CalculatoryUtility object for performing arithmetic and updates to the frame
	 */
	public ButtonPanel(CalculatorUtility calculatorUtility) {
		super(new BorderLayout());
		
		// Main Button Panel
		final int GRID_LAYOUT_ROWS = 4;
		final int GRID_LAYOUT_COLUMNS = 4;
		JPanel mainButtonPanel = new JPanel(new GridLayout(GRID_LAYOUT_ROWS, GRID_LAYOUT_COLUMNS));
		
		// Buttons
		String[] buttonChars = {"1","2","3",ADD_CHAR,"4","5","6",SUBTRACT_CHAR,"7","8","9",MULTIPLY_CHAR,PERIOD,"0",EQUALS_CHAR,DIVIDE_CHAR};
		for (int i = 0; i < buttonChars.length; i ++) {
			JButton b = new JButton(buttonChars[i]);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calculatorUtility.addCharacter(b.getText());
				}
			});
			mainButtonPanel.add(b);
		}
		JButton clearButton = new JButton("CLEAR");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculatorUtility.clearScreen();
			}
		});
		
		// Add Components
		this.add(mainButtonPanel, BorderLayout.CENTER);
		this.add(clearButton, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Gets the operators available to the user. (+,-,*,/,etc.)
	 * @return the available operators.
	 */
	public static final List<String> getOperators() {
		List<String> operators = new LinkedList<>();
		operators.add(ADD_CHAR);
		operators.add(SUBTRACT_CHAR);
		operators.add(MULTIPLY_CHAR);
		operators.add(DIVIDE_CHAR);
		return operators;
	}
	
	/**
	 * Gets the equals symbol.
	 * @return the equals symbol.
	 */
	public static final String getEquals() {
		return EQUALS_CHAR;
	}
	
	/**
	 * Gets a list of numbers available for input.
	 * @return a list of numbers available for input.
	 */
	public static final List<String> getNumbers() {
		List<String> numbers = new LinkedList<>();
		numbers.add("1");
		numbers.add("2");
		numbers.add("3");
		numbers.add("4");
		numbers.add("5");
		numbers.add("6");
		numbers.add("7");
		numbers.add("8");
		numbers.add("9");
		numbers.add("0");
		return numbers;
	}
	
	/**
	 * Gets the period symbol.
	 * @return the period symbol.
	 */
	public static final String getPeriod() {
		return PERIOD;
	}
	
	private static final long serialVersionUID = 15326521L;
	public static final String ADD_CHAR = "+";
	public static final String SUBTRACT_CHAR = "-";
	public static final String MULTIPLY_CHAR = "*";
	public static final String DIVIDE_CHAR = "/";
	public static final String EQUALS_CHAR = "=";
	public static final String PERIOD = ".";
	
}
