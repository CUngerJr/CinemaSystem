package CBS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowEvent {
	
private String showTitle, theaterName;
private int theaterID, availableSeats, reservedSeats, totalSeats;
private Date date;
private Seat[][] seats;
private Theater thtr;


public ShowEvent(String title, int id, Date d, Theater t) 
{
	showTitle = title;
	theaterID = id;
	this.date = d;
	thtr = t;
		totalSeats = t.getTotalSeats();
		availableSeats = totalSeats;
		reservedSeats = 0;
	
}

	public ShowEvent(String title, int id, Date d, int totalSeats, int availSeats, int reservSeats) {
		showTitle = title;
		theaterID = id;
		this.date = d;
		this.totalSeats = totalSeats;
		availableSeats = availSeats;
		reservedSeats = reservSeats;
	}
	
	public ShowEvent(String title, String theaterName, Date d) {
		showTitle = title;
		this.theaterName = theaterName;
		date = d;
	}

	public String getTitle() {
		return showTitle;
	}
	
	public void setTitle(String title) {
		showTitle = title;
	}
	
	public int getTheaterID() {
		return theaterID;
	}
	
	public void setTheaterID(int id) {
		theaterID = id;
	}
	
	public Theater getTheater() {
		return thtr;
	}
	
	public void setTheater(Theater t) {
		thtr = t;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date d) {
		date = d;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int s) {
		totalSeats = s;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	
	public void setAvailableSeats(int avail) {
		availableSeats = avail;
	}
	
	public int getReservedSeats() {
		return reservedSeats;
	}
	
	public void setReservedSeats(int resrv) {
		reservedSeats = resrv;
	}
	
	public Seat[][] getSeats() {
		return seats;
	}
	
	public void setSeats(Seat[][] s) {
		seats = s;
	}

    
    public String toString() {
    	SimpleDateFormat outputDateConvert = new SimpleDateFormat("MM/dd/YYYY");
    		return  getTitle() +  ";" + getTheaterID() +  ";" + outputDateConvert.format(getDate()) + ";" + getTheater();
    }


}

