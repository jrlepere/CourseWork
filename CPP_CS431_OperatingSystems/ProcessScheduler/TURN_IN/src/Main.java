import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class, containing the main method, for the scheduler project. The
 * program is ran through the command line with the pid file name passed as
 * input. An output file is generated with filename =
 * inputfilename.output.csv. This output file is to be used with a
 * spreadsheet application to analyze the data.
 * 
 * @author JLepere2 09/29/2017
 */
public class Main {

	/**
	 * The main method for the program. Parses the input pid file and passes the
	 * processes to the OS.
	 * 
	 * @param args
	 *            the command line arguments. Pass one file through the
	 *            command line.
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("No process files passed through commandline.");
			return;
		}

		try {

			final int CPU_SWITCH_TIME = 3;
			List<Scheduler> schedulingAlgorithmList = new LinkedList<>();
			schedulingAlgorithmList.add(new FirstComeFirstServe());
			schedulingAlgorithmList.add(new ShortestJobFirst());
			schedulingAlgorithmList.add(new RoundRobin(25));
			schedulingAlgorithmList.add(new RoundRobin(50));
			schedulingAlgorithmList.add(new Lottery(60));

			OperatingSystem os = new OperatingSystem(new CPU(CPU_SWITCH_TIME), schedulingAlgorithmList.get(0));

			String inputFilename = args[0];
			File f = new File(inputFilename);

			List<Process> processHolderList = new LinkedList<>();

			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String pid = s.nextLine().trim();
				if (!s.hasNextLine()) {
					System.out.println("Illegal process file format.");
					s.close();
					return;
				}
				String burstTime = s.nextLine().trim();
				if (!s.hasNextLine()) {
					System.out.println("Illegal process file format.");
					s.close();
					return;
				}
				String priority = s.nextLine().trim();
				try {
					Process p = new Process(Integer.parseInt(pid), Integer.parseInt(burstTime),
							Integer.parseInt(priority));
					processHolderList.add(p);
				} catch (NumberFormatException e) {
					System.out.println("Illegal integer conversion in process file.");
					s.close();
				}
			}

			String output = "INPUT_FILE:," + inputFilename + "\n";
			output += "CPU_SWITCH_TIME:," + os.getCpuSwitchTime() + "\n";
			output += "SYSTEM_TIME:,"
					+ new SimpleDateFormat("MM/dd/yyy HH:mm:ss").format(Calendar.getInstance().getTime());
			
			for (Scheduler scheduler : schedulingAlgorithmList) {

				os.setScheduler(scheduler);

				for (Process process : processHolderList) {
					os.addProcess(process.clone());
				}

				output += "\n,\n";
				output += "SCHEDULER:," + os.getSchedulerAlgorithmName() + "\n";

				output += os.run();

			}
			
			String outputFilename = inputFilename + ".output.csv";
			FileWriter writer = new FileWriter(new File(outputFilename));
			writer.write(output);
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
