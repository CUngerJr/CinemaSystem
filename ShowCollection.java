package CBS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class ShowCollection

{

	private ArrayList<Show> shows = new ArrayList<Show>();
	private File ShowsFile;
	private File EventFile;
	private DateTime dateConvert = new DateTime();
	private TheaterCollection theaterCollect;
	private Seat[][] seats;
	private ShowEvent showEvents;
	private Date startDate = new Date(), endDate = new Date();
	protected static ArrayList<ShowEvent> showEv; 
	
	public ShowCollection() throws ParseException, IOException
	{
	setShows(new ArrayList<Show>());	
	showEv = new ArrayList<ShowEvent>();
	readFile();
	SEreadFile();
	}

	
		public void addShow(Show s) throws IOException, ParseException {
			theaterCollect = new TheaterCollection();
			getShows().add(s);
			writeFile();
			showInstanceByDate();
			SEwriteFile();
		}
		
		public void removeShow(Show s) throws IOException {
			getShows().remove(s);
			writeFile();
		}
		
		// Save Show File
		public void writeFile() throws IOException {
			ShowsFile = new File("ShowList.txt");
			FileWriter writer = new FileWriter(ShowsFile, false);
			BufferedWriter bw = new BufferedWriter(writer);
			
			for (Show s : getShows()) {
				bw.write(s.toString() + "\n");
			}
			bw.close();
			writer.close();
		}
		
		public void readFile() throws FileNotFoundException, ParseException
		{
	
			Scanner reader = new Scanner(new FileInputStream("ShowList.txt"));
				while (reader.hasNextLine())
				{
				
					String line = reader.nextLine();
					Scanner sc = new Scanner(line);
					sc.useDelimiter("\\;");
				
					String showName = sc.next();
					String cat = sc.next();
					String rating = sc.next();
					String startDate = sc.next();
					String endDate = sc.next();
					int id = sc.nextInt();
					shows.add(new Show(showName, cat, rating, startDate, endDate, id));
				}    
		}

		public ArrayList<Show> getShows() {
			return shows;
		}

		public void setShows(ArrayList<Show> shows) {
			this.shows = shows;
		}
		

		public void showInstanceByDate() throws ParseException, IOException {
			Calendar start = new GregorianCalendar();
			Calendar end = new GregorianCalendar();
			showEv = new ArrayList<>();
			SEreadFile();
				
			theaterCollect = new TheaterCollection();
					for (Show s : shows) 	{
						for (Theater t : theaterCollect.theaters) {
							startDate = dateConvert.convertDate(s.getStartDate());
							endDate = dateConvert.convertDate(s.getEndDate());
							start.setTime(startDate);
							end.setTime(endDate);
					
							// Save all dates in range to dateList
								 if (t.getTheaterID() == s.getTheaterID()) {		// Check theaterID with theater show is assigned to
									 while (start.before(end) || start.equals(end)) { 	// set seating instance for each date
										 Date result = start.getTime();
										 showEvents = new ShowEvent(s.getShowTitle(), s.getTheaterID(), result, t);
										 addShowEvent(showEvents);
										 start.add(Calendar.DATE, 1); // increment start date
									 }
								}
							}
				    
				} // End For
		} // End Method
		
		public void SEwriteFile() throws IOException {
			EventFile = new File("ShowEventList.txt");
			FileWriter writer = new FileWriter(EventFile, false);
			BufferedWriter bw = new BufferedWriter(writer);

			for (ShowEvent se : getShowEvent()) {
				bw.write(se.toString() + "\n");
			}
			bw.close();
			writer.close();
		}

		public void SEreadFile() throws ParseException, IOException {
				TheaterCollection thtrcoll = new TheaterCollection();
			Scanner reader = new Scanner(new FileInputStream("ShowEventList.txt"));
			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				Scanner sc = new Scanner(line);
				sc.useDelimiter("\\;");
				String title = sc.next();
				int id = sc.nextInt();
				String date = sc.next();
				Date d = dateConvert.convertDate(date);
				
				for (Theater t : thtrcoll.getTheaters())
				{
					if (t.getTheaterID() == id)
					{
					showEv.add(new ShowEvent(title, id, d, t));
					}
				}
				
			}
			reader.close();
		}

		public static void addShowEvent(ShowEvent ev) {
			showEv.add(ev);
		}

		public ArrayList<ShowEvent> getShowEvent() {
			return showEv;
		}

		public String toString() {
			return showEvents.getTitle() + " " + showEvents.getTheaterID() + " " + showEvents.getDate() + " " + showEvents.getSeats();
		}


}// End Class



