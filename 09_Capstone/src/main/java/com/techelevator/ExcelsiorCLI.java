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
import com.techelevator.view.UI;

public class ExcelsiorCLI {
	private static final String one = "List Venues";
	private static final String two = "Quit";
	private static final String[] MAIN_MENU_OPTION_ARRAY = new String[] { one, two };

	private static final String SUB_MENU_OPTION_VIEW_VENUES = "View venues";
	private static final String SUB_MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";
	private static final String SUB_MENU_OPTION_VENUE_DETAILS = "View venue Details";

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
<<<<<<< HEAD
		this.venueDAO = new JDBCVenueDAO(dataSource);
		this.venueSpaceDAO = new JDBCVenueSpaceDAO(dataSource);
		this.reservationDAO = new JDBCReservationDAO(dataSource);

	}

// Reminder: No System.out.printlns in this class
=======
 		this.venueDAO = new JDBCVenueDAO(dataSource);
 		this.venueSpaceDAO = new JDBCVenueSpaceDAO(dataSource);
 		this.reservationDAO = new JDBCReservationDAO(dataSource);
 		
 	}

>>>>>>> cf18ad1f3302910b8fd20b0dec29ea4c3cb1c033
	public void run() {
		boolean running = true;
		while (running) {
			ui.printFirstMenu();
			String Choice = (String) ui.getChoiceFromOptions(MAIN_MENU_OPTION_ARRAY);
<<<<<<< HEAD
			if (Choice == LIST_VENUES) {
				handleListOfVenues();

			}

		}
=======
			if(Choice.equals(one)) {
				handleListOfVenues();	
			}
			else if (Choice.equals(two)) {
					System.exit(0);
			}
				
			
		}
			
				
			
			
	
		
>>>>>>> cf18ad1f3302910b8fd20b0dec29ea4c3cb1c033

	}

	public List<Venue> handleListOfVenues() {
		List<Venue> venueList = venueDAO.getAllVenues();
		if(venueList.size() > 0) {
			for(Venue venue : venueList) {
				System.out.println(venue.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
		return venueList;
	}

}
