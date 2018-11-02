package CBS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Reservation extends Show {
	File reservationFile;
	private ArrayList<ReservedSeat> reserveSeatArr;
	private ArrayList<Reservation> reservArr;
	private Customer customer;
	private ShowEvent showEve;
	private DateTime dateConverter;
	

	public Reservation(Customer customer, ShowEvent event, ArrayList<ReservedSeat> rSeats) {
		reserveSeatArr = new ArrayList<>(rSeats.size());
		reservArr = new ArrayList<Reservation>();
		this.customer = customer;
		showEve = event;
			for (int j = 0; j < rSeats.size(); j++) {
				reserveSeatArr.add(rSeats.get(j));
			}
	}
	
	public void addReservation(Reservation res) throws IOException {
		reservArr.add(res);
		writeFile();
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservArr;
	}

	public void setReservations(ArrayList<Reservation> res) {
		reservArr = res;
	}
	
	public ArrayList<ReservedSeat> getReservedSeats() {
		return reserveSeatArr;
	}
	
	public String toString() {
		return customer.getFirstName() + "; " + customer.getLastName() + "; " + customer.getPhoneNum() + 
				"; " + showEve.getTitle() + "; "  + showEve.getTheater().getTheaterName() + "; " + showEve.getDate() + "; " +// showEve.getTotalSeats() + 
				/*"; " + showEve.getAvailableSeats() + "; " + showEve.getReservedSeats() + */"; " + getReservedSeats();		
	}
	
	public void display() {
		String resInfo = "Name: " + customer.getFirstName() +  " " + customer.getLastName() + "\nPhone: " + customer.getPhoneNum() + 
						"\nShow Name: " + showEve.getTitle() + "\nTheater: " + showEve.getTheater().getTheaterName() + "\nDate of Show: " + showEve.getDate();
		
		System.out.print(resInfo + " ");
			for (int i = 0; i < reserveSeatArr.size(); i ++) {
				System.out.print("\nReserved Seats: Row " + reserveSeatArr.get(i).getRow() + " Seat " + reserveSeatArr.get(i).getSeat());
			}
		
	}
	
	// Save Reservation File
			public void writeFile() throws IOException {
				reservationFile = new File("Reservations.txt");
				FileWriter writer = new FileWriter(reservationFile, true);
				BufferedWriter bw = new BufferedWriter(writer);
				
				for (Reservation r : reservArr) {
					bw.write(r.toString() + "\n");
				}
				bw.close();
				writer.close();
			}

			/*public void readFile() throws ParseException, IOException {
			Scanner reader = new Scanner(new FileInputStream("Reservations.txt"));
			ArrayList<ReservedSeat> resvSeats = new ArrayList<ReservedSeat>();
			ArrayList<ShowEvent> shwEvnt = new ArrayList<ShowEvent>();
			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				Scanner sc = new Scanner(line);
				sc.useDelimiter("\\; \\, \\[ \\]");
				
				// Get Customer Object
				String fName = sc.next();
				String lName = sc.next();
				String phone = sc.next();
				Customer c = new Customer(fName, lName, phone);
				
				// Get ShowEvent Object
				String showTitle = sc.next();
				String theaterName = sc.next();
				String date = sc.next();
				Date d = dateConverter.convertDate(date);
				
				ShowEvent e = new ShowEvent(showTitle, theaterName, d);
				
				// Get reservedSeats
				int row = sc.nextInt();
				int seat = sc.nextInt();
				
				ReservedSeat rs = new ReservedSeat(row, seat);
				
				reservArr.add(c, e, rs);
				
				
			}
			reader.close();
		}*/
	









} // End Class
