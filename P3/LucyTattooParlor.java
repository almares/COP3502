import java.util.*;
public class LucyTattooParlor {

	public static void main (String [] args) {
		
		Scanner scan = new Scanner (System.in);
		
		//Initialize 2D array based on command line arguments and populate it with null values
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		
		TattooCustomer [][] customerList = new TattooCustomer [x][y];
		for (int i = 0; i < customerList.length; i++)
			for (int j = 0; j < customerList[i].length; j++)
				customerList[i][j] = null;
		
		System.out.println("Hello, welcome to Lucy's Tattoo Parlor!");
		System.out.println();
		
		//Loop for asking user information
		while (true) {
			String name;
			String tattoo;
			int technician;
			int minutes;
			
			System.out.println("Please enter the name of the customer to add: ");
			name = scan.next();
			//If user enters "Print Waitlist," loop ends
			if (name.compareTo("Print Waitlist") == 0)
				break;
			System.out.println("Please enter the type of tattoo the customer wants: ");
			tattoo = scan.next();			
			System.out.println("Would you like to request a specific tattoo artist? If not, you will be added to the shortest waitlist. (Yes or No) ");
			if (scan.next().compareTo("Yes") == 0) {
				System.out.println("Which artist would you like? (0, 1, 2, etc.)");
				technician = scan.nextInt();
				System.out.println("How many minutes is the tattoo expected to take?");
				minutes = scan.nextInt();
				//Create the TattooCustomer object
				TattooCustomer c = new TattooCustomer (name, tattoo, minutes); 
				//If successfully added, inform the user. If not, print an error message
				if (addCustomer(customerList, c, technician))
					System.out.println("Thank you, the customer has been added to Artist " +technician+ "'s waitlist");
				else System.out.println("Error- Sorry, Artist " +technician+ "'s waitlist is full or their minutes are full");
			}
			else {
				System.out.println("How many minutes is the tattoo expected to take?");
				minutes = scan.nextInt();
				//Create the TattooCustomer object
				TattooCustomer c = new TattooCustomer (name, tattoo, minutes);
				//If successfully added, inform the user. If not, print an error message
				if (addCustomer(customerList, c))
						System.out.println("Thank you, the customer has been added to the shortest waitlist");
				else System.out.println("Sorry, all of our artist's waitlists are full or exceed 8 hours");
			}
			System.out.println();
		}
		//Print out the customer waitlist
		for (int i = 0; i < customerList.length; i++){
			System.out.println("Artist " +i+ ": ");
			for (int j = 0; j < customerList[i].length; j++){
				System.out.println("Customer: " +customerList[i][j].getName()+ "\nTattoo: " +customerList[i][j].getTattoo()+ "\nMinutes: " +customerList[i][j].getMinutes()+ "\nTotal Minutes for Artist: " +computeMinutesOfWork(customerList[i]));
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("Thank you, have a nice day!");
	}
	
	/**
	* Computes how many minutes of work the specified tattoo artist has.
	* @param The array of customers for one particular tattoo artist
	*/
	public static int computeMinutesOfWork (TattooCustomer [] a) {
		int minutes = 0;
		//Run through the array, ignoring "null" spots and adding together the minutes using getMinutes method
		for (int i = 0; i < a.length; i++) {
			if (a[i] == null)
				continue;
			else minutes += a[i].getMinutes();
		}
		return minutes;
	}
	
	/**
	* Adds customer to the waitlist for a specific artist.
	* If the artist is at capacity (in terms of number of customers or minutes)
	* Then the customer is not added and the method returns false
	* If the customer is successfully added the method returns true
	* @param
	*/
	public static boolean addCustomer (TattooCustomer [][] a, TattooCustomer c, int artistNum) {
		//Accounts for if the artist's waitlist is full
		if (a[artistNum][a[0].length-1] != null)
			return false;
		//Accounts for if the artist's total minutes are equal to or exceed 480
		if (computeMinutesOfWork(a[artistNum]) >= 480)
			return false;
		//Accounts for if the artist's total minutes plus the customer's minutes would exceed 480
		if (computeMinutesOfWork(a[artistNum]) + c.getMinutes() > 480)
			return false;
		else {
			//Runs through the artist's array of customers and adds TattooCustomer c to the first "null" spot found
			for (int i = 0; i < a[0].length; i++)
				if (a[artistNum][i] == null){
					a[artistNum][i] = c;
					return true;
				}
		}
		return false;
	}
	
	/**
	* Adds customer to the shortest waitlist in terms of minutes. If some artists have equal length waitlists
	* then the customer is added to the lowest numbered artist. If no artist has space then the method does not
	* add the customer, and returns false.
	* TODO - finish this javadoc
	* @return true if the customer was added to the waitlist, false otherwise (if all artists were full)
	*/
	public static boolean addCustomer (TattooCustomer [][] a, TattooCustomer c) {
		
		int counter = 0;
		//Determine if all of the spots in the 2D a array are full or if there are any empty spots available
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				if (a[i][j] != null)
					counter++;
		if (counter == 0)
			return false;
		
		//Determines which artist spot has the shortest wait time, ignoring artist's whose waitlists are full
		int shortestArtist = 0;
		for (int i = 0; i < a.length; i++){
			if (a[i][a[0].length-1] != null)
				continue;
			if (computeMinutesOfWork(a[i]) < computeMinutesOfWork(a[shortestArtist]))
				shortestArtist = i;
		}
		
		//Accounts for if the artists total minutes plus those of TattooCustomer c would exceed 480
		if (computeMinutesOfWork(a[shortestArtist]) + c.getMinutes() > 480)
			return false;
		else {
			//Runs through the artist's array and adds the customer to the first available "null" spot
			for (int i = 0; i < a[0].length; i++)
				if (a[shortestArtist][i] == null){
					a[shortestArtist][i] = c;
					return true;
				}
		}
		return false;
	}
	
}
