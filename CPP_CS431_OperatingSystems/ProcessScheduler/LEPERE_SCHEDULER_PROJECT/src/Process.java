/**
 * A Process data structure for storing process data, such as PID, burst time
 * and priority.
 * 
 * @author JLepere2 09/29/2017
 */
public class Process implements Cloneable {

	private int pid, burstTime, priority;

	/**
	 * Creates a Process object.
	 * 
	 * @param thePid
	 *            the processes ID
	 * @param theBurstTime
	 *            the burst time
	 * @param thePriority
	 *            the priority
	 */
	public Process(int thePid, int theBurstTime, int thePriority) {
		pid = thePid;
		burstTime = theBurstTime;
		priority = thePriority;
	}

	/**
	 * Gets the remaining burst time of the process.
	 * 
	 * @return the burst time of the process.
	 */
	public int getBurstTime() {
		return burstTime;
	}

	/**
	 * Gets the PID.
	 * 
	 * @return the PID.
	 */
	public int getPID() {
		return pid;
	}

	/**
	 * Gets the priority.
	 * 
	 * @return the priority.
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Run the process.
	 * 
	 * @param time
	 *            the time to run.
	 */
	public void run(int time) {
		burstTime -= time;
	}

	/**
	 * Clones the Process object.
	 */
	public Process clone() {
		return new Process(pid, burstTime, priority);
	}

}
