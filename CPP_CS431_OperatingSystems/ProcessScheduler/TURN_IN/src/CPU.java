/**
 * A CPU Interface.
 * @author JLepere2
 * 09/29/2017
 */
public class CPU {
	
	private int switchTime;
	
	/**
	 * Creates a CPU Object with a switch time.
	 * @param theSwitchTime the switch time of the CPU.
	 */
	public CPU(int theSwitchTime) {
		switchTime = theSwitchTime;
	}
	
	/**
	 * Runs a process for a given amount of time. If time is null, run the process until completion.
	 * @param theProcess the process to run
	 * @param time the amount of time to run the process
	*/
	void run(Process theProcess, int time) {
		theProcess.run(time);
	}
	/**
	 * Gets the switch time of the CPU.
	 * @return the switch time of the CPU
	*/
	public int getSwitchTime() {
		return switchTime;
	}
}
