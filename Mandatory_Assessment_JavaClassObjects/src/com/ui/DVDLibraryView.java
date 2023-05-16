/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.ui;

import java.util.List;
import java.util.Scanner;

import com.exceptions.*;
import com.model.Dvd;

public class DVDLibraryView {
	// This is the view of the MVC design pattern.
	// This view class is not affected by changes to the DAO, the Controller and the App classes.
	// Therefore, this view is reusable and it is not required to recompile when we change other classes.
	
	
	// Display the main menu
	public static void displayMenu() {
		System.out.println();
		System.out.println("^~^~^~^~^~^~^~^~^~^~^Welcome to the DVD Library!^~^~^~^~^~^~^~^~^~^~^");
		System.out.println("Please select from the following option (1-6)");
		System.out.println("1. Add one or more DVDs to the collection");
		System.out.println("2. Remove  one or more DVDs from the collection");
		System.out.println("3. Edit the information for one or more DVDs existing DVD");
		System.out.println("4. List all the DVDs in the collection");
		System.out.println("5. Search for a particular DVD by title and display its info");
		System.out.println("6. Save all the updates and close the library. ");
		System.out.println("^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^");
		System.out.println();
	}
	
	// Ask and receive the response from the user for the operation that they want to perform
	public int getResponse(Scanner keyboard) {
		String strOption = "";
		int option = 0;
		
		displayMenu();
		boolean toContinue = true;
		do {			
			try {
				System.out.println("Which service do you want (1-6)? ");
				strOption = keyboard.nextLine();				
				if (strOption.length() == 0) {
					throw new EmptyInputException();
				}
				option = Integer.parseInt(strOption);
				if (option <1 || option >6) {
					throw new InputOutOfRangeException();
				}				
				toContinue=false;
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be empty.");
			} catch (NumberFormatException numberEx) {
				System.out.println("Invalid Input: The input must be a whole number.");
			} catch (InputOutOfRangeException rangeEx) {
				System.out.println("Invalid Input: The input must be 1 to 6");
			}
		} while (toContinue);		
		
		return option;
	}
	
	// Receive data from DAO via the controller for output
	public static void displayAll(List<Dvd> dvdList) {
		System.out.println("Display the information for all DVDs in the collection");
		
		for (int i = 0; i < dvdList.size(); i++) {
			int position = i+1;
			System.out.println(position + ". " + dvdList.get(i));
		}
	}
	
	// Receive data from DAO via the controller for output
	public static void displayOne(Dvd dvd) {
		
		System.out.println("Display the information for a particular DVD");
		if (dvd!=null) {
			System.out.println(dvd);
		}	
	}
	
	
	// Show the goodbye message when the user wants to leave the program.
	public static void displayGoodbyeMessage() {
		System.out.println();
		System.out.println("^~^~^~^~^~^~^~^~ Thank you for using the DVD Library!~^~^~^~^~^~^~^~^");
		System.out.println("^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~^");
		System.out.println();
	}
	
}
