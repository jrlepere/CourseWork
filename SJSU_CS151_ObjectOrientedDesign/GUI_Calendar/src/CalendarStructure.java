import java.util.ArrayList;
import java.util.Collections;

/**
 * A Tree structure to hold the events for the calendar
 * @author JLepere2
 * Version 1
 */
public class CalendarStructure {

	private ArrayList<YearNode> years;
	
	/**
	 * Creates a CalendarStructure Data Type.
	 */
	public CalendarStructure() {
		this.years = new ArrayList<>();
	}
	
	/**
	 * Adds the event to the Structure
	 * @param year the year of the event
	 * @param month the month of the event
	 * @param day the day of the event
	 * @param event a string containing the event. Include the time of the event her if applicable.
	 * @param startTime the start time
	 * @param endTime the end time
	 */
	public void addEvent(int year, int month, int day, String event, MyTime startTime, MyTime endTime) {
		for (YearNode yearNode : years) {
			if (yearNode.year == year) {
				// Year Node already exists for year of event
				for (MonthNode monthNode : yearNode.months) {
					if (monthNode.month == month) {
						// Month Node already exists for month of event
						for (DayNode dayNode : monthNode.days) {
							if (dayNode.day == day) {
								// Day Node already exists for day of event
								dayNode.addEvent(event, startTime, endTime);
								return;
							}
						}
						// Day node does not exists for month of event. Create a Day Node and add to the month.
						DayNode newDayNode = new DayNode(day);
						newDayNode.addEvent(event, startTime, endTime);
						monthNode.addDayNode(newDayNode);
						return;
					}
				}
				// Month Node does not exist for month of event. Create a Month and Day Node and add to year.
				DayNode newDayNode = new DayNode(day);
				newDayNode.addEvent(event, startTime, endTime);
				MonthNode newMonthNode = new MonthNode(month);
				newMonthNode.addDayNode(newDayNode);
				yearNode.addMonthNode(newMonthNode);
				return;
			}
		}
		// Year Node does not exist for year of event. Create a new Year, Month, and Day Node.
		DayNode newDayNode = new DayNode(day);
		newDayNode.addEvent(event, startTime, endTime);
		MonthNode newMonthNode = new MonthNode(month);
		newMonthNode.addDayNode(newDayNode);
		YearNode newYearNode = new YearNode(year);
		newYearNode.addMonthNode(newMonthNode);
		years.add(newYearNode);
		return;
	}
	
	/**
	 * Gets the events for the provided day as a multiple lined string
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return the events of the given day
	 */
	public ArrayList<Event> getEventsByDay(int year, int month, int day) {
		for (YearNode yearNode : years) {
			if (yearNode.year == year) {
				// Year Found
				for (MonthNode monthNode : yearNode.months) {
					if (monthNode.month == month) {
						// Month Found
						for (DayNode dayNode : monthNode.days) {
							if (dayNode.day == day) {
								// Day Found
								return dayNode.events;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the events in the format for saving
	 * @return a string containing the events in the structure in the format for saving
	 */
	public String getEventsToSave() {
		String events = "";
		boolean first = true;
		Collections.sort(years);
		for (YearNode yearNode : years) {
			Collections.sort(yearNode.months);
			for (MonthNode monthNode : yearNode.months) {
				Collections.sort(monthNode.days);
				for (DayNode dayNode : monthNode.days) {
					Collections.sort(dayNode.events);
					for (Event event : dayNode.events) {
						if (!first) {
							events += "\n";
						} else {
							first = false;
						}
						if (monthNode.month < 10) {
							events += "0" + monthNode.month;
						} else {
							events += monthNode.month;
						}
						if (dayNode.day < 10) {
							events += "/0" + dayNode.day;
						} else {
							events += "/" + dayNode.day;
						}
						events += "/" + yearNode.year + ": ";
						if (event.hasEndTime()) {
							events += event.getStartTime().toString() + " - " + event.getEndTime().toString() + " ";
						} else {
							events += event.getStartTime().toString() + " ";
						}
						events += event.getEvent();
					}
				}
			}
		}
		return events;
	}
	
	/**
	 * Inner Class for the Year Node
	 * @author JLepere2
	 * Version 1.1
	 */
	private static class YearNode implements Comparable<YearNode> {
		int year;
		ArrayList<MonthNode> months;
		public YearNode(int year) {
			this.year = year;
			this.months = new ArrayList<MonthNode>();
		}
		public void addMonthNode(MonthNode monthNode) {
			months.add(monthNode);
		}
		public int compareTo(YearNode o) {
			return this.year - o.year;
		}
	}
	/**
	 * Inner Class for the Month Node
	 * @author JLepere2
	 * Version 1.1
	 */
	private static class MonthNode implements Comparable<MonthNode> {
		int month;
		ArrayList<DayNode> days;
		public MonthNode(int month) {
			this.month = month;
			this.days = new ArrayList<DayNode>();
		}
		public void addDayNode(DayNode dayNode) {
			days.add(dayNode);
		}
		public int compareTo(MonthNode o) {
			return this.month - o.month;
		}
	}
	/**
	 * Inner Class for the Day Node
	 * @author JLepere2
	 * Version 1.1
	 */
	private static class DayNode implements Comparable<DayNode> {
		int day;
		ArrayList<Event> events;
		public DayNode(int day) {
			this.day = day;
			this.events = new ArrayList<Event>();
		}
		public void addEvent(String event, MyTime startTime, MyTime endTime) {
			events.add(new Event(event, startTime, endTime));
		}
		public int compareTo(DayNode o) {
			return this.day - o.day;
		}
	}
	
}