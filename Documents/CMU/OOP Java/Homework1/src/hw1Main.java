import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class that drives the program.
 * Handles menu input and various operations such as reading, displaying, and adding citations.
 */
public class hw1Main {
    // Global scanner object for user input.
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Main method that runs the menu and handles user choices.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int choice, searchNumber;
        String searchType, searchLastName, filePath = "citations.csv";
        CitationList citationList = new CitationList();
        Menu menu = new Menu();

        // Read citations from file.
        CitationList.readCitationFile(filePath);

        // Display the menu and handle user inputs in a loop.
        do {
            choice = Menu.displayMenu(); // Show menu and get user choice.
            if (choice == 0)
                break; // Exit loop if user selects 0.
            else if (choice == 1)
                System.out.println(citationList); // Display all citations.
            else if (choice == 2) {
                System.out.println("Enter type of offense: ");
                searchType = scanner.nextLine();
                citationList.displayCitationType(searchType); // Search citations by offense type.
            }
            else if (choice == 3) {
                System.out.println("Enter offense number: ");
                try {
                    searchNumber = Integer.parseInt(scanner.nextLine());
                    citationList.displayCitation(searchNumber); // Search citation by number.
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
            }
            else if (choice == 4) {
                System.out.println("Enter last name: ");
                searchLastName = scanner.nextLine();
                citationList.displayCitation(searchLastName); // Search citation by person's last name.
            }
            else if (choice == 5) {
                citationList.newCitation(); // Add a new citation.
            } else
                System.out.println("Invalid Input. Put in a valid number.");
        } while (true);

        // Write all citations back to file before exiting.
        CitationList.writeCitationFile("citations.csv");
    }
}
