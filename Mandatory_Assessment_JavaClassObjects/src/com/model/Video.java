/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.model;

import java.io.Serializable;


public class Video  implements Serializable{
	private String title;
	private int userRating;
	private String userNote;
	
	public Video() {

	}

	public Video(String title, int userRating, String userNote) {
		super();
		this.title = title;
		this.userRating = userRating;
		this.userNote = userNote;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserRating() {
		return userRating;
	}

	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	@Override
	public String toString() {
		return "Video [title=" + title + ", userRating=" + userRating + ", userNote=" + userNote + "]";
	}
	
//	@Override
//	// sort the list by title in an ascending order.
//	public int compareTo(Video o) {
//		// TODO Auto-generated method stub
//		return title.compareTo(o.getTitle());
//	}
	
}
