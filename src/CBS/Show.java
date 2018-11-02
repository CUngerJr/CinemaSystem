package CBS;


//DONE.
//COMMENTED.

import java.text.ParseException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Show // This class creates a seat object that is used by the Row and CBS classes.
{// Begin Show class.
	private String showTitle;// This variable holds the value for show title.
	private String showCategory;// This variable holds the value for show category.
	private int theaterID;// This variable holds the value for theater ID.
	private String showRating;// This variable holds the value for show rating.
	private String startDate;// This variable holds the value for start date.
	private String endDate;// This variable holds the value for end date.
	private ArrayList<Show> showList;// This array list is created to hold the list of shows, and is type Show.
	private File showsFile;// This creates a show variable with type file.
	TheaterCollection theater;

	public Show(String title, String category, String rating, String start, String end, int ID) throws ParseException // Constructor.
	{// Start Constructor.
		showTitle = title;// Initializes the show title variable.
		showCategory = category;// Initializes the show category variable.
		showRating = rating;// Initializes the show rating variable.
		startDate = start; // Initializes the show start date variable.
		endDate = end;// Initializes the show end date variable.
		this.theaterID = ID;// Initializes the theater ID.
		showList = new ArrayList<Show>();// Initializes the show list array list.
	}// End constructor.

	public Show() {
		showList = new ArrayList<Show>();// Initializes the show list array list as a new array list.
	}

	public void loadShow()// This method takes the data via File IO and loads it into the variables.
	{
		// Takes the data via File IO and loads it into the variables
	}

	public String getShowTitle()// This method gets the show title.
	{
		return showTitle;// Returns the show title variable when the method is called.
	}

	public String getShowCategory()// Gets the show category.
	{
		return showCategory;// Returns the value of show category when this method is called.
	}

	public int getTheaterID() // Gets the theater ID.
	{
		return theaterID;// Returns the value of theater ID when this method is called.
	}

	public String getStartDate()// Gets the start date.
	{
		return startDate; // Returns the value of start date when this method is called.
	}

	public String getEndDate() // Gets the end date.
	{
		return endDate;// Returns the value of end date when this method is called.
	}

	public String getShowRating()// Gets the show rating.
	{
		return showRating;// Returns the value of the show rating when this method is called.
	}

	public void setShowTitle(String title)// Sets the show title.
	{
		showTitle = title;// Sets the show title to a String parameter named title.
	}

	public void setShowCategory(String category)// Sets the show category.
	{
		showCategory = category;// Sets the show category to a String parameter named category.
	}

	public void setTheaterID(int ID)// Sets the theater ID.
	{
		theaterID = ID;// Sets the theater ID to an integer parameter named ID.
	}

	public void setStartDate(String start)// Sets the start date.
	{
		startDate = start;// Sets the start date to a String parameter named start.
		// Change to format "MM/dd/yyyy"
	}

	public void setEndDate(String end)// Sets the end date.
	{
		endDate = end;// Sets the end date to a String parameter named end.
		// Change to format "MM/dd/yyyy"
	}

	public void setShowRating(String rating)// Sets the show rating.
	{
		showRating = rating;// Sets the show rating to a String parameter named rating.
	}

	public void addShow(Show s) throws IOException // This method adds a show to the show list array list. Throws an
, ParseException
													// IOException if it can not.
	{
		showList.add(s);// Adds the s variable (which is type Show) to the show list array list.
		writeToFile();// Directs this method to the writeToFile method.
	}

	public String toString() // Overrides the toString method. This method returns the title, category,
								// rating, start and end date, and ID of the show.
	{
		return getShowTitle() + ";" + getShowCategory() + ";" + getShowRating() + ";" + getStartDate() + ";"
				+ getEndDate() + ";" + getTheaterID();
	} // The delimiter ";" is used so we know when one value ends and the next one
		// begins.
	
	public void display() {
		System.out.println("\nShow Title: " + getShowTitle() + "\nCategory: " + getShowCategory() + "\nRating: " + getShowRating() + "\nPlays From: " + getStartDate() + " to "
				+ getEndDate() + "\nTheater ID: " + getTheaterID() + "\n");
	}

	private void writeToFile() // Method that write the show info to a text file.
	{
		showsFile = new File("ShowList.txt");// Creates a new file called "ShowList.txt" that we will use to store show
												// info.
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(showsFile, true)); // Adds to a show list text file.
			for (Show s : showList) // For loop for each show object that is in show list array list.
			{
				bw.write(s.toString() + "\n"); // Writes the show info on a single line in the file.
				bw.newLine(); // Tells the file to start at the next line.
			}
			bw.close(); // Closes the buffered reader input.
		} catch (IOException e) // Catches an exception if the file write does not work.
		{
			System.out.println("Writer did not work."); // Error message that prints if the try block did not work.
		}
	}
}// End Show class.
