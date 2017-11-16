
/**
 * The Operating System to manage interaction with the CPU.
 * @author JLepere2
 * 09/29/2017
 */
public class OperatingSystem {

	private CPU cpu;
	private Scheduler scheduler;
	
	/**
	 * Creates an OperatingSystem object.
	 * @param theCpu the CPU
	 * @param theScheduler the scheduler
	 */
	public OperatingSystem(CPU theCpu, Scheduler theScheduler) {
		cpu = theCpu;
		scheduler = theScheduler;
	}
	
	/**
	 * Sets the OperatingSystems's scheduler.
	 * @param theScheduler the scheduler
	 */
	public void setScheduler(Scheduler theScheduler) {
		scheduler = theScheduler;
	}
	
	/**
	 * Gets the scheduler's algorithm name.
	 * @return the algorithm of the scheduler.
	 */
	public String getSchedulerAlgorithmName() {
		return scheduler.getAlgorithmName();
	}
	
	/**
	 * Gets the cpu switch time.
	 * @return the cpu switch time.
	 */
	public int getCpuSwitchTime() {
		return cpu.getSwitchTime();
	}
	
	/**
	 * Adds a Process to the OperatingSystem's queue.
	 * @param theProcess the process
	 */
	public void addProcess(Process theProcess) {
		scheduler.addProcess(theProcess);
	}
	
	/**
	 * Starts the OS.
	 * @return the status of the run.
	 */
	public String run() {
		String status = scheduler.getStatusHeader();
		status += "\n0," + scheduler.getStatus();
		int t = 1;
		while (scheduler.getProcessCount() != 0) {
			scheduler.runNextProcess(cpu);
			status += "\n" + t + "," + scheduler.getStatus();
			t += 1;
		}
		status += "\n" + scheduler.getTurnAroundTimeRow();
		return status;
	}
	
}
