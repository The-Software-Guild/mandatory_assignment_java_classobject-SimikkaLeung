/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.model;

import java.io.Serializable;
import java.time.LocalDate;


// Since the program needs to write the object data to a file, the Dvd object needs to be serializable.
// The Comparable interface is needed to sort the dvdList.
// Although it is not required, a Dvd class can extend a superclass such as Video. 
public class Dvd extends Video implements Comparable<Dvd>, Serializable {

	private String title;
	private LocalDate releaseDate;
	private MPAARating mpaaRating;
	private String directorName;
	private String studio;
	private int userRating;
	private String userNote;
	
	public Dvd() {
		super();
	}	

	public Dvd(String title, LocalDate releaseDate, MPAARating mpaaRating, String directorName, String studio,
			int userRating, String userNote) {
		super(title, userRating, userNote);
		//this.title = title;
		this.releaseDate = releaseDate;
		this.mpaaRating = mpaaRating;
		this.directorName = directorName;
		this.studio = studio;
		//this.userRating = userRating;
		//this.userNote = userNote;
	}

	public String getTitle() {
		return super.getTitle();
	}

	public void setTitle(String title) {
		super.setTitle(title);
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MPAARating getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(MPAARating mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public int getUserRating() {
		return super.getUserRating();
	}

	public void setUserRating(int userRating) {
		super.setUserRating(userRating);
	}

	public String getUserNote() {
		return super.getUserNote();
	}

	public void setUserNote(String userNote) {
		super.setUserNote(userNote);
	}

	@Override
	public String toString() {
		return "DVD [title=" + super.getTitle() + ", releaseDate=" + releaseDate + ", mpaaRating=" + mpaaRating + ", directorName="
				+ directorName + ", studio=" + studio + ", userRating=" + super.getUserRating() + ", userNote=" + super.getUserNote() + "]";
	}

	@Override
	// sort the list by title in an ascending order.
	public int compareTo(Dvd o) {
		// TODO Auto-generated method stub
		return getTitle().compareTo(o.getTitle());
	}
	
}
