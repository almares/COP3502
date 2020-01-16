import java.util.*;

public class fluffyswarm
{
	
	public static void main (String [] args)
	{
		
		Scanner scan = new Scanner (System.in); //Create the scanner "scan"
		
		
		System.out.println("Please enter the x coordinate of Drone 1: ");
		double d1x = scan.nextDouble(); //Request the x coordinate of Drone 1 from user and use scanner to assign it to variable "d1x"
		
		
		System.out.println("Please enter the y coordinate of Drone 1: ");
		double d1y = scan.nextDouble(); //Request the y coordinate of Drone 1 from user and use scanner to assign it to variable "d1y"
		
		
		System.out.println("Please enter the altitude of Drone 1: ");
		double d1a = scan.nextDouble(); //Request the altitude of Drone 1 from user and use scanner to assign it to variable "d1a"
		
		
		System.out.println("Please enter the x coordinate of Drone 2: ");
		double d2x = scan.nextDouble(); //Request the x coordinate of Drone 2 from user and use scanner to assign it to variable "d2x"
		
		
		System.out.println("Please enter the y coordinate of Drone 2: ");
		double d2y = scan.nextDouble(); //Request the y coordinate of Drone 2 from user and use scanner to assign it to variable "d2y"
		
		
		System.out.println("Please enter the altitude of Drone 2: ");
		double d2a = scan.nextDouble(); //Request the altitude of Drone 2 from user and use scanner to assign it to variable "d2a"
		
		
		double distance = ( Math.sqrt( (Math.pow(d2x-d1x, 2)) + (Math.pow(d2y-d1y, 2)) ) ); //Calculate the distance between two drones using Distance Formula and assign to variable "difference"
		
		
		double heightdiff = Math.abs(d1a - d2a); //Calculate the height difference between the drones and assign to variable "heightdiff"
		
		
		double hypotenuse = Math.sqrt((distance*distance) + (heightdiff*heightdiff)); //Calculate the distance in 3D space and assign it to variable "hypotenuse"
		
		
		System.out.print("The two drones are " +distance+ " feet apart in (x,y) coordinates."); //Print the distance
		
		
		if (distance < 1)
			System.out.println(" They need to move farther apart in (x,y)."); //If the calculated distance is less than 1, print that they need to move apart
		
		else if (distance > 500)
			System.out.println(" They need to move closer together in (x,y)."); //If the calculated distance is greater than 500, print that they need to move closer
		
		else System.out.println(" They do not need to move farther apart or closer in (x,y)."); //Otherwise, print that they don't need to move farther or closer
		
		
		System.out.print("The two drones are " +hypotenuse+ " feet apart in 3D space."); //Print the distance in 3D space
		
		
		if (hypotenuse > 500)
			System.out.print(" They need to move closer together."); //If the calculated 3D distance is greater than 500, print that they need to move closer
		
		else if (hypotenuse < 1)
			System.out.print(" They need to move farther apart."); //If the calculated 3D distance is greater than 500, print that they need to move farther
		
		else System.out.print(" They do not need to move farther apart or closer."); //Otherwise print that they don't need to move farther or closer
	
	}
	
}

