package CBS;

public class Seat 
{
    private int seatNum;
    private boolean seatStatus;

    

        
    public Seat(int s)
    {
        this.seatNum = s;
       
        
    }
    


	public int getSeatNum()
    {       
    	return seatNum;
    }
    
    
    public void setSeatStatus(boolean b)
    {              
    	seatStatus = b;        
    }
       

    public boolean getSeatStatus()
    {
        return seatStatus;
    }
    
    public String toString()
    {
        return String.format(seatNum + " " + seatStatus);
    }

    
    
    

    

    
    

    

}//End Class.

