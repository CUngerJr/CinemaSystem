package CBS;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerCollection

{

	private ArrayList<Customer> customers;
	private File CustomerFile;

	
	public CustomerCollection()
	{
	setCustomers(new ArrayList<Customer>());	
	}

	// Add theater to Array to save to file
		public void addCustomer(Customer c) throws IOException {
			getCustomers().add(c);
			writeFile();
		}
		
		public void removeCustomer(Customer c) throws IOException {
			getCustomers().remove(c);
			writeFile();
		}
		
		// Save Theater File
		public void writeFile() throws IOException {
			CustomerFile = new File("CustomerList.txt");
			FileWriter writer = new FileWriter(CustomerFile, false);
			BufferedWriter bw = new BufferedWriter(writer);
			
			for (Customer c : getCustomers()) {
				bw.write(c.toString() + "\n");
			}
			bw.close();
			writer.close();
		}

		public ArrayList<Customer> getCustomers() {
			return customers;
		}

		public void setCustomers(ArrayList<Customer> customers) {
			this.customers = customers;
		}
} // End Class
