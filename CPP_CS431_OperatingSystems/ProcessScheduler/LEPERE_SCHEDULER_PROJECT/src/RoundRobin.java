import java.util.LinkedList;

/**
 * A Round Robin scheduler.
 * @author JLepere2
 * 10/01/2017
 */
public class RoundRobin extends Scheduler {

	private int quantum;
	
	/**
	 * Creates a Round Robin scheduler with a quantum.
	 * @param theQuantum the quantum.
	 */
	public RoundRobin(int theQuantum) {
		super(new LinkedList<>(), theQuantum);
		quantum = theQuantum;
	}

	public String getAlgorithmName() {
		return "ROUND_ROBIN_(" + quantum + ")";
	}

}
