import java.util.LinkedList;

/**
 * The First-Come-First-Serve algorithm for process scheduling.
 * 
 * @author JLepere2 
 * 09/29/2017
 */
public class FirstComeFirstServe extends Scheduler {

	/**
	 * Creates a First-Come-First-Server scheduler.
	 */
	public FirstComeFirstServe() {
		super(new LinkedList<>(), -1);
	}

	public String getAlgorithmName() {
		return "FIRST_COME_FIRST_SERVE";
	}

}
