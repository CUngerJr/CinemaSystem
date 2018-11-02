package CBS;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Cinema booking system to manage and maintain theaters, shows, and bookings.
 *
 */
public class CinemaBookingSystem {
	private Show showClass;
	private ShowCollection showCollect;
	private ShowEvent showEvent;
	private Theater theater;
	private TheaterCollection theaterCollect;
	private Customer customer;
	private DateTime dateConverter = new DateTime();
	private Reservation reservation;
	private FileReader showFile;
	private String showName, showCategory, rating, startDate, endDate, theaterName, firstName, lastName, phoneNum;
	private int theaterID, rows, seatsPerRow, id;
	//private ArrayList<Theater> theaters;
	//private ArrayList<SeatBooking> seatBookings;
	
	/**
	 * Gets the list of shows.
	 * 
	 * @return The list of shows.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void getAllShows() throws IOException, ParseException {
		boolean found = false;
		showCollect = new ShowCollection();
		

		for (int j = 0; j < showCollect.getShows().size(); j++) {
			System.out.println(showCollect.getShows().get(j).getShowTitle());
		}
	}
	/**
	 * Find a show by the user-given name.
	 * 
	 * @param showName The name of the show to be searched.
	 * @return The show/shows that correspond to the given name.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public boolean findShowByName(String showTitle) throws ParseException, IOException {
		// Pull up by Show Title
		boolean found = false;
		showCollect = new ShowCollection();
		
		for (int j = 0; j < showCollect.getShows().size(); j++) {
			String title = showCollect.getShows().get(j).getShowTitle();
			if(title.equals(showTitle)) {
				 showCollect.getShows().get(j).display(); // Display Show Info to console
				 found = true;
				 pickDate(showCollect.getShows().get(j).getShowTitle());
				 return true;
			}
		}
		if (!found) { // Cannot find movie by this title
			System.out.println("The movie title " + showTitle + " cannot be found. Try Again.\n");
		}
		return false;
	}
		
	
	/**
	 * Find a show by a user-given date.
	 * 
	 * @param date The date of the show to be searched.
	 * @return The show/shows that correspond to the given date.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public boolean findShowByDate(String date) throws ParseException, IOException {
		// Pull All Shows by Date
		boolean found = false;
			Scanner fsbd = new Scanner(System.in);
			Date userDate = dateConverter.convertDate(date); // convert user date
		
			showCollect = new ShowCollection();

			for (int j = 0; j < showCollect.getShows().size(); j++) {
				String tempStartDate = showCollect.getShows().get(j).getStartDate();
				Date startDate = dateConverter.convertDate(tempStartDate);
			
				String tempEndDate = showCollect.getShows().get(j).getEndDate();
				Date endDate = dateConverter.convertDate(tempEndDate);
				// Check to see if user Date is the same as the starting date or ending date or falls between the starting date and ending date of a movie
				if (userDate.equals(startDate) || userDate.equals(endDate) || userDate.after(startDate) && userDate.before(endDate)) {
					System.out.println(showCollect.getShows().get(j).getShowTitle());
					System.out.println("Type in the show title for more information: ");
					String title = fsbd.nextLine();
					for (int se = 0; se < showCollect.getShowEvent().size();) {	// loop through showlist text to get title, date, an instance					
						if (showCollect.getShowEvent().get(se).getTitle().equals(title) && userDate.equals(showCollect.getShowEvent().get(se).getDate())) { 
							bookShow(showCollect.getShowEvent().get(se));
							return found = true;
						}
						else {
							se++;
						}
					}
				}
			}
			// If No Shows are found on that Date
			if (!found) {
				System.out.println("No Shows could be found by this date.");
			}
			return found = false;
	}
	
	/**
	 * Adds a show to the collection of shows.
	 */
	public void addShow() {
		Scanner sc = new Scanner(System.in);
		
		// Get information from admin
		System.out.println("Enter the show name to add (ex. My Movie):");
		showName = sc.nextLine();
		System.out.println("Enter this show's category (ex. Action):");
		showCategory = sc.nextLine();
		System.out.println("Enter this show's rating (ex. PG-13):");
		rating = sc.nextLine();
		System.out.println("Enter the start date of the show (mm/dd/yyyy): ");
		startDate = sc.nextLine();
		System.out.println("Enter the end date of the show (mm/dd/yyyy):  ");
		endDate = sc.nextLine();
		System.out.println("Enter the theater number that show is playing in:");
		theaterID = sc.nextInt();
		
		try {
			// create Show Object
			showClass = new Show(showName, showCategory, rating, startDate, endDate, theaterID);
			showCollect = new ShowCollection();
			showCollect.addShow(showClass);	// add show to arrayList to write to ShowList.txt
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		sc.close();
		}
		
	}
	
