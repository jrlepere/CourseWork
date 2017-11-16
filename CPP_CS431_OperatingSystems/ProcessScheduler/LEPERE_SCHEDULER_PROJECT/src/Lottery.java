/**
 * A Lottery scheduler based on process priority.
 * @author JLepere2
 * 10/04/2017
 */
public class Lottery extends Scheduler {
	
	private int quantum;
	
	/**
	 * Creates a Lottery Scheduler object.
	 * @param theQuantum the time quantum.
	 */
	public Lottery(int theQuantum) {
		super(new LotteryQueue(),theQuantum);
		quantum = theQuantum;
	}
	
	public String getAlgorithmName() {
		return "LOTTERY_(" + quantum + ")";
	}

}
