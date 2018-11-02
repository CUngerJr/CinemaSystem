package CBS;

import java.util.ArrayList;

public class ReservedSeat {
	private int row, seat;
	private ShowEvent eventShow;
	
	
	public ReservedSeat(int row, int seat) {
		this.row = row;
		this.seat = seat;
		
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setSeat(int s) {
		seat = s;
	}
	
	public int getSeat() {
		return seat;
	}
	
	public String toString() {
		return row + " " + seat;
	}

} // End Class
