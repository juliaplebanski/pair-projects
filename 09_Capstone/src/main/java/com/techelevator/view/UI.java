package com.techelevator.view;


import java.io.InputStream;


import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.util.LinkedHashSet;
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
	
	/**Used for each header that prints out to our console*/
	public void printHeader(String headerInformation) {
		System.out.println(headerInformation);

	}
	/**Returns user to the previous menu.*/
	public void returnsUserToPreviousMenu() {
		System.out.println("returning to previous menu....");

	}
	
	/**Prints the main menu.*/
	public void printMainMenu() {
		System.out.println("***************************************************************");
		System.out.println("Welcome to Excelsior Venues ");

		System.out.println("****************************************************************");
	}	

	/**Prints the reservation header.*/
	public void printReservationHeader() {
		System.out.println("*********** The following spaces are available based on your needs ******************************");
		System.out.println(String.format("%-50s", "Space ID | Name | Max Occup | Accessible | Daily Rate($)"));
		System.out.println("------------------------------------------------------------------------------------");
		
	}
	
	/**This method informs the user that there is no available space.*/
	public void printNoSpace() {
		System.out.println("Unvaliable spaces for that date range. Please try again.");
	}	

	/**This method gets user's choice from from available options.*/
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	/**This method gets Choice from user's input.*/
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
	
	/**This method displays menu options.*/
	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(">>>>>>>Please choose an option >>>>>>>>>> ");
		out.flush();
	}
	
	/**Used when invalid value is put in by user.*/
	public void handleError() {

		System.out.println("\n** option is invalid ***\n");

	}
	public void invalidDateError() {

		System.out.println("Please select a different date range***\n");

	}

	/**Prints out a list of all Venues with their respective place in the array+1 */
	public void printVenueList(List<Venue> venueList) {
		
		 //List<Venue> listWithoutDuplicates = venueList.stream().distinct().collect(Collectors.toList());
         

		  if(venueList .size() > 0) {
			 
	
			for (int i = 0; i < venueList .size(); i++) {
				System.out.println((i + 1) + ") " + venueList .get(i).getName() + " " + venueList .get(i).getCategory());
				
			}
		} else {
			System.out.println("\n*** No results ***");
		 
		}
	}
	
	/**Prints out a list of all spaces*/
	public void printSpaceList(List<VenueSpace> spaceList) {

		if (spaceList.size() > 0) {
			for (int i = 0; i < spaceList.size(); i++) {
				System.out.println((i + 1) + ") " + spaceList.get(i).getName());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}
	
	/**Prints out a list of available spaces.*/
	public void printListOfAvaliableSpaces(List<VenueSpace> listAvaliableSpaces) {

		if (listAvaliableSpaces.size() > 0) {
			
			for (int i = 0; i < listAvaliableSpaces.size(); i++) {
				System.out.println(listAvaliableSpaces.get(i).getSpaceID() + "| " + listAvaliableSpaces.get(i).getName() + "  |  "  + listAvaliableSpaces.get(i).getMaxOccupancy() + " |  " + listAvaliableSpaces.get(i).isAccessible() +  "   | " +  listAvaliableSpaces.get(i).getDaily_rate());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}

	/**Prints out Venue details*/
	public void printVenueDetails(List<Venue> venueDetails) {
		ArrayList<Venue> finalVenueDetailsList = new ArrayList<Venue>(new LinkedHashSet<Venue>(venueDetails));
	

		if (finalVenueDetailsList.size() > 0) {
			for (int i = 0; i < finalVenueDetailsList.size(); i++) {
				System.out.println("Name: " + finalVenueDetailsList.get(i).getName());
				System.out.println("Location: " + finalVenueDetailsList.get(i).getLocation());
				System.out.println("Categories: " + finalVenueDetailsList.get(i).getCategory());
				System.out.println("Details: " + finalVenueDetailsList.get(i).getDescription());
			}
		}
	}
	
	/**Used to print out the reservation information when reservation in searched by id*/
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
	
	/**Used to get the format of the date the user inputs*/
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

	/**Prints out the confirmation message when the reservation is complete*/
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
	
	/**Prints exit message and exits the program*/
	public void exitMessage() {
		System.out.println("Have A Great Day!");
	}
}
