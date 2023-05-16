/*	Author: Simikka Leung
 * 	The purpose of this program is to store the information of different DVDs in a file 
 *  and retrieve the information from the file. This program adopts the MVC design pattern
 *  and dependency injection.
 */

package com.exceptions;

public class NonAlphanumericInput extends Exception {
	public NonAlphanumericInput() {
		super();
	}
}
