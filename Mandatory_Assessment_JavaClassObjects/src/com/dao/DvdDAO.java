/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.model.Dvd;
import com.model.MPAARating;


/* I created a DAO interface because it can be used to create different versions of DAO.
 * 
 */

public interface DvdDAO {
	
	// DAO is responsible for passing data between the program and the file.
	public List<Dvd> readDataFromFile(List<Dvd> dvdList) throws IOException, ClassNotFoundException; 
	
	public void writeDataToFile(List<Dvd> dvdList) throws IOException;
	
	// title is a mandatory field.
	public String inputTitle (Scanner keyboard);
	
	//releaseDate is an optional field. 
	public LocalDate inputReleaseDate (Scanner keyboard);
	
	//MPAA Rating is an optional field. 
	public MPAARating inputMpaaRating (Scanner keyboard);
	
	//Director Name is an optional field. 
	public String inputDirectorName (Scanner keyboard);
	
	//Studio is an optional field. 
	public String inputStudio (Scanner keyboard);

	//User Rating is an optional field. 
	public int inputUserRating (Scanner keyboard);
	
	//User note is an optional field. 
	public String inputUserNote (Scanner keyboard);
	
	//Allow the user to add a DVD to the collection
	public Dvd create(Scanner keyboard);

	//Allow the user to add one or many DVDs in one session
	public List<Dvd> addToCollection(List<Dvd> dvdList, Scanner keyboard);
	
	//Allow the user to search for a DVD by title, return only the first found.
	public Dvd searchByTitle (List<Dvd> dvdList, Scanner keyboard);

	//Allow the user to remove one or many DVDs in one session
	public List<Dvd> removeFromCollection(List<Dvd> dvdList, Scanner keyboard);

	//Allow the user to edit one or many DVDs in one session
	public List<Dvd> editCollection(List<Dvd> dvdList, Scanner keyboard);
	
	// Allow the user to list the DVDs in the collection
	public List<Dvd> displayAll(List<Dvd> dvdList);
	
	//Allow the user to display the information for a particular DVD
	public Dvd displayOne(List<Dvd> dvdList, Scanner keyboard);
	
}
