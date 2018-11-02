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
import java.util.Scanner;

public class TheaterCollection

{
	
	protected ArrayList<Theater> theaters;
	private File theaterFile;

	
	public TheaterCollection() throws IOException
	{
	theaters = new ArrayList<Theater>();
	readFile();
	}

	// Add theater to Array to save to file
		public void addTheater(Theater t) throws IOException {
			theaters.add(t);
			writeFile();
		}
		
		public void removeTheater(Theater t) throws IOException {
			theaters.remove(t);
			writeFile();
		}
		
		// Save Theater File
		public void writeFile() throws IOException {
			theaterFile = new File("Theater.txt");
			FileWriter writer = new FileWriter(theaterFile, false);
			BufferedWriter bw = new BufferedWriter(writer);
			
			for (Theater t : theaters) {
				bw.write(t.toString() + "\n");
			}
			bw.close();
			writer.close();
		}
		
		public void readFile() throws FileNotFoundException
		{
	
			Scanner reader = new Scanner(new FileInputStream("Theater.txt"));
			
			//if (reader.nextLine() == null) {
			
			//}
			//else {
				while(reader.hasNextLine())
				{
				
					String line = reader.nextLine();
				
					Scanner sc = new Scanner(line);
					sc.useDelimiter("\\;");
				
					String theaterName = sc.next();
					int id = sc.nextInt();
					int rows = sc.nextInt();
					int columns = sc.nextInt();
               
					theaters.add(new Theater(theaterName, id, rows, columns));
				} 
			//}

		}

		public ArrayList<Theater> getTheaters() {
			return theaters;
		}

		public void setTheaters(ArrayList<Theater> theaters) {
			this.theaters = theaters;
		}
		
		

		
}// End Class
