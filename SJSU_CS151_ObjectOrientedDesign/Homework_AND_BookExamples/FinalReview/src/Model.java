import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A model to hold the text input from the user.
 * @author JLepere2
 * Version 1.1
 */
public class Model <E> {

	private ArrayList<E> data;
	private ArrayList<ChangeListener> listeners;
	
	public Model() {
		data = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	public void addData(E theData) {
		data.add(theData);
		notifyListeners();
	}
	
	public void addListener(ChangeListener theListener) {
		listeners.add(theListener);
	}
	
	private void notifyListeners() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}
	
	public String getDataString() {
		String text = "";
		boolean first = true;
		for (E e : data) {
			if (first) {
				text += e;
				first = false;
			} else {
				text += "\n" + e;
			}
		}
		return text;
	}
	
}
