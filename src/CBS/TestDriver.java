package CBS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDriver {
	static CinemaBookingSystem cbs = new CinemaBookingSystem();
	public static void main(String[] args) throws IOException, ParseException {
	
		MainMenu();
	}
	
	public static void MainMenu() throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		int userInput = 0;
		String userInputString;
		String date;
		boolean goAgain = false, wrong = false, checker = true, dateChecker = true;
		
		do {
			wrong = false;
			do {
				goAgain = false;
				Scanner optionScanner = new Scanner(System.in);
				System.out.println("************ WELCOME TO JCKDM CINEMA BOOKING SYSTEM ************");
				System.out.println("1. Search by title.");
				System.out.println("2. Search by date.");
				System.out.println("3. Login as an Administrator.");
				System.out.println("4. Quit.\n");
		
			try
			{
				userInput = optionScanner.nextInt();
			} 
			catch(InputMismatchException e) 
			{
				System.out.println("Error: Please Enter a correct Menu Number.");
				goAgain = true;
			}		
			System.out.println();
			optionScanner.reset();
		} while(goAgain);
		

		switch (userInput) {
		
		case 1:
			do {
				try {
					System.out.println("**** Current Shows Playing ****");
					
				cbs.getAllShows();
				} catch (IOException | ParseException e) {
				e.printStackTrace();
				}
				System.out.println("\nPlease enter a title to search for:");
					userInputString = sc.nextLine();
				try {
					checker = cbs.findShowByName(userInputString);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			
			} while (checker != true);
			sc.reset();
			break;

		case 2:
			do {
				System.out.println("What date would you like to see a Show (mm/dd/yyyy): ");
				date = sc.nextLine();
				
				try {
				dateChecker = cbs.findShowByDate(date);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
					System.out.println("Error: Please Try Again.");
				}
			} while (dateChecker != true);
			sc.reset();
			break;

		case 3:
			int adminPassword = 0;
			System.out.println("Please enter your Administrator password (123):");
			adminPassword = sc.nextInt();
			if (adminPassword == 123) {
				adminWindow();
			} else {
				System.out.println("Incorrect Password. System will now close.");
			}
			break;

		case 4:
			System.out.println("Good bye!");
			break;

		default:
			System.out.println("Please pick a correct number.\n");
			wrong = true;
		}
		} while (wrong == true);
	}

	public static void adminWindow() throws IOException, ParseException {
		Scanner adminScanner = new Scanner(System.in);
		Scanner removeT = new Scanner(System.in);
		TheaterCollection theaterCollect = new TheaterCollection();
		ShowCollection showCollect = new ShowCollection();
		CustomerCollection customerCollect = new CustomerCollection();
		int adminInput = 0;
		System.out.println("Welcome Administrator!");
		System.out.println("Enter '1' to add a theater.");
		System.out.println("Enter '2' to remove a theater.");
		System.out.println("Enter '3' to add a show.");
		System.out.println("Enter '4' to remove a show.");
		System.out.println("Enter '5' to add a customer.");
		System.out.println("Enter '6' to remove a customer.");
		System.out.println("Enter '7' to Quit.");
		adminInput = adminScanner.nextInt();

		switch (adminInput) {
		case 1:
			try {
				cbs.addATheater();  // create and add theater object to arraylist then to text file
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter a Theater Name to remove: 	");		// remove a theater object from the text file
			String theater = removeT.next();
			for (int i = 0; i < theaterCollect.getTheaters().size(); i++) {	// put text file in arrayList, find theater, remove, rewrite file
				String t = theaterCollect.getTheaters().get(i).getTheaterName();
					if (t.equalsIgnoreCase(theater)) {
						theaterCollect.removeTheater(theaterCollect.getTheaters().get(i));
					}
			}
			removeT.reset();
			break;

		case 3:
			cbs.addShow();	// add a show to arrayList then to text file
			break;

		case 4:
			System.out.println("Enter a Show Name to remove: 	");		// remove a show from the text file
			String show = removeT.next();
			for (int i = 0; i < showCollect.getShows().size(); i++) {		// put text file in arrayList, find show, remove, rewrite file
				String s = showCollect.getShows().get(i).getShowTitle();
					if (s.equalsIgnoreCase(show)) {
						showCollect.removeShow(showCollect.getShows().get(i));
					}
			}
			removeT.reset();
			break;

		case 5:
			cbs.addCustomer();	// adds a customer to the customer file
			break;

		case 6:
			System.out.println("Enter a Customer's last name to remove: 	");		// remove a customer from the text file
			String customer = removeT.next();
			for (int i = 0; i < customerCollect.getCustomers().size(); i++) {		// put text file in arrayList, find customer, remove, rewrite file
				String c = customerCollect.getCustomers().get(i).getLastName();
					if (c.equalsIgnoreCase(customer)) {
						customerCollect.removeCustomer(customerCollect.getCustomers().get(i));
					}
			}
			break;

		case 7:
			System.out.println("Good bye!");
			break;

		default:
			System.out.println("Input did not work. System will now close.");
			break;
		}
	}
}