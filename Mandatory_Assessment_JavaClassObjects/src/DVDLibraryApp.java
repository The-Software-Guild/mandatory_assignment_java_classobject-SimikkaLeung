/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.controller.DVDLibraryController;
import com.dao.DvdDAO;
import com.dao.DvdDAOImpl;
import com.model.Dvd;
import com.ui.DVDLibraryView;


public class DVDLibraryApp {


	public static void main (String[] args) throws ClassNotFoundException, IOException {
		
		Scanner keyboard = new Scanner(System.in);
		List<Dvd> dvdList = new ArrayList<Dvd>();
		
		// DvdDAO is an interface and DvdDAOImpl is a class that implements it.
		// Polymorphism allows developers to create and switch to another version of DvdDAO 
		// implementation without changing this app class.
		
		DvdDAO dvdDAO = new DvdDAOImpl();
		
		// View is part of the MVC design pattern. It will display a menu and prompt for a response from the user.
		DVDLibraryView libraryView = new DVDLibraryView();
		
		// I adopt the MVC design pattern in this project, and this calls the controller.
		DVDLibraryController controller = new DVDLibraryController(dvdList, keyboard, dvdDAO, libraryView);
		controller.libraryControl();
				
		keyboard.close();	// Close the Scanner object to prevent resource leak.
	}
}
