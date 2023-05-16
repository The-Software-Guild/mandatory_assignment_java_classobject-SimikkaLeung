/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.model;

// The MPAA Rating has five ratings. They are constant and are best represented by enum.

public enum MPAARating {
	G,
	PG,
	PG_13 {
		public String toString () {		//	The enum identifier does not accept a hyphen, so I override the toString methods.
			return "PG-13";
		}
	},
	R,
	NC_17
	{
		public String toString () {
			return "NC-17";
		}
	};
	
	// The DvdDAOImpl calls this method when promoting the user to provide the rating.
	public static String listAllRatings() {			
		return "1. G\t2. PG\t3. PG-13\t4. R\t5. NC-17";
	}
	

}
