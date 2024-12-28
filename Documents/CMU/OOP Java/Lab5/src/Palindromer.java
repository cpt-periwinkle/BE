// Name: Shlok Kalekar
// Andrew id: skalekar

import java.util.Scanner;

/** Class Palindromer takes up to 10 phrases, checks to see 
 *  if each is a palindrome, displays the phrases and the count
 *  of how many were palindromes.
 */

public class Palindromer {
    /* Class data: */
	private static Scanner scanner = new Scanner(System.in);
	private String[] plist;
	private int pcount = 0;

    /** inputPalindromes:
     *  Parameters: int n, the number of phrases to be entered.
     *  Returns: a String[] array containing the phrases.
     *  Creates an array of Strings, then prompts the user
     *  to enter phrases.
     */
	public String[] inputPalindromes(int n) {
        // Your code goes here
		plist = new String[n];
		for(int i = 0; i < n; i++)	{
			String temp_phrase = scanner.nextLine();
			plist[i] = this.cleanString(temp_phrase);
		}
		return plist;
	}
	
    /** displayPalindromes:
     *  Parameters: none
     *  Returns: void
     *  For each phrase in plist[], call cleanString( ) to create
     *  an all-upper-case, letters only version of the phrase;
     *  check if that String is a palindrome - if so, say so;
     *  if not, say that. Increment pcount with the number of
     *  palindromes found.
     */
	public void displayPalindromes() {
        // Your code goes here
		for (String phrase: plist)	{
			boolean print_palindrome = true;
			print_palindrome = this.isPalindrome(phrase);
			if (print_palindrome)	{
				pcount++;
				System.out.println("\""+phrase+"\" is a palindrome");
			}
			else
				System.out.println("\""+phrase+"\" is not a palindrome");
		}
	}
	
    /** isPalindrome:
     *  Parameter: String s to test for palindromity
     *  Returns: boolean
     *  If s is a palindrome, return true, otherwise
     *  return false
     */
	public boolean isPalindrome(String s) {
        // Your code goes here
		boolean palindrome_flag = false;
		StringBuilder rev_string = new StringBuilder();
		for (int i = (s.length() - 1); i >= 0; i--)	{
			char temp_letter = s.charAt(i);
			rev_string.append(temp_letter);
		}
		String rev_phrase = rev_string.toString();
		if ( s.equals(rev_phrase) )
			palindrome_flag = true;
		return palindrome_flag;
	}

    /** cleanString:
     *  Parameter: String s to be cleaned
     *  Returns: String, upper case letters only
     *  Removes non-letters from s, converts to upper case.
     */
	public String cleanString(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c: s.toCharArray()) {
			if (Character.isLetter(c)) {
				sb.append(Character.toUpperCase(c));
			}
		}
		String newString = sb.toString();
		return newString;
	}
	
    /** getPcount:
     *  Parameters: none
     *  Returns: int, the value of pcount
     *  Standard getter function
     */
	public int getPcount() {
        // Your code goes here
		return pcount;
	}

    /** DO NOT CHANGE THIS METHOD */
	public static void main(String[] args) {
		Palindromer palindromer = new Palindromer();
		System.out.println("Palindromer App\n");
		System.out.print("Enter the number of palindromes to store (10 max): ");
		int count = Integer.parseInt(scanner.nextLine());
		if (count > 0 && count <= 10) {		//was taking only 9 phrases max originally, changed to <=
			palindromer.plist = palindromer.inputPalindromes(count);
			System.out.println();
			palindromer.displayPalindromes();
			System.out.println("\nNumber of palindromes: " + palindromer.getPcount());
		} else {
			System.out.println("Sorry, cannot do that number");
		}
	}
	
}
