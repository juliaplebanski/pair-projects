package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class UI {
	private PrintWriter out;
	private Scanner in;

	public UI(InputStream input, PrintStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		
			
	}
	
	public void printFirstMenu() {
		System.out.println("What would you like to do?");
	
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			handleError();
		}
		if(choice == null) {
			out.println("\n***Please select a valid option ***\n");
		}
		
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\n Please choose an option >>> ");
		out.flush();
	}
	public void handleError() {
		
		out.println("\n** option is invalid ***\n");
		
	}

}
