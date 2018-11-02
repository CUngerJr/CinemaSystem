package CBS;

import java.util.ArrayList;


public class Row

{
    
    private ArrayList<Seat> seats;
    private int rowNumber;


    public Row(int rowNumber)
    
    {
        this.rowNumber = rowNumber;
        seats = new ArrayList<Seat>();
        
    }
    
    public int getRowNumber()
    {
        return rowNumber;
    }
    
    public void setRowNumber(int rowNumber)
    {
        this.rowNumber = rowNumber;
    }

    public int getRowLength()
    {
        return seats.size();
    }

    public String toString(Row row)
    {
        return String.format("Row Number: " + row.getRowNumber());
    }


}

