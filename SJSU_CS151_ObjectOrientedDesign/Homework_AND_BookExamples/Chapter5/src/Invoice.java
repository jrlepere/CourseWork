import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Invoice {
	private ArrayList<String> items;
	private ArrayList<ChangeListener> listeners;
	
	public Invoice() {
		items = new ArrayList<>();
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public void addItem(String item) {
		items.add(item);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}
	
	public String toString() {
		String result = "";
		for (String item : items) {
			result += item + "\n";
		}
		return result;
	}
}
