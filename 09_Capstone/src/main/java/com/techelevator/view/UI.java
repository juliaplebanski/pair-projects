package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.model.domain.Venue;

public class UI {
	private PrintWriter out;
	private Scanner in;
	private Venue venue;

	public UI(InputStream input, PrintStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);

	}

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

	public void handleError() {

		out.println("\n** option is invalid ***\n");

	}

	public void selectListOfVenues(List<Venue> venueList) {

		if (venueList.size() > 0) {
			for (int i = 0; i < venueList.size(); i++) {
				System.out.println((i + 1) + ") " + venueList.get(i).getName());
			}
		} else {
			System.out.println("\n*** No results ***");

		}
	}
	
	public void selectVenueDetails(List<Venue> venueDetails) {
		if (venueDetails.size() > 0) {
			for (int i = 0; i < venueDetails.size(); i++) {
				System.out.println("Name: " + venueDetails.get(i).getName());
				System.out.println( "Location: " + venueDetails.get(i).getLocation());
				System.out.println("Categories: " + venueDetails.get(i).getCategory());
				System.out.println("Details: " + venueDetails.get(i).getDescription());
			}
		}
	}
	
	public void exitMessage() {
		System.out.println("Have A Great Day!");
	}
}
