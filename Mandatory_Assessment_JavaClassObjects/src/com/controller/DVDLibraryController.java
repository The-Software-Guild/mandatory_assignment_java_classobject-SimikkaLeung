/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.controller;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import com.dao.DvdDAO;
import com.model.Dvd;
import com.ui.DVDLibraryView;

public class DVDLibraryController {
	//private static Scanner keyboard;
	private List<Dvd> dvdList;
	private Scanner keyboard;
	private DvdDAO dvdDAO;		// Declaring the interface as it allows polymorphism.
	private DVDLibraryView libraryView;
	
	// A parameterized constructor
	public DVDLibraryController(List<Dvd> dvdList, Scanner keyboard, DvdDAO dvdDAO, DVDLibraryView libraryView) {
		super();
		this.dvdList = dvdList;
		this.keyboard = keyboard;
		this.dvdDAO = dvdDAO;	// dependency injection
		this.libraryView = libraryView;
	}

	// This method controls the flows of the program.
	public void libraryControl() throws ClassNotFoundException, IOException {
		
		// DAO is responsible for the implementation of reading data from the file.
		dvdList = dvdDAO.readDataFromFile(dvdList);		
		
		int response = 0;
		do {
			response = libraryView.getResponse(keyboard);
			
			switch (response) {
			case 1:dvdList = dvdDAO.addToCollection(dvdList, keyboard);
				break;
			case 2:dvdList = dvdDAO.removeFromCollection(dvdList, keyboard);
				break;
			case 3:dvdList = dvdDAO.editCollection(dvdList, keyboard);
				break;
			case 4:DVDLibraryView.displayAll(dvdDAO.displayAll(dvdList));	// The controller passes the data from DAO to View for output
				break;
			case 5:DVDLibraryView.displayOne(dvdDAO.displayOne(dvdList, keyboard));	// The controller passes the data from DAO to View for output
				break;
			}			
			
		} while (response !=6);		
		
		// The view is responsible for the goodbye message.
		DVDLibraryView.displayGoodbyeMessage();
		// the DAO is responsible for writing the data to the file.
		dvdDAO.writeDataToFile(dvdList);		
	}
}
