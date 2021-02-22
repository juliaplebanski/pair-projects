package com.techelevator;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.JDBC.JDBCVenueDAO;
import com.techelevator.model.JDBC.JDBCVenueSpaceDAO;
import com.techelevator.model.dao.ReservationDAO;
import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSpaceDAO;
import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.Venue;
import com.techelevator.model.domain.VenueSpace;
import com.techelevator.view.UI;

public class ExcelsiorCLI {
	private static final String ONE = "List Venues";
	private static final String TWO = "Quit";
	private static final String[] MAIN_MENU_OPTION_ARRAY = new String[] { ONE, TWO };

	private static final String SUB_MENU_OPTION_ONE = "View Spaces";
	private static final String SUB_MENU_OPTION_TWO = "Search for a reservation";
	private static final String SUB_MENU_OPTION_THREE = "Return to the previous screen.";
	private static final String[] SUB_MENU_OPTION_ARRAY = new String[] { SUB_MENU_OPTION_ONE, SUB_MENU_OPTION_TWO,
			SUB_MENU_OPTION_THREE };
	private static final String SECOND_SUB_MENU_OPTION_ONE = "Reserve a Space";
	private static final String SECOND_SUB_MENU_OPTION_TWO = "Return to the previous screen";
	private static final String[] SECOND_SUB_MENU_OPTION_ARRAY = new String[] { SECOND_SUB_MENU_OPTION_ONE,
			SECOND_SUB_MENU_OPTION_TWO };

	/** Please use format when commenting on top of a method. */
	// Please use this format when commenting within a method.
	// All that is left: add reservation, search for reservation, print out
	// reservation confirmation and format.

	private UI ui;
	private VenueDAO venueDAO;
	private ReservationDAO reservationDAO;
	private VenueSpaceDAO venueSpaceDAO;

	public static void main(String[] args) {
		ExcelsiorCLI application = new ExcelsiorCLI();
		application.run();
	}

	public ExcelsiorCLI() {
		this.ui = new UI(System.in, System.out);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		this.venueDAO = new JDBCVenueDAO(dataSource);
		this.venueSpaceDAO = new JDBCVenueSpaceDAO(dataSource);
		this.reservationDAO = new JDBCReservationDAO(dataSource);

	}

// Reminder: No System.out.printlns in this class

	public void run() {
		boolean running = true;
		while (running) {
			
			//first print the main menu
			ui.printMainMenu();

			//then, print this question to the user; not sure why this is printing above the main menu?
			ui.printHeader("What would you like to do?");
			String Choice = (String) ui.getChoiceFromOptions(MAIN_MENU_OPTION_ARRAY);

			if (Choice.equals(ONE)) {
				handleListOfVenues();
			} else if (Choice.equals(TWO)) {
				ui.exitMessage();
				System.exit(0);
			} else {
				ui.handleError();
			}

		}

	}

	/** This method returns a list of venues. */
	public void handleListOfVenues() {
		List<Venue> venueList = venueDAO.getAllVenues();
		ui.printVenueList(venueList);
		ui.printHeader("Which venue would you like to view?");

		Object[] venueArray = new Object[venueList.size()];
		venueList.toArray(venueArray);
		Venue venue = (Venue) ui.getChoiceFromUserInput(venueArray);
		handleVenueDetails(venue.getVenueID());
		ui.printHeader("What would you like to do next");
		subMenu();

	}

	/** This is the sub menu method. */
	private void subMenu() {
		boolean isRunning = true;
		while (isRunning) {
			String Choice = (String) ui.getChoiceFromOptions(SUB_MENU_OPTION_ARRAY);
			if (Choice.equals(SUB_MENU_OPTION_ONE)) {
				handleSpaces();
			} else if (Choice.equals(SUB_MENU_OPTION_TWO)) {
				Long ChoiceForSubMenu2 = Long.parseLong(ui.getInputFromUser("Provide the reservation ID"));
				handleSearchReservation(ChoiceForSubMenu2);

			} else {
				isRunning = false;
			}

		}

	}
	
