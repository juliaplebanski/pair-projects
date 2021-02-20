package com.techelevator;

import org.apache.commons.dbcp2.BasicDataSource;
import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.JDBC.JDBCVenueDAO;
import com.techelevator.model.JDBC.JDBCVenueSpaceDAO;
import com.techelevator.model.dao.ReservationDAO;
import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSpaceDAO;
import com.techelevator.view.UI;

public class ExcelsiorCLI {
	private static final String LIST_VENUES = "1";
	private static final String QUIT = "2";
	private static final String[] MAIN_MENU_OPTION_ARRAY = new String[] { LIST_VENUES, QUIT };

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
 		this.venueDAO = new JDBCVenueDAO(dataSource);
 		this.venueSpaceDAO = new JDBCVenueSpaceDAO(dataSource);
 		this.reservationDAO = new JDBCReservationDAO(dataSource);
 		
 	}

	public void run() {
		
		// Reminder: No System.out.printlns in this class
		

	}
}
