
/**
 * A Schedule interface for the Operating System.
 * @author JLepere2
 * 09/29/2017
 */
public interface IScheduler {
	/**
	 * Adds a Process to the Scheduler queue.
	 * @param theProcess the process.
	 */
	void addProcess(Process theProcess);
	/**
	 * Gets the current number of processes.
	 * @return the current number of processes.
	 */
	int getProcessCount();
	/**
	 * Runs the next process.
	 * @param cpu the cpu to use.
	 * @return the time ran on the cpu.
	 */
	void runNextProcess(CPU cpu);
	/**
	 * Gets the status of the processes in a csv format.
	 * @return the status of the processes.
	 */
	String getStatus();
	/**
	 * Gets the scheduler status header for the csv format.
	 * @return the scheduler status header for the csv format.
	 */
	String getStatusHeader();
	/**
	 * Gets the turn around time of each process in a formatted row. 
	 * @return the turn around time for each process.
	 */
	String getTurnAroundTimeRow();
	/**
	 * Gets the scheduling algorithm name.
	 * @return the name of the scheduling algorithm.
	 */
	String getAlgorithmName();
}
