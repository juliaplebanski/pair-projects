package com.techelevator.view;

import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.Venue;
import com.techelevator.model.domain.VenueSpace;

public class UI {
	private PrintWriter out;
	private Scanner in;
	private Venue venue;
	private VenueSpace venueSpace;
	private Reservation reservation;

	public UI(InputStream input, PrintStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);

	}
	
	/**used for each header that prints out to our console*/
	public void printHeader(String headerInformation) {
		System.out.println(headerInformation);

	}
	/** prints the main menu*/
	public void printMainMenu() {
		out.println("***************************************************************");
		out.println("Welcome to Excelsior Venues ");

		out.println("****************************************************************");
	}	


	public void printReservationHeader() {
		System.out.println("*********** The following spaces are available based on your needs ******************************");
		System.out.println(String.format("%-50s", "Space ID | Name | Max Occup | Accessible | Daily Rate($)"));
		System.out.println("        ------------------------------------------------------------------------------");
		
	}
	public void printNoSpace() {
		System.out.println("Unvaliable spaces for that date range. Please try again.");
	}	


	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			handleError();
		}
		if (choice == null) {
			out.println("\n***Please select a valid option ***\n");
		}

		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("Please choose an option >>> ");
		out.flush();
	}
	
	/*used when invalid value is put in by user*/
	public void handleError() {

		System.out.println("\n** option is invalid ***\n");

	}
	public void invalidDateError() {

		System.out.println("Please select a different date range***\n");

	}

	/**prints out a list of all venues with their respective place in the array+1 */
	public void printVenueList(List<Venue> venueList) {

		if (venueList.size() > 0) {
			for (int i = 0; i < venueList.size(); i++) {
				System.out.println((i + 1) + ") " + venueList.get(i).getName());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}
	public void printSpaceList(List<VenueSpace> spaceList) {

		if (spaceList.size() > 0) {
			for (int i = 0; i < spaceList.size(); i++) {
				System.out.println((i + 1) + ") " + spaceList.get(i).getName());
			//spaceList.get(i).getName());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}
	public void printListOfAvaliableSpaces(List<VenueSpace> listAvaliableSpaces) {

		if (listAvaliableSpaces.size() > 0) {
			
			for (int i = 0; i < listAvaliableSpaces.size(); i++) {
				System.out.println(listAvaliableSpaces.get(i).getSpaceID() + "| " + listAvaliableSpaces.get(i).getName() + "  |  "  + listAvaliableSpaces.get(i).getMaxOccupancy() + " |  " + listAvaliableSpaces.get(i).isAccessible() +  "   | " +  listAvaliableSpaces.get(i).getDaily_rate());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}

	/**prints out venue details*/
	public void printVenueDetails(List<Venue> venueDetails) {
		if (venueDetails.size() > 0) {
			for (int i = 0; i < venueDetails.size(); i++) {
				System.out.println("Name: " + venueDetails.get(i).getName());
				System.out.println("Location: " + venueDetails.get(i).getLocation());
				System.out.println("Categories: " + venueDetails.get(i).getCategory());
				System.out.println("Details: " + venueDetails.get(i).getDescription());
			}
		}
	}
	
	/*used to print out the reservation information when reservation in searched by id*/
	public void printReservationByID(Reservation reservation) {
		System.out.println("Name: " + reservation.getName());
		System.out.println("Start Date: " + reservation.getStartDate());
		System.out.println("End Date: " + reservation.getEndDate());
	}
	
	
	/**This method prints out a message to the console and also retrieves input from the user.*/
	public String getInputFromUser(String message) { 
		String input = "";
		while (input.isEmpty()) {
		
			System.out.print(message + " >>> ");
			input = in.nextLine();
		}
		return input;
	}
	
	/**used to get the format of the date the user inputs*/
	public LocalDate getDateFromUser(String input) {
	
		LocalDate startDate = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		while (startDate == null) {
		
			try {
			
				String Choice = getInputFromUser(input);
				startDate = LocalDate.parse(Choice, dateFormat);
			}
			catch (DateTimeParseException e)
			{
				System.out.println("Invalid date format.");
			}
		}

		return startDate;
	}

	/**method to print out the confirmation message when the reservation is complete*/
	public void printConfirmationMessage(Reservation reservation, Venue venue, VenueSpace venueSpace, double totalCost) {
		out.println("Thanks for submitting your reservation! The details for your event are listed below:");
		out.println("Confirmation #: " + reservation.getReservationID());
		out.println("Venue: " + venue.getName());
		out.println("Space: " + venueSpace.getName());
		out.println("Reservered for: " + reservation.getName());
		out.println("Attendees: " + reservation.getNumOfAttendees());
		out.println("Arrival Date: " + reservation.getStartDate());
		out.println("Depart Date: " + reservation.getEndDate());
		out.println("Total Cost: " + totalCost);
		//(venueSpace.getDaily_rate() * reservation.getLengthOfStay()));
	}
	
	/**prints exit message and exits the program*/
	public void exitMessage() {
		System.out.println("Have A Great Day!");
	}
}
