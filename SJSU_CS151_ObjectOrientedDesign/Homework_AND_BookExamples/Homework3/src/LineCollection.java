import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A collection for user inputed lines.
 * @author JLepere2
 * Version 1.1
 */
public class LineCollection {

	private ArrayList<String> lines;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Creates a LineCollection instance with an initially empty collection.
	 */
	public LineCollection() {
		this.lines = new ArrayList<>();
		this.listeners = new ArrayList<>();
	}
	
	/**
	 * Adds a line to the collection.
	 * @param line the line to add
	 */
	public void add(String line) {
		lines.add(line);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}
	
	/**
	 * Gets the collection of lines.
	 * @return the collection of lines.
	 */
	public ArrayList<String> getLines() {
		return lines;
	}
	
	/**
	 * Attaches a view to this model.
	 * @param view the view to attach.
	 */
	public void attachListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public String toString() {
		String result = "";
		for (String str : lines) {
			result += str + "\n";
		}
		return result;
	}
	
}
