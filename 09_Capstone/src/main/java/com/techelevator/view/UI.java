package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
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
	//used for each header that prints out to our console
	public void printHeader(String headerInformation) {
		System.out.println(headerInformation);

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
		out.print("\n Please choose an option >>> ");
		out.flush();
	}
	
	//used when invalid value is put in by user
	public void handleError() {

		out.println("\n** option is invalid ***\n");

	}

	//prints out a list of all venues with their respective place in the array+1
	public void selectListOfVenues(List<Venue> venueList) {

		if (venueList.size() > 0) {
			for (int i = 0; i < venueList.size(); i++) {
				System.out.println((i + 1) + ") " + venueList.get(i).getName());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}

	//prints out venue details
	public void selectVenueDetails(List<Venue> venueDetails) {
		if (venueDetails.size() > 0) {
			for (int i = 0; i < venueDetails.size(); i++) {
				System.out.println("Name: " + venueDetails.get(i).getName());
				System.out.println("Location: " + venueDetails.get(i).getLocation());
				System.out.println("Categories: " + venueDetails.get(i).getCategory());
				System.out.println("Details: " + venueDetails.get(i).getDescription());
			}
		}
	}

	//prints out the list of spaces inside a particular venue that was selected
	public void listVenueSpaces(List<VenueSpace> venueSpaces) {
		if (venueSpaces.size() > 0) {
			for (int i = 0; i < venueSpaces.size(); i++) {
				// System.out.println("Name: " venueSpaces.get(i).getName()); in getName()
				// method, should be returning the venue name not the venue space name
				// System.out.println(number, name, open, close, daily rate, max occupancy) this
				// should print out in columbus
			}
		}
	}
	//method to print out the confirmation message when the reservation is complete
	public void confirmationMessage() {
		System.out.println("Thanks for submitting your reservation! The details for your event are listed below:");
		System.out.println("Confirmation #: " + reservation.getReservationID());
		System.out.println("Venue: " + venue.getName());
		System.out.println("Space: " + venueSpace.getName());
		System.out.println("Reservered for: " + reservation.getName());
		System.out.println("Attendees: " + reservation.getNumOfAttendees());
		System.out.println("Arrival Date: " + reservation.getStartDate());
		System.out.println("Depart Date: " + reservation.getEndDate());
		System.out.println("Total Cost: " + (venueSpace.getDaily_rate() * reservation.getLengthOfStay()));
	}
	//prints exit message and exits the program
	public void exitMessage() {
		System.out.println("Have A Great Day!");
	}
}
