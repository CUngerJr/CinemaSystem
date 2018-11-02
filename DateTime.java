package CBS;

//DONE.
//COMMENTED.

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime 
{//Start DateTime class.
	private Date date, startTime, endTime, time;//Creates Date variable for the date, start and end times, and the time.
	private String showDuration, showName;//Creates String variables for the show duration and show name.
	
	public DateTime(String showName, String date, String startTime, String endTime) throws ParseException //Constructor.
	{//Start Constructor.
		this.showName = showName;//Initializes the show name variable.
		this.date = convertDate(date);//Initializes the date variable.
		this.startTime = convertTime(startTime);//Initializes the start time variable.
		this.endTime = convertTime(endTime);//Initializes the end time variable.
	}//End Constructor.
	
	public DateTime() 
	{
		
	}
	
	public String showDuration(Date startTime, Date endTime) //This method converts duration to String.
	{
		long duration = endTime.getTime() - startTime.getTime();//Creates the duration variable, whose value is the end time minus the start time.
		DateFormat formatter = new SimpleDateFormat("HH:mm");//Creates the formatter variable, whose format is a standardized hour and minute format.
		return showDuration = formatter.format(duration);//This method returns the show duration variable, whose value is the duration variable.
	}
	
	public Date convertDate(String d) throws ParseException //This method converts String date to Date type.
	{
		Date tempStartDate = new SimpleDateFormat("MM/dd/yyyy").parse(d);
		//Creates a temporary start date variable, that converts the standardized month, date, year format to a Date Type Format.
		return date = tempStartDate;//This method returns date, whose value is the temporary start date variable.
	}

	public void setDate(Date d)//This method sets the date variable to type Date.
	{
		date = d;//Sets the date variable to type Date.
	}
	
	public Date getDate()//Gets the date variable as a Date type.
	{ 
		return date;//Returns the date variable.
	}
	
	public Date convertTime(String time) throws ParseException //This method sets the String time to a Date type for the time variable.
	{
		Date tempTime = new SimpleDateFormat("HH:mm").parse(time);
		//Creates a temporary time variable, that converts the standardized time format to a Date type time format.
		return this.time = tempTime;//Returns time, whose value is the temporary time variable.
	}
	
	public void setStartTime(Date start) //This method sets the start time variable as a Date type.
	{
		startTime = start;//Sets the start time variable as a Date type.
	}
	
	public Date getStartTime() //Gets the start time variable as a Date type.
	{
		return startTime;//Returns the value of the start time variable.
	}
	
	public void setEndTime(Date end) //Sets the end time variable as a Date type.
	{
		endTime = end;//Sets the end time variable as a Date type.
	}
		
	public Date getEndTime() //Gets the end time variable as a Date type.
	{
		return endTime;//Returns the value of the end time variable.
	}
		
	// (redundant)
	public void setShowName (String showName) //Sets the show name variable as a Date type.
	{
		this.showName = showName;//Sets the show name variable as a String type.
	}
		
	// (redundant)
	public String getShowName() //Gets the show name variable.
	{
		return showName;//Returns the value of the show name variable.
	}
}// End Class
