import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The view panel for the manager
 * 
 * @author Rodion Yaryy 
 * Version 1.1
 */
public class ManagerViewPanel extends JPanel {

	private static final long serialVersionUID = 1215316L;
	private ArrayList<HotelRoom> availableRoomsEconomic;
	private ArrayList<HotelRoom> availableRoomsLuxurious;

	/**
	 * Creates a ManagerViewPanel.
	 * 
	 * @param mainCardPanel
	 *            the main card panel associated with this state panel.
	 * @param hotelManager
	 *            the hotel manager.
	 */
	public ManagerViewPanel(final MainCardPanel mainCardPanel, final HotelManager hotelManager) {

		// ------Instantiate Panel
		this.setLayout(new GridLayout(2, 2, 20, 20));
		this.setBorder(new EmptyBorder(20, 20, 50, 20));

		// ------Date Range Model
		final DateRangeReservationModel dateRangeModel = new DateRangeReservationModel(hotelManager);

		// ------Instance Variables
		setRoomsAvailable(dateRangeModel);

		// ------ Room Available Text Area
		String roomAvailabilityText = "Room Availability " + dateRangeModel.getDateFrom().getDateString() + ".\n\n"
				+ "Economic Rooms: " + availableRoomsEconomic.size() + " Available.\n" + "Luxurious Rooms: "
				+ availableRoomsLuxurious.size() + " Available.";
		ArrayList<Reservation> reservationsForDay = hotelManager.getReservationsByDate(dateRangeModel.getDateFrom());
		if (reservationsForDay.size() != 0) {
			roomAvailabilityText += "\n\n\nOccupied: \n";
			for (Reservation r : reservationsForDay) {
				roomAvailabilityText += "\nRoom: " + r.getHotelRoom().getRoomType() + r.getHotelRoom().getRoomNumber()
						+ "\nGuest: " + r.getGuestName() + "\nId: " + r.getGuestId() + "\n";
			}
		}
		final JTextArea roomAvailabilityArea = new JTextArea(roomAvailabilityText);
		roomAvailabilityArea.setCaretPosition(0);
		roomAvailabilityArea.setEditable(false);
		final String roomInformationHeader = "Information About the Room: ";
		final JTextArea roomInformation = new JTextArea(roomInformationHeader);
		roomInformation.setEditable(false);

		// ------ Room View Panel
		JPanel roomView = new JPanel(new GridLayout(4, 1));
		JLabel economicRoomInfo = new JLabel("Economic Room Information: ");
		final JPanel economicRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		JLabel luxuriousRoomInfo = new JLabel("Luxurious Room Information: ");
		final JPanel luxuriousRoomPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation, roomInformationHeader,
				hotelManager.getReservationsByDate(dateRangeModel.getDateTo()));
		roomView.add(economicRoomInfo);
		roomView.add(economicRoomPanel);
		roomView.add(luxuriousRoomInfo);
		roomView.add(luxuriousRoomPanel);

