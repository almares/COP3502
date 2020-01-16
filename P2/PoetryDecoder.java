//Do not edit this imports
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/**
 * STUDENTS FILL IN PROPER DOCUMENTATION HERE
 * @author Aaron Mares
 * COP 3502 Section Number: 149B 
 *
 */

public class PoetryDecoder {

	/**
	 * This main method asks the user to enter input. Then, it calls
	 * the decode method on that input. 
	 * STUDENTS: DO NOT EDIT this main method. Do all of your work
	 * in the methods below it. Note: This is the only method that has System.out output. 
	 * No other method prints to the console! (You may of course print to console for your own
	 * debugging purposes, but remove that debug code before you submit your program.) 
	 * @param args This main method does not take command line args.
	 */
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//This prompt to the user is intentionally vague to avoid
		//incrimating those who use this program. 
		System.out.println("Please enter your input: ");
		String hex = sc.nextLine();

		//STUDENTS: Your decode method must return a fully formatted String,
		//which will be output here. 
		System.out.print(decode(hex));
		
		sc.close();
		
	}

	/**
	 * The decode method 
	 * STUDENTS: This decode method is where all your work happens. 
	 * To keep it clean and simple, it calls other methods.
	 * @param hex The string of hex digits that is to be decoded.
	 * @return A String representing an entire decoded poem of English words. 
	 * (The returned string includes line breaks and spacing.) 
	 */
	
	public static String decode(String hex) {
		
		//STUDENTS: First, translate the hex string into English characters. 
		//Use the toEnglishCharacters() method to do that. 
		String engchar = toEnglishLetters(hex);
		
		//STUDENTS: Now that you have the String of English characters, search 
		//it to extract all four-letter words and store those in one array. 
		//Search it again to extract all five-letter words and store in a different array.
		//Search it one final time to extract all six-letter words and store in a different array.
		//To do this, call your findWordsOfLength() method three times with the appropriate parameters.
		
		//create String arrays for each group of word lengths and populate them using findWordsOfLength method
	
		
		String[] fourletterwords = findWordsOfLength(engchar, 4);
		String [] fiveletterwords   = findWordsOfLength(engchar, 5);
		String [] sixletterwords  = findWordsOfLength(engchar, 6);

		//Create the final formatted poem by sending the three arrays of word lengths to the formatPoem method
		String poem = formatPoem(fourletterwords, fiveletterwords, sixletterwords);
		
		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile. 
		return poem;
		
	}


	/**
	 * STUDENTS: You must write this method. Fill in its body. But do not change its header! 
	 * 
	 * This method takes a String of hex characters and, chunking them two at a time,
	 * finds a corresponding English letter according to the ASCII table. If the String of 
	 * hex characters is of odd length, the final hex character is IGNORED. 
	 * Note that the pairs of hex characters may not precisely correspond to ASCII letter values--
	 * that would be too easy to track! Instead, you must translate the hex pair into an ASCII English
	 * letter (no punctuation or digits) using character arithmetic. More details on that: 
	 * 
	 * To translate any pair of hex digits to English letters, here is the procedure:
	 * Take every two hex digits and convert it to its decimal form. If this number is inside the ASCII range 
	 * for an English letter, either lower case or upper case, then you're done converting to a letter.
	 * If it is not inside the ASCII range for letters, mod the value by 26 and map it to an ASCII letter based on 
	 *  the result. mod 0='a'; mod 1 = 'b'; mod 2='c' and so on. You do not need
	 *  a big switch statement or if's. Just use character arithmetic. 
	 *  
	 * @param String hex: String of hex characters. If the String is of odd length, the final hex
	 * character is ignored and not translated.
	 * 
	 * @return String: the String of English letters. Each letter came from a pair of hex
	 * digits in the original input String. 
	 */
	public static String toEnglishLetters(String hex){
		
		//Determine if the hex length is odd; if so, remove the last character to make even
		if ((hex.length() % 2) != 0)
			hex = hex.substring(0, hex.length()-1);
		
		String engchar = "";
		for (int i = 0; i <= hex.length()-1; i += 2){	
			char var;
			//Go through the hex digits in pairs
			int decimal = Integer.parseInt(hex.substring(i, (i+2)), 16);
			//Determine if valid lowercase letter in ASCII
			if (decimal >= 97 && decimal <= 122)
				var = (char)decimal;
			//If not, mod the values to fit the appropriate letter
			else{
				decimal %= 26;
				decimal += 97;
				var = (char)decimal;
			}
			//Add each translated pair together
			engchar += var;
		}
		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile.
		return engchar;
		
	}


	/**
	 * STUDENTS: Complete this method. Do NOT change its header. 
	 * This method searches an input String of English letters, and returns all words of 
	 * the specified length that occur in the String. A "sliding window" will be considered, so 
	 * a given character could occur in more than one word that is found. This is desirable.
	 * 
	 * The size of the array you return depends on the size of the string passed and the word size to be found. 
	 * The array must be able to hold the maximum number of words of the size wordSize that could potentially be found in
	 * the input string. You do not have to first check how many real words there are. Instead, just compute how many words
	 * there *could* be. For example, a letter string of length 15 could have 13 possible three-letter words (think of
	 * a sliding window). As another example, a letter string of size 100 could have 97 possible four-letter words. Make
	 * a simple arithmetic expression to decide the size of your array at the beginning of this method.
	 * 
	 * DO NOT make the array hold a random or just very large number of words: so, do not set the array to a large number 
	 * like 500. You will lose points if you do this. 
	 * Also, you must fill up the array in the order that you encounter valid words of that length. So the first word 
	 * found goes to index 0, second word goes to index 1, etc. The array does not need to be completely filled up, 
	 * it just need to store all the words found. Thus the remaining elements of the array must be null.
	 * 
	 * For example, if you created an array of size 97 and you only found five valid words to store in it, you would have
	 * index 0, 1, 2, 3, and 4 filled up with valid words, and indices 5 through 96 would be null. 
	 * 
	 * @param String letters: A string of English letters
	 * @param String wordSize: The size of words to be extracted from the input String
	 * @return String[]: Contains a word in each element. Elements can be null if no words are found.  
	 */
	public static String[] findWordsOfLength(String letters, int wordSize) {

		//If there are not at least six letters, return "null"
		if (letters.length() < 4)
			return null;
		
		//Create an array with length based on the wordSize and length of "letters"
		String [] wordarray = new String [letters.length() - wordSize + 1];
		//Create counter for the index of the created array
		int index = 0;	
		
		for (int j = 0; j <= letters.length()-wordSize; j++){
			//Run through each grouping of Strings of the wordSize
			if (j + wordSize > letters.length()-1){
				//Call the isWord method to determine validity of word
				if (isWord(letters.substring(j))){
					wordarray[index] = letters.substring(j);
					index++;
				}
			}
			//Accounts for last grouping of characters in "letters"
			else if (isWord(letters.substring(j, j + wordSize))){
				wordarray[index] = letters.substring(j, j + wordSize);
				index++;
			}	
		}
		
		//Fill in the remaining array slots with "null" if the number of spots filled does not equal the size of the array
		if (index != (wordarray.length - 1)){
			int reps = wordarray.length - index + 1;
			for(int k = reps; k <= wordarray.length-1; k++){
				wordarray[k] = null;
			}
		}	
				
		
		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile.	
		return wordarray;

	}

	/**
	 * This method formats a poem according to Poetry Movement specifications. 
	 * @param Takes three arrays of Strings of shortest, medium, and longest lengths. 
	 * @return Returns a String with line breaks and tabs as needed for poetic formatting. 
	 */
	public static String formatPoem(String[] shortest, String[] medium, String[] longest){
		
		//If all of the arrays are null (meaning that there were less than six letters and not enough digits in the hexadecimal) return nothing
		if (shortest == null && medium == null && longest == null)
			return "";
		
		String poem = "";
		
		int shortestwords = 0;
		int mediumwords = 0;
		int longestwords = 0;
		
		for (int i = 0; i < shortest.length; i++){
			if (shortest[i] != null)
				shortestwords++;
		}
		for (int j = 0; j < medium.length; j++){
			if (medium[j] != null)
				mediumwords++;
		}
		for (int k = 0; k < longest.length; k++){
			if (longest[k] != null)
				longestwords++;
		}
		
		if (longestwords <= mediumwords && mediumwords <= shortestwords){
			for(int l = 0; l < longestwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == longestwords-1 && longestwords >= mediumwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = longestwords; m < mediumwords; m++){
				if (medium[m] != null)
					poem += medium[m]+ " ";
				if (shortest[m] != null)
					poem += shortest[m];
				if (m == mediumwords-1 && mediumwords >= shortestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = mediumwords; n < shortestwords; n++){
				if (shortest[n] != null)
					poem += shortest[n];
				if (n == shortestwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}
		}
		
		else if (longestwords <= shortestwords && shortestwords <= mediumwords){
			for(int l = 0; l < longestwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == longestwords-1 && longestwords >= shortestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = longestwords; m < shortestwords; m++){
				if (medium[m] != null)
					poem += medium[m]+ " ";
				if (shortest[m] != null)
					poem += shortest[m];
				if (m == shortestwords-1 && shortestwords >= mediumwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = shortestwords; n < mediumwords; n++){
				if (medium[n] != null)
					poem += medium[n];
				if (n == mediumwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}
		}	
		
		else if (mediumwords <= longestwords && longestwords <= shortestwords){
			for(int l = 0; l < mediumwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == mediumwords-1 && mediumwords >= longestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = mediumwords; m < longestwords; m++){
				if (longest[m] != null)
					poem += longest[m]+ " ";
				if (shortest[m] != null)
					poem += shortest[m];
				if (m == longestwords-1 && longestwords >= shortestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = longestwords; n < shortestwords; n++){
				if (shortest[n] != null)
					poem += shortest[n];
				if (n == shortestwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}
		}
		
		else if (mediumwords <= shortestwords && shortestwords <= longestwords){
			for(int l = 0; l < mediumwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == mediumwords-1 && mediumwords >= shortestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = mediumwords; m < shortestwords; m++){
				if (longest[m] != null)
					poem += longest[m]+ " ";
				if (shortest[m] != null)
					poem += shortest[m];
				if (m == shortestwords-1 && shortestwords >= longestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = shortestwords; n < longestwords; n++){
				if (longest[n] != null)
					poem += longest[n];
				if (n == longestwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}
		}
		
		else if (shortestwords <= mediumwords && mediumwords <= longestwords){
			for(int l = 0; l < shortestwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == shortestwords-1 && shortestwords >= mediumwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = shortestwords; m < mediumwords; m++){
				if (longest[m] != null)
					poem += longest[m]+ " ";
				if (medium[m] != null)
					poem += medium[m];
				if (m == mediumwords-1 && mediumwords >= longestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = mediumwords; n < longestwords; n++){
				if (longest[n] != null)
					poem += longest[n];
				if (n == longestwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}
		}
		
		else if (shortestwords <= longestwords && longestwords <= mediumwords){
			for(int l = 0; l < shortestwords; l++){
				if (longest[l] != null)
					poem += longest[l]+ " ";
				if (medium[l] != null)
					poem += medium[l]+ " ";
				if (shortest[l] != null)
					poem += shortest[l];
				if (l == shortestwords-1 && shortestwords >= longestwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= l; z++)
						poem += "\t";
				}
			}
			for(int m = shortestwords; m < longestwords; m++){
				if (longest[m] != null)
					poem += longest[m]+ " ";
				if (medium[m] != null)
					poem += medium[m];
				if (m == longestwords-1 && longestwords >= mediumwords)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= m; z++)
						poem += "\t";
				}
			}
			for (int n = longestwords; n < mediumwords; n++){
				if (medium[n] != null)
					poem += medium[n];
				if (n == mediumwords-1)
					continue;
				else{
					poem += "\n";
					for (int z = 0; z <= n; z++)
						poem += "\t";
				}
			}		
		}
		//Create six large if statements accounting for the possible differences in size of array
		
		
		
		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile.
		return poem;
		
	}



	/* *******************************************************************************
	STUDENTS -- BELOW THIS POINT ARE METHODS PROVIDED FOR YOU. DO NOT EDIT THEM. 
	USE THEM AS INDICATED IN THEIR DESCRIPTIONS. 
	 ********************************************************************************/

	/**
	 * This method checks whether the given String occurs in a dictionary of English.
	 * STUDENTS: DO NOT MODIFY THIS METHOD IN ANY WAY. IT IS PROVIDED FOR YOU. 
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param possWord The word to be checked.
	 * @return boolean Returns true if the word given is an English word, false otherwise.
	 */
	private static boolean isWord(String possWord) {
		boolean isWord = true;
		try {
			//connect to the URL. 
			String s = getUrl(possWord);
			Document d = Jsoup.connect(s).timeout(6000).get();
			Elements tdTags = d.select("h3");

			// Loop over all tdTags, in this case: the h3 tag 
			for( Element element : tdTags ){
				String check = element.toString();

				//Wordnet has a special h3 tag that appears only if the word is not in the dictionary
				//We search for this tag. If it is found, then the word searched is not in the dictionary
				if(check.equals("<h3>Your search did not return any results.</h3>") ){
					isWord = false;
				}
			}
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		return isWord;
	}

	/**
	 * This is a helper method that the teaching staff code uses. 
	 * STUDENTS: DO NOT EDIT THIS METHOD.
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param String: search 
	 * @return A string containing the URL for the wordnet search.
	 */
	private static String getUrl(String search) {
		//Standard URL for wordnet to search
		String url = "http://wordnetweb.princeton.edu/perl/webwn?s=";
		String newURL = null;
		try {
			//Get new page from word wordnet and get its location
			Document doc = Jsoup.connect(url + search).timeout(6000).get();
			newURL = doc.location().toString();
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		//Return the string of the new URL. 
		return (newURL);
	}

}