	/**This method is used to search for a reservation by id*/
	private Reservation handleSearchReservation(long reservationID) {
		Reservation reservation = reservationDAO.getByReservationID(reservationID);
		ui.printReservationByID(reservation);
		return reservation;
	}

	/** This method handles Venue spaces. */
	private void handleSpaces() {
		List<VenueSpace> listOfVenueSpaces = venueSpaceDAO.getAllSpaces();
		ui.printSpaceList(listOfVenueSpaces);
		ui.printHeader("What would you like to do next");
		reservationSubmenu();

	}

	/** This method handles the reservation Sub menu. */
	private void reservationSubmenu() {
		String Choice = (String) ui.getChoiceFromOptions(SECOND_SUB_MENU_OPTION_ARRAY);
		if (Choice.equals(SECOND_SUB_MENU_OPTION_ONE)) {
			createNewReservation();
		}

	}

	/** This method retrieves a list of availability based on the user's need. */
	private void createNewReservation() {
		Long selectedVenueID = Long.parseLong(ui.getInputFromUser("Please provide the venue space ID of the venue space listed to the left "));
		Venue selectedVenueName = venueDAO.getVenueNameByID(selectedVenueID);
		String finalselectedVenueName = selectedVenueName.getName();

		int numOfAttendees = Integer.parseInt(ui.getInputFromUser("How many people would be attending?"));
		Long lengthOfStay = Long.parseLong(ui.getInputFromUser("How many days will you need the space?"));
		LocalDate startDate = ui.getDateFromUser("When do you need the space? (MM/DD/YYYY)");
		LocalDate endDate = startDate.plusDays(lengthOfStay);
		long diff = ChronoUnit.DAYS.between(startDate, endDate);
		if (diff < 1) {

		} else {

			List<VenueSpace> avaliableSpaceList = reservationDAO.getAvaliableVenueSpacesByDate(selectedVenueID,
					startDate, endDate);
			if (avaliableSpaceList.size() == 0) {

				ui.printNoSpace();
			} else {
				ui.printReservationHeader();
				ui.printListOfAvaliableSpaces(avaliableSpaceList);
				Long selectedSpaceID = Long.parseLong(ui.getInputFromUser("Which space do you want to reserve?"));
				VenueSpace selectedVenueSpaceName = venueSpaceDAO.getVenueSpacebyID(selectedSpaceID);
				String finalselectedVenueSpace = selectedVenueSpaceName.getName();

				handleCreateReservation(selectedVenueSpaceName, numOfAttendees, startDate, endDate, selectedSpaceID,
						finalselectedVenueName, finalselectedVenueSpace, lengthOfStay);
			}
		}

	}

	/** This method creates a new reservation */
	private void handleCreateReservation(VenueSpace venueSpace, int numOfAttendees, LocalDate startDate,
			LocalDate endDate, long spaceID, String finalselectedVenueName, String finalselectedVenueSpace,
			long lengthOfStay) {
		String reservationName = ui.getInputFromUser("Who is this reservation for?");
		Reservation reservation = new Reservation();
		Venue venue = new Venue();
		venue.setName(finalselectedVenueName);
		double totalCost = venueSpace.getDaily_rate() * lengthOfStay;
		reservation.setSpaceID(spaceID);
		reservation.setName(reservationName);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setNumOfAttendees(numOfAttendees);
		reservation = reservationDAO.createReservation(reservation);
		ui.printConfirmationMessage(reservation, venue, venueSpace, totalCost);

	}

	/** This method returns a list of venue details. */
	public List<Venue> handleVenueDetails(long venue_id) {
		List<Venue> venueDetails = venueDAO.getVenueDetails(venue_id);
		ui.printVenueDetails(venueDetails);
		return venueDetails;

	}

}
