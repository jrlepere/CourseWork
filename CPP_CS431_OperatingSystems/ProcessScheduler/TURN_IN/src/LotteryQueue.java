import java.util.LinkedList;
import java.util.Random;

/**
 * A Queue for the Lottery Scheduler.
 * @author JLepere2
 * 10/04/2017
 */
public class LotteryQueue extends LinkedList<Process> {
		
		private static final long serialVersionUID = 11265425L;
		private final int RANDOM_SEED = 4000;
		private Random gen = new Random(RANDOM_SEED);
		
		/**
		 * Creates a LotteryQueue object.
		 */
		public LotteryQueue() {
			super();
		}
		
		/**
		 * Overrides the LinkedListQueue class.
		 */
		public Process remove() {
			int total = 0;
			for (Process p : this) {
				total += p.getPriority();
			}
			int r = gen.nextInt(total);
			total = 0;
			int i = 0;
			for (Process p : this) {
				total += p.getPriority();
				if (total > r) {
					return super.remove(i);
				}
				i ++;
			}
			return null;
		}
		
	}