		// ----- Grid Component
		final JLabel currentDayLabel = new JLabel();
		final CalendarGridComponent.Manager gridComp = new CalendarGridComponent.Manager(dateRangeModel,
				currentDayLabel);
		dateRangeModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setRoomsAvailable(dateRangeModel);
				setRoomPanels(economicRoomPanel, luxuriousRoomPanel, roomInformation, roomInformationHeader,
						hotelManager.getReservationsByDate(new MyDate(gridComp.getCurrentDayShort())));
				String roomAvailabilityText = "Room Availability " + dateRangeModel.getDateFrom().getDateString()
						+ ".\n\n" + "Economic Rooms: " + availableRoomsEconomic.size() + " Available.\n"
						+ "Luxurious Rooms: " + availableRoomsLuxurious.size() + " Available.";
				ArrayList<Reservation> reservationsForDay = hotelManager
						.getReservationsByDate(dateRangeModel.getDateFrom());
				if (reservationsForDay.size() != 0) {
					roomAvailabilityText += "\n\n\nOccupied: \n";
					for (Reservation r : reservationsForDay) {
						roomAvailabilityText += "\nRoom: " + r.getHotelRoom().getRoomType()
								+ r.getHotelRoom().getRoomNumber() + "\nGuest: " + r.getGuestName() + "\nId: "
								+ r.getGuestId() + "\n";
					}
				}
				roomAvailabilityArea.setText(roomAvailabilityText);
				roomAvailabilityArea.setCaretPosition(0);
				roomAvailabilityArea.repaint();
				currentDayLabel.setText(gridComp.getCurrentDayLong());
				currentDayLabel.repaint();
				roomInformation.setText(roomInformationHeader);
				roomInformation.repaint();
			}
		});

		// ------NORTH GRID PANEL------//
		JPanel gridPanel = new JPanel(new BorderLayout());
		currentDayLabel.setText(gridComp.getCurrentDayLong());
		JPanel nextPreviousPanel = new JPanel(new FlowLayout());
		JButton previousYearButton = new JButton("  <<  ");
		previousYearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeYear(false);
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
			}
		});
		JButton previousMonthButton = new JButton("   <   ");
		previousMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(false);
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
			}
		});
		JButton nextMonthButton = new JButton("   >   ");
		nextMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeMonth(true);
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
			}
		});
		JButton nextYearButton = new JButton("  >>  ");
		nextYearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridComp.changeYear(true);
				dateRangeModel.setDate(true, new MyDate(gridComp.getCurrentDayShort()));
				dateRangeModel.setDate(false, new MyDate(gridComp.getCurrentDayShort()));
			}
		});
		nextPreviousPanel.add(previousYearButton);
		nextPreviousPanel.add(previousMonthButton);
		nextPreviousPanel.add(nextMonthButton);
		nextPreviousPanel.add(nextYearButton);
		gridPanel.add(nextPreviousPanel, BorderLayout.NORTH);
		gridPanel.add(gridComp, BorderLayout.CENTER);
		gridPanel.add(currentDayLabel, BorderLayout.SOUTH);

		// Add Components
		this.add(gridPanel);
		this.add(new JScrollPane(roomAvailabilityArea));
		this.add(roomView);
		this.add(new JScrollPane(roomInformation));
	}

	/**
	 * Sets the room panels with the current date range
	 * 
	 * @param economicRoomPanel
	 *            the economic room panel
	 * @param luxuriousRoomPanel
	 *            the luxurious room panel
	 * @param roomInformation
	 *            the room information text are
	 */

	private void setRoomPanels(JPanel economicRoomPanel, JPanel luxuriousRoomPanel, final JTextArea roomInformation,
			String roomInformationHeader, ArrayList<Reservation> reservationsForDay) {

		// Remove Components
		for (Component c : economicRoomPanel.getComponents()) {
			economicRoomPanel.remove(c);
		}
		for (Component c : luxuriousRoomPanel.getComponents()) {
			luxuriousRoomPanel.remove(c);
		}

		// Economic Room
		for (int i = 1; i <= 10; i++) {
			JButton button = new JButton(i + "");
			button.setForeground(Color.red);
			boolean available = false;
			for (HotelRoom room : availableRoomsEconomic) {
				if (room.getRoomNumber() == i) {
					button.setForeground(Color.green);
					available = true;
				}
			}
			String roomInfoText = roomInformationHeader;
			if (available) {
				roomInfoText += "\n\nRoom is available.";
			} else {
				roomInfoText += "\n\nRoom is occupied.";
			}
			for (Reservation r : reservationsForDay) {
				if (r.getHotelRoom().getRoomType().equals(EconomicRoom.identifier)
						&& i == r.getHotelRoom().getRoomNumber()) {
					roomInfoText += "\n\nGuest Name: " + r.getGuestName() + "\nGuest Id: " + r.getGuestId()
							+ "\nLength of Reservation: " + r.getTime();
				}
			}
			final String theInfo = roomInfoText;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(theInfo);
				}
			});
			economicRoomPanel.add(button);
		}
		// Lux Room
		for (int i = 1; i <= 10; i++) {
			JButton button = new JButton(i + "");
			button.setForeground(Color.red);
			boolean available = false;
			for (HotelRoom room : availableRoomsLuxurious) {
				if (room.getRoomNumber() == i) {
					button.setForeground(Color.green);
					available = true;
				}
			}
			String roomInfoText = roomInformationHeader;
			if (available) {
				roomInfoText += "\n\nRoom is available.";
			} else {
				roomInfoText += "\n\nRoom is occupied.";
			}
			for (Reservation r : reservationsForDay) {
				if (r.getHotelRoom().getRoomType().equals(LuxuriousRoom.identifier)
						&& i == r.getHotelRoom().getRoomNumber()) {
					roomInfoText += "\n\nGuest Name: " + r.getGuestName() + "\nGuest Id: " + r.getGuestId()
							+ "\nLength of Reservation: " + r.getTime();
				}
			}
			final String theInfo = roomInfoText;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					roomInformation.setText(theInfo);
				}
			});
			luxuriousRoomPanel.add(button);
		}

		// Repaint
		economicRoomPanel.revalidate();
		luxuriousRoomPanel.revalidate();
		roomInformation.repaint();
	}

	/**
	 * Sets the available rooms from the model.
	 * 
	 * @param dateRangeModel
	 *            the date range model.
	 */
	private void setRoomsAvailable(DateRangeReservationModel dateRangeModel) {
		dateRangeModel.setRoomSelected(true, false);
		availableRoomsEconomic = dateRangeModel.getAvailableRooms(new ArrayList<Reservation>());
		dateRangeModel.setRoomSelected(false, false);
		availableRoomsLuxurious = dateRangeModel.getAvailableRooms(new ArrayList<Reservation>());
	}

}