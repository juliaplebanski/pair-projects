package com.techelevator;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.JDBC.JDBCVenueDAO;
import com.techelevator.model.JDBC.JDBCVenueSpaceDAO;
import com.techelevator.model.dao.ReservationDAO;
import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSpaceDAO;
import com.techelevator.model.domain.Venue;
import com.techelevator.model.domain.VenueSpace;
import com.techelevator.view.UI;

public class ExcelsiorCLI {
	private static final String one = "List Venues";
	private static final String two = "Quit";
	private static final String[] MAIN_MENU_OPTION_ARRAY = new String[] { one, two };

	private static final String SUB_MENU_OPTION_VIEW_VENUES = "View venues";
	private static final String SUB_MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";
	private static final String SUB_MENU_OPTION_VENUE_DETAILS = "View venue details";
	private static final String SUB_MENU_OPTION_VIEW_SPACES = "View spaces";
	
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
			// prints the main menu, Main Menu
			ui.printHeader("What would you like to do?");
			String Choice = (String) ui.getChoiceFromOptions(MAIN_MENU_OPTION_ARRAY);

			// prints submenu 1, View Venues
			if (Choice.equals(one)) {
				handleListOfVenues();
				// prints submenu 2, Venu Details
				ui.printHeader("Which venue would you like to view?");

				List<Venue> venueList = venueDAO.getAllVenues();
				Object[] venueArray = new Object[venueList.size()];
				venueList.toArray(venueArray);
				Object subChoice1 = ui.getChoiceFromUserInput(venueArray);
				handleVenueDetails();// this line here should print out the venue details for the venue number that was selected by the user

				
				// prints submenu 3, List Venue Spaces
				//ui.printHeader("What would you like to do next?");

				// prints submenu 4, Reserve a Space

				//exits the program
			} else if (Choice.equals(two)) {
				ui.exitMessage();
				System.exit(0);
			} else {
				ui.handleError();
			}

		}

	}

	//returns a list of venues that is 
	public List<Venue> handleListOfVenues() {
		List<Venue> venueList = venueDAO.getAllVenues();
		ui.selectListOfVenues(venueList);
		return venueList;
	}
	//returns a list of venue details
	public List<Venue> handleVenueDetails() {
		List<Venue> venueDetails = venueDAO.getVenueDetails();
		ui.selectVenueDetails(venueDetails);
		return venueDetails;

	}

//	public List<VenueSpace> handleVenueSpaces() {
//		List<VenueSpace> venueSpaces = venueSpaceDAO.getVenueSpacebyID(ID);
//		return venueSpaces;
//	}

}
