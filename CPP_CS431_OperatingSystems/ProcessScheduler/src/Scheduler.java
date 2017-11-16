import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * An Abstract Scheduler implementing common functions.
 * 
 * @author JLepere2 
 * 
 * 09/29/2017
 */
public abstract class Scheduler implements IScheduler {

	private List<Integer> pidHolder;
	private Map<Integer, Integer> turnAroundTimeHolder;
	private int quantum;
	private int totalTimeRan;
	protected Queue<Process> processQueue;

	/**
	 * Creates a Scheduler.
	 * 
	 * @param theProcessQueue
	 *            the process queue formatted for the scheduler.
	 * @param runTime
	 *            the run time to run the processes, -1 for until completion.
	 */
	public Scheduler(Queue<Process> theProcessQueue, int runTime) {
		pidHolder = new ArrayList<>();
		turnAroundTimeHolder = new TreeMap<>();
		processQueue = theProcessQueue;
		quantum = runTime;
		totalTimeRan = 0;
	}

	public void addProcess(Process theProcess) {
		processQueue.add(theProcess);
		pidHolder.add(theProcess.getPID());
		turnAroundTimeHolder.put(theProcess.getPID(), 0);
	}

	public void runNextProcess(CPU cpu) {
		Process p = processQueue.remove();
		int timeToRunProcess = quantum;
		if (quantum < 0 || p.getBurstTime() <= quantum) {
			timeToRunProcess = p.getBurstTime();
		}
		cpu.run(p, timeToRunProcess);
		totalTimeRan += timeToRunProcess;
		if (p.getBurstTime() > 0) {
			processQueue.add(p);
		} else {
			turnAroundTimeHolder.put(p.getPID(), totalTimeRan);
		}
		totalTimeRan += cpu.getSwitchTime();
	}

	public int getProcessCount() {
		return processQueue.size();
	}

	public String getStatus() {
		String status = "";
		for (Integer pid : pidHolder) {
			boolean foundPID = false;
			for (Process p : processQueue) {
				if (pid == p.getPID()) {
					foundPID = true;
					status += p.getBurstTime() + ",";
					break;
				}
			}
			if (!foundPID) {
				status += "0,";
			}
		}
		return status.substring(0, status.length() - 1);
	}

	public String getStatusHeader() {
		String pidList = "PID:";
		String burstTimeList = "BURST_TIME:";
		String priorityList = "PRIORITY:";
		String pidHeaderList = "TIME";
		for (Integer pid : pidHolder) {
			for (Process p : processQueue) {
				if (pid == p.getPID()) {
					pidList += "," + p.getPID();
					burstTimeList += "," + p.getBurstTime();
					priorityList += "," + p.getPriority();
					pidHeaderList += ",PID_" + p.getPID();
				}
			}
		}
		String statusHeader = pidList + "\n" + burstTimeList + "\n" + priorityList + "\n" + pidHeaderList;
		return statusHeader;
	}

	public String getTurnAroundTimeRow() {
		int totalTurnaroundTime = 0;
		String turnAroundTime = "TURN_AROUND_TIME:";
		for (Integer pid : pidHolder) {
			int turnaroundTime = turnAroundTimeHolder.get(pid);
			turnAroundTime += "," + turnaroundTime;
			totalTurnaroundTime += turnaroundTime;
			
		}
		turnAroundTime += "\nTotal_Turnaround_Time:," + totalTurnaroundTime;
		turnAroundTime += "\nAverage_Turnaround_Time:," + ((0.0 + totalTurnaroundTime) / pidHolder.size());
		return turnAroundTime;
	}

}
