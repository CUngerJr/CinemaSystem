package CBS;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Theater {
// Variables
	private String theaterName;
	private int theaterID;
	protected Seat[][] seating;
	private File theaterFile;
	private FileReader retrieveTheaterFile;
	protected ArrayList<Theater> theaterarr;

	public Theater(String theaterName, int theaterID, int rows, int seats) {
		this.theaterName = theaterName;
		this.theaterID = theaterID;
		seating = new Seat[rows][seats];

		for (int i = 0; i < seating.length; i++) {			// Loop for Rows
			for (int j = 0; j < seating[i].length; j++) {	// Loop for Each Seat in a Row
				seating[i][j] = new Seat((seats*i) + j + 1);	// Row and Seat Number
			}
		}
		theaterarr = new ArrayList<>();
	}

	// Get Theater Name
	public String getTheaterName() {
		return theaterName;
	}

	// Set Theater Name
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	
	public void setTheaterID(int id) {
		theaterID = id;
	}
	
	public int getTheaterID() {
		return theaterID;
	}

	// Get the Total Number of Seats in one Row
	public int getNumSeats() {
		return seating[0].length;
	}

	// Get the Total Number of Rows
	public int getNumRows() {
		return seating.length;
	}

	// Get The Total Number of Seats in the Theater
	public int getTotalSeats() {
		return (getNumSeats() * getNumRows());
	}

	// Set seat status by row and seat number to reserved
	public void fillSeat(int x, int y) {
		seating[x-1][y-1].setSeatStatus(true);
	}

	// Change seat status from reserved to available
	public void cancelSeat(int x, int y){
		seating[x-1][y-1].setSeatStatus(false);
	}

	// Check a seat's status
	public boolean checkSeat(int x, int y) {
		boolean status;
		int sn = seating[x-1][y-1].getSeatNum();
		if (seating[x-1][y-1].getSeatStatus() == true) {
			status = false;	
		}
		else {
			status = true;
		}
		return status;
	}
	
	// Checks to see how many total seats are available in one theater (good for an initial check in CBS to save Runtime)
	public int checkAvailableSeats() {
		int f = 0;
		for (int i = 0; i < seating.length; i++) {        // number of rows
			System.out.println("\n");
			for (int j = 0; j < seating[i].length;) { 		// number of seats in one row
				System.out.println("Row: " + (i + 1) + " Seat Number: " + (j + 1));
				if (seating[i][j].getSeatStatus() == true) {		// keep track of the number of seats that are reserved
					f=f+1;
           	 		j++;
				}
				else {
					j++;
				}
			}
		}
		return getTotalSeats() - f; 		// subtract total seats from number of reserved seats
	}										// and return the total number of available seats in the theater

	// Set the seating instance
	public void setSeating(Seat[][] seats) {
		this.seating = seats;
	}
	
	public Seat[][] getSeating() {
		return seating;
	}

	// Override the toString method
	public String toString() {
		return getTheaterName() + ";" + getTheaterID() + ';' + getNumRows() + ";" + getNumSeats() + ";" + getTotalSeats();
	}
	
	// Display Seating of Theater on Console (Won't need once GUI is done. Used for testing and as an emergency backup)
	public void theaterSeatingDisplay() {
		System.out.println("FRONT\n");			// Front of Theater
		
		for (int i = 0; (i < seating.length); i++) { 			// Loop through Rows
			System.out.print("Row: " + (i + 1) + " - ");	// Print Row Number
			
			int j = 0;
			while (j < seating[i].length) {				// Loop through seats in a row
				if (!seating[i][j].getSeatStatus() == true) {	// If seat is NOT RESERVED
					System.out.print(" | " + (j + 1) + " | ");		// Print Seat Number
					j++; 
				}
				else {											// Seat IS RESERVED
					System.out.print(" | " + "X" + " | ");		// Print X
					j++;
				}
			}
			System.out.print("\n");		// New Line for another Row
		}
		System.out.print("\nBACK");			// Back of Theater
	}


} // End Class
