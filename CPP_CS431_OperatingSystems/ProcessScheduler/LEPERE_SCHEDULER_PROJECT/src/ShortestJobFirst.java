import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Creates a Shortest Job First scheduler.
 * 
 * @author JLepere2 10/01/2017
 */
public class ShortestJobFirst extends Scheduler {

	/**
	 * Creates a Shortest Job First scheduler.
	 */
	public ShortestJobFirst() {
		super(new PriorityQueue<>(new Comparator<Process>() {
			public int compare(Process o1, Process o2) {
				return o1.getBurstTime() - o2.getBurstTime();
			}
		}), -1);
	}

	public String getAlgorithmName() {
		return "SHORTEST_JOB_FIRST";
	}
}
