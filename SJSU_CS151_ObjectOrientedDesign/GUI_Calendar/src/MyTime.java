/**
 * Class to store the time for an event
 * @author JLepere2
 * Version 1
 */
public class MyTime implements Comparable<MyTime> {
	
	private int hour;
	private int minute;
	
	/**
	 * Constructs an MyTime object with hours and minutes
	 * @param hour the hour(s)
	 * @param minute the minute(s)
	 */
	public MyTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public int compareTo(MyTime o) {
		if (hour - o.hour != 0) {
			return hour - o.hour;
		} else {
			return minute - o.minute;
		}
	}
	
	public String toString() {
		String s = "";
		if (hour < 10) {
			s += "0" + hour;
		} else {
			s += hour;
		}
		s += ":";
		if (minute < 10) {
			s += "0" + minute;
		} else {
			s += minute;
		}
		return s;
	} 
	
	/**
	 * Gets the number of seconds from 0 for this time.
	 * @return the number of seconds from 0.
	 */
	public int getSeconds() {
		return 3600*hour + 60*minute;
	}
	
	/**
	 * Gets the time with am and pm.
	 * @return the time with am and pm
	 */
	public String getTime() {
		
		String time = "";
		
		if (hour == 0 || hour == 12) {
			time += "12:";
		} else if (hour < 10) {
			time += "0" + hour + ":";
		} else if (hour < 12) {
			time += hour + ":";
		}	else if (hour < 22){
			time += "0" + (hour - 12) + ":";
		} else {
			time += hour - 12 + ":";
		}

		if (minute < 10) {
			time += "0" + minute;
		} else {
			time += minute;
		}
		
		if (hour < 12) {
			time += "am";
		} else {
			time += "pm";
		}
		
		return time;
		
	}
	
}
