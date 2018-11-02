package CBS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//DONE.
//COMMENTED.

public class Customer // This class has the getters and setters for the customer info that the
						// Customer Collection and CBS classes will use.
{// Start Customer class.
	private String firstName; // Field for first name.
	private String lastName; // Field for last name.
	private String phoneNum; // Field for phone number.
	private ArrayList<Customer> customerList;

	public Customer(String first, String last, String phone) // Constructor.
	{// Start Constructor.
		this.firstName = first; // Initializes the first name variable.
		this.lastName = last; // Initializes the last name variable.
		this.phoneNum = phone; // Initializes the phone number variable.
		customerList = new ArrayList<>();
	}// End Constructor.

	public String getFirstName() // Getter for first name.
	{
		return firstName;// Returns the value of the first name variable when the method is called.
	}

	public String getLastName() // Getter for last name.
	{
		return lastName;// Returns the value of the last name variable when the method is called.
	}

	public String getPhoneNum() // Getter for phone number.
	{
		return phoneNum;// Returns the value of the phone number variable when the method is called.
	}

	public void setFirstName(String fName) // Sets the first name.
	{
		this.firstName = fName;// Sets the value of the first name variable to be a String type.
	}

	public void setLastName(String lName) // Sets the last name.
	{
		this.lastName = lName;// Sets the value of the last name variable to be a String type.
	}

	public void setPhoneNum(String phone) // Sets the phone number.
	{
		this.phoneNum = phone;// Sets the value of the phone number variable to be a String type.
	}

	public void addCustomer(Customer c) {
		customerList.add(c);
		writeToFile();
	}

	public String toString() // Overrides the toString method. This prints the customer info when the method
								// is called by CBS.
	{
		return firstName + ";" + lastName + ";" + phoneNum;// Returns the first name, last name, and phone number when
															// the method is called.
	}// The delimiter ";" is used so we know when the value of one variable ends and
		// when the next one starts.

	private void writeToFile() // Method that write the customer info to a text file.
	{// Start method.
		try // Start try.
		{// Start try block.
			BufferedWriter bw = new BufferedWriter(new FileWriter("CustomerList.txt", true)); // Adds to a customer list
																								// text file.
			for (Customer c : customerList) // For loop for each customer object that is in customer list array list.
			{// Start for block.
				bw.write(c.toString()); // Writes the customer info on a single line in the file.
				bw.newLine(); // Tells the file to start at the next line.
			} // End for block.
			bw.close(); // Closes the buffered reader input.
		} // End try block.
		catch (IOException e) // Catches an exception if the file write does not work.
		{// Start catch block.
			System.out.println("Writer did not work."); // Error message that prints if the try block did not work.
		} // End catch block.
	}// End method. */
}// End Customer class.
