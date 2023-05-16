/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.dao;

import com.exceptions.*;
/* Allow the user to add a DVD to the collection
Allow the user to remove a DVD from the collection
Allow the user to edit the information for an existing DVD in the collection
Allow the user to list the DVDs in the collection
Allow the user to display the information for a particular DVD
Allow the user to search for a DVD by title
Load the DVD library from a file
Save the DVD library back to the file when the program completes
Allow the user to add, edit, or delete many DVDs in one session
 * 
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.model.Dvd;
import com.model.MPAARating;


//  This is just an implementation version of DvdDAO. It is possible to create another DAOImpl and use it in the app instead.
public class DvdDAOImpl implements DvdDAO{

	public List<Dvd> readDataFromFile(List<Dvd> dvdList) throws IOException, ClassNotFoundException {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		boolean fileIsFound = false;
		Dvd dvd;
		
		try {
			fis = new FileInputStream("DVDLibrary.txt");
			fileIsFound = true;
		} catch (FileNotFoundException ex) {}
		
		if (fileIsFound) {
			ois = new ObjectInputStream(fis);
			while (true) {
				try {
					dvd = (Dvd) ois.readObject();
					dvdList.add(dvd);
				} catch (EOFException ex) {
					break;
				}				
			}
			ois.close();
			fis.close();
		}
		
		Collections.sort(dvdList);
		return dvdList;
	}
	
	public void writeDataToFile(List<Dvd> dvdList) throws IOException {
		FileOutputStream fos = new FileOutputStream("DVDLibrary.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Collections.sort(dvdList);
		for (Dvd dvd: dvdList) {
			oos.writeObject(dvd);
		}
	
		oos.close();
		fos.close();
	}
	
	// title is a mandatory field.
	public String inputTitle (Scanner keyboard) {
		String title = "";		// Some DVD titles may contain numbers and symbols, so this method will not remove any characters.

		do {
			System.out.println("Please enter the title: ");
			try {
				title = keyboard.nextLine();
				if (title.length() == 0) {
					throw new EmptyInputException();
				}
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: A title is a required field.");
			}
		} while (title.length() == 0);
		return title;
	}
	
	
	//releaseDate is an optional field. 
	public LocalDate inputReleaseDate (Scanner keyboard) {
		LocalDate date = null;
		String strDate = "";

		boolean toContinue = true;
		
		do {
			System.out.println("Please enter the DVD release date (YYYY-MM-DD) or '*' to skip it: ");
			try {
				strDate = keyboard.nextLine();
				if (strDate.length() == 0) {
					throw new EmptyInputException();
				}
				if (strDate.equals("*")) {
					toContinue = false;
				} else {
					date = LocalDate.parse(strDate);
					toContinue = false;
				}
				
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be null.");
			} catch (DateTimeParseException dateEx) {
				System.out.println("Invalid Input: The input is not in a valid date format.");
			}
			
			
		} while (toContinue);
		
		return date;
	}
	
	//MPAA Rating is an optional field. 
	public MPAARating inputMpaaRating (Scanner keyboard) {
		String strSelection = "";
		int selection = 0;
		MPAARating rating = null;
		boolean toContinue = true;
		
		do {
			System.out.println("Please enter the number (1-5) representing the MPAA rating or '*' to skip it. ");
			System.out.println(MPAARating.listAllRatings());
			try {
				strSelection = keyboard.nextLine();
				if (strSelection.length() == 0) {
					throw new EmptyInputException();
				}
				if (strSelection.equals("*")) {
					toContinue = false;
				} else {
					selection = Integer.parseInt(strSelection);
					if (selection >=1 && selection <= 5) {
						//"1. G\t2. PG\t3. PG-13\t4. R\t5. NC-17";
						switch (selection) {
						case 1: rating = MPAARating.G;
								break;
						case 2: rating = MPAARating.PG;
								break;
						case 3: rating = MPAARating.PG_13;
								break;
						case 4: rating = MPAARating.R;
								break;
						case 5: rating = MPAARating.NC_17;
								break;					
						}					
						toContinue = false;
					} else {
						throw new InputOutOfRangeException();
					}
				}				
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be null.");
			} catch (NumberFormatException numberEx) {
				System.out.println("Invalid Input: The input is not a whole number.");
			} catch (InputOutOfRangeException rangeEx) {
				System.out.println("Invalid Input: The input must be 1-5 or *.");
			}		
			
		} while (toContinue);
					
		return rating;
	}
	
	
	//Director Name is an optional field. 
	public String inputDirectorName (Scanner keyboard) {
		String strDirectorName = "", strDirectorNameCleaned="";

		boolean toContinue = true;
		
		do {
			System.out.println("Please enter the director name or '*' to skip it: ");
			try {
				strDirectorName = keyboard.nextLine();
				if (strDirectorName.length() == 0) {
					throw new EmptyInputException();
				}
				if (strDirectorName.equals("*")) {
					toContinue = false;
				} else { 
					strDirectorNameCleaned = strDirectorName.replaceAll("[^A-Za-z]"," ");
					if (strDirectorName.equals(strDirectorNameCleaned)) {
						toContinue = false;
					} else {
						throw new NonAlphabeticInput();
					}
				}
				
				
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be null.");
			} catch (NonAlphabeticInput alphaEx) {
				System.out.println("Invalid Input: The input can only contain alphabets.");
			}
			
			
		} while (toContinue);
		
		return strDirectorNameCleaned;
	}
	
	//Studio is an optional field. 
	public String inputStudio (Scanner keyboard) {
		String strStudio = "", strStudioCleaned="";

		boolean toContinue = true;
		
		do {
			System.out.println("Please enter the studio or '*' to skip it: ");
			try {
				strStudio = keyboard.nextLine();
				if (strStudio.length() == 0) {
					throw new EmptyInputException();
				}
				if (strStudio.equals("*")) {
					toContinue = false;
				} else {
					strStudioCleaned = strStudio.replaceAll("[^A-Za-z0-9]"," ");	// A studio name may have numeric characters.
					if (strStudio.equals(strStudioCleaned)) {
						toContinue = false;
					} else {
						throw new NonAlphanumericInput();
					}
				}
				
				
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be null.");
			} catch (NonAlphanumericInput alphaEx) {
				System.out.println("Invalid Input: The input can only contain alphabets and numbers.");
			}			
			
		} while (toContinue);
		
		return strStudioCleaned;
	}
	
	
	//User Rating is an optional field. 
	public int inputUserRating (Scanner keyboard) {
		String strRating = "";
		int rating = 0;
		boolean toContinue = true;
		
		do {
			System.out.println("Please enter your own rating (a whole number from 1 to 10) or '*' to skip it. ");
			try {
				strRating = keyboard.nextLine();
				if (strRating.length() == 0) {
					throw new EmptyInputException();
				}
				if (strRating.equals("*")) {
					toContinue = false;
				} else {
					rating = Integer.parseInt(strRating);
					if (rating >=1 && rating <= 10) {
						toContinue = false;
					} else {
						throw new InputOutOfRangeException();
					}
				}
					
			} catch (EmptyInputException emptyEx) {
				System.out.println("Invalid Input: The input cannot be null.");
			} catch (NumberFormatException dateEx) {
				System.out.println("Invalid Input: The input is not a whole number.");
			} catch (InputOutOfRangeException dateEx) {
				System.out.println("Invalid Input: The input must be 1-10.");
			}
			
			
		} while (toContinue);
					
		return rating;
	}
	
	//User note is an optional field. 
	public String inputUserNote (Scanner keyboard) {
		String note;		// A user note can contain numbers and symbols, so this method will not remove any characters.

		System.out.println("Please enter your own note in one single paragraph or press enter to skip it. ");
		note = keyboard.nextLine();
		
		return note;
	}
	
	//Allow the user to add a DVD to the collection
	public Dvd create(Scanner keyboard) {
		Dvd dvd = new Dvd(inputTitle(keyboard), inputReleaseDate(keyboard), inputMpaaRating(keyboard), inputDirectorName(keyboard), inputStudio(keyboard), inputUserRating(keyboard), inputUserNote(keyboard));
		
		return dvd;
	}

	
	//Allow the user to add one or many DVDs in one session
	public List<Dvd> addToCollection (List<Dvd> dvdList, Scanner keyboard) {
		String response = "";
		System.out.println("Enter 'Y' if you want to add a dvd to collection, or anything else to go back to the main menu." );
		response = keyboard.nextLine();
		
		while (response.toUpperCase().equals("Y")) {
			dvdList.add(create(keyboard));		// An ArrayList
			System.out.println("The DVD is added to the collection successfully.");			
			System.out.println("Enter 'Y' if you want to add another dvd to collection, or anything else to go back to the main menu." );
			response = keyboard.nextLine();
		}
		return dvdList;
	}
	
	
	// Allow the user to search for a DVD by title, return only the first found.
	// It is an important helper function as the program needs to search for a particular DvD 
	// before editing, deleting or displaying it on the screen.
	public Dvd searchByTitle (List<Dvd> dvdList, Scanner keyboard) {
		Dvd dvd = null;
		System.out.println("Search for a DVD by title");
		
		if (dvdList.isEmpty()) {
			System.out.println("The DVD Library is empty! Please add new DVDs to the collection!");
		} else {
			String title = inputTitle(keyboard).toUpperCase();		// This comparison is case-insensitive.
			for (Dvd d: dvdList) {
				if (d.getTitle().toUpperCase().equals(title)) {
					dvd = d;
					break;		// Return only the first found
				}
			}

			if (dvd == null) {
				System.out.println("Dvd not found!");
			} else {
				System.out.println("The dvd is found!");				
			}
		}

		return dvd;
	}
	

	//Allow the user to remove one or many DVDs in one session
	public List<Dvd> removeFromCollection (List<Dvd> dvdList, Scanner keyboard) {
		String response = "";
		if (dvdList.isEmpty()) {
			System.out.println("The DVD Library is empty! Please add new DVDs to the collection!");
		} else {
			System.out.println("Enter 'Y' if you want to remove a dvd to collection, or anything else to go back to the main menu." );
			response = keyboard.nextLine();
			
			while (response.toUpperCase().equals("Y")) {
				Dvd dvd = searchByTitle(dvdList, keyboard);
				
				if (dvd != null) {
					dvdList.remove(dvd);		
					System.out.println("The dvd is removed from the library succesfully.");
				}		
				System.out.println("Enter 'Y' if you want to remove another dvd from collection, or anything else to go back to the main menu." );
				response = keyboard.nextLine();
			}
		}
		
		return dvdList;
	}
	
	

	//Allow the user to edit one or many DVDs in one session
	public List<Dvd> editCollection(List<Dvd> dvdList, Scanner keyboard) {
		String response = "";
		if (dvdList.isEmpty()) {
			System.out.println("The DVD Library is empty! Please add new DVDs to the collection!");
		} else {
			System.out.println("Enter 'Y' if you want to edit a dvd in the collection, or anything else to go back to the main menu." );
			response = keyboard.nextLine();
			
			while (response.toUpperCase().equals("Y")) {
				Dvd dvd = searchByTitle(dvdList, keyboard);
				String option = "";
				boolean toContinue = true;
				if (dvd != null) {
					do {
						System.out.println(dvd);			
						System.out.println("Please select the field that you want to edit, or enter anything else to exit the editing panel.");
						System.out.println("1. Title\n2. Release Date\n3. MPAA Rating\n4. Director's Name\n5. Studio\n6. userRating\n7. userNote");
						
						option = keyboard.nextLine();
						
						switch (option) {
						case "1" : dvd.setTitle(inputTitle(keyboard));
						break;
						case "2" : dvd.setReleaseDate(inputReleaseDate(keyboard));
						break;
						case "3" : dvd.setMpaaRating(inputMpaaRating(keyboard));
						break;
						case "4" : dvd.setDirectorName(inputDirectorName(keyboard));
						break;
						case "5" : dvd.setStudio(inputStudio(keyboard));
						break;
						case "6" : dvd.setUserRating(inputUserRating(keyboard));
						break;
						case "7" : dvd.setUserNote(inputUserNote(keyboard));
						break;
						default : toContinue = false;
						} 
					} while (toContinue);	
				}	
				System.out.println("Enter 'Y' if you want to edit another dvd in the collection, or anything else to go back to the main menu." );
				response = keyboard.nextLine();
			}
		}
		
		return dvdList;
	}
	
	
	
	
	// Allow the user to list the DVDs in the collection
	public List<Dvd> displayAll(List<Dvd> dvdList) {
		Collections.sort(dvdList);		// sort the list by title in an ascending order using the Comparable interface.

		return dvdList;
	}
	
	//Allow the user to display the information for a particular DVD
	public Dvd displayOne(List<Dvd> dvdList, Scanner keyboard) {
		Dvd dvd = null;
		if (dvdList.isEmpty()) {
			System.out.println("The DVD Library is empty! Please add new DVDs to the collection!");
		} else {
			dvd = searchByTitle (dvdList, keyboard);

		}
		return dvd;	
	}

}