	/**
	 * Adds a theater to the collection of theaters.
	 * @throws IOException 
	 */
	public void addATheater() throws IOException {
		theaterCollect = new TheaterCollection();
		Scanner scan = new Scanner(System.in);
		
		// Get Theater Information from admin
		System.out.println("Enter Theater Name: ");
		theaterName = scan.nextLine();
		System.out.println("Enter Theater ID Number: "); 
		id = scan.nextInt();
		System.out.println("Enter Number of Rows in Theater: ");
		rows = scan.nextInt();
		System.out.println("Enter Number of Seats per Row in Theater: ");
		seatsPerRow = scan.nextInt();
		
		// Create theater object
		theater = new Theater(theaterName, id, rows, seatsPerRow);
		try {
			try {
				theaterCollect.addTheater(new Theater(theaterName, id, rows, seatsPerRow));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally {
			scan.close();
		}
	}
	
	// Add a customer to the customer text file
	public Customer addCustomer() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Customer's First Name (ex. John):");
		firstName = sc.nextLine();
		System.out.println("Enter the Customer's Last Name (ex. Doe):");
		lastName = sc.nextLine();
		System.out.println("Enter the Customer's Phone Number (ex. 123-456-7890):");
		phoneNum = sc.nextLine();
		
		customer = new Customer(firstName, lastName, phoneNum);	// create new customer object
		customer.addCustomer(customer);	// add customer to arrayList to add to text file
		return customer;
	}
	
	/**
	 * Books a new show based on a given show name and date.
	 * 
	 * @param showName The name of the show to be booked.
	 * @param date The date of the show to be booked.
	 */
	public void bookShow(ShowEvent showEv) {
		Scanner scan = new Scanner(System.in);
		String title = showEv.getTitle();		// get the title from the showEvent
		ArrayList<ReservedSeat> reservSeats = new ArrayList<>();	// arrayList to hold reserved seats incase more than 1
		ArrayList<Reservation> reservation = new ArrayList<>();		// arrqyList to hold reservation
		Customer custom;		// customer Object
		int numSeatsToReserve = 0;
		Reservation resClass = null;
		ReservedSeat rsrvSeat;
		boolean stopper = true;
		
		if (showEv.getAvailableSeats() != 0 || stopper == false) {	// Check if all the seats for this showevent are NOT booked
			System.out.println();
			showEv.getTheater().theaterSeatingDisplay();
			System.out.println("\n\nHow many seats would you like to reserve? ");	// how many seats are to be reserved
			numSeatsToReserve = scan.nextInt();
			
			int i = numSeatsToReserve;
			while (i > 0) {			// reserve seats by row and seat
				System.out.println("Please enter your desired Row Number: 	");
				int rowNum = scan.nextInt();
				
				System.out.println("Please enter the Seat Number in row " + rowNum + ": ");
				int seatNum = scan.nextInt();
				
					if (showEv.getTheater().checkSeat(rowNum, seatNum) == false) {	// check if this seat is already reserved
						System.out.println("Sorry, that seat is Reserved.");
					}
					else {
						showEv.getTheater().fillSeat(rowNum, seatNum);	// set set to reserved (reserve flag)
						System.out.println("Your seat at row " + rowNum + " seat " + seatNum + " for " + showEv.getTitle() + " has been Reserved\n");	// alert admin it has executed successfully
						rsrvSeat = new ReservedSeat(rowNum,seatNum);
						reservSeats.add(rsrvSeat);	// and to arrayList
						i--; // increment to next seat to reserve if more than one
						stopper = false;
					}
			}
		}
		else {  // All seats for this show on this date is booked
			System.out.println("Sorry, that show is all booked!");
		}
		
		custom = addCustomer();	// create customer to add to reservation
		resClass = new Reservation(custom, showEv, reservSeats);	// create reservation object
		try {
			resClass.addReservation(new Reservation(custom, showEv, reservSeats)); // add reservation to  arraylist to add to text file
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n");
		showEv.getTheater().theaterSeatingDisplay();	// show a display of the theater seating
		System.out.println("\n");
		resClass.display();
		System.out.println("\n\nThank you for Reserving your Seats at JCKDM Cinemas!");	// exit program
		scan.close();
		
		
		
	}
	
	// After an admin selects a movie title, they need to pick a date to that title.
	// calls the bookShow() once a title and date are matched and an instance of that specific theater seating is found
	public void pickDate(String showTitle) throws ParseException, IOException {
		Scanner d = new Scanner(System.in);
		boolean notFound = true, issue = false;
		String pickDate;
		Date picked = null;
		
		do {
			do {
			System.out.println("Please Enter what Date you would like to see this movie (mm/dd/yyyy): ");	// prompt admin
				pickDate = d.nextLine();	// get entry
				
				if (checkDateFormat(pickDate) == false) {		// check format can be correctly parsed
					System.out.println("Check your date format (mm/dd/yyyy)");
					issue = true;
					d.reset();
				}
				else {
					issue = false;
				}
			
			} while (issue == true);
				
				try {
					picked = dateConverter.convertDate(pickDate);	// convert to date type for comparison methods
				} catch (ParseException e) {
					e.getStackTrace();
				}
		 		
				showCollect = new ShowCollection();
				for (int se = 0; se < showCollect.getShowEvent().size();) {	// loop through showlist text to get title, date, an instance					
					if (showCollect.getShowEvent().get(se).getTitle().equals(showTitle) && picked.equals(showCollect.getShowEvent().get(se).getDate())) { 
							bookShow(showCollect.getShowEvent().get(se));  // call bookShow method and pass in the showevent instance of this specific theater
							notFound = false;
							break;// break loop once found
					}
					else {
						se++;
					}
				}
			if (notFound == true) {
				System.out.println("Please check your show date.");
			}
		} while (notFound == true);
	}
	
	// Method to check if date entered can be formated correctly
	private boolean checkDateFormat(String d) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false);
		try {
			Date test = format.parse(d);
		}
		catch (ParseException e) {
			return false;
		}
		return true;
	}

} // End Class
