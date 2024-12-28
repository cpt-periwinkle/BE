import java.util.Arrays;

/**
 * Menu class to display options to the user and handle menu selections.
 * <p>
 * The `Menu` class provides functionality to display a list of options for the user to choose from. It includes both a default constructor
 * with predefined menu options and an overload that allows for custom menu options. The class also handles reading the user's choice
 * and returning it as an integer.
 * </p>
 */
public class Menu {
    private static String[] menuChoice;

    /**
     * Default constructor that initializes menu options.
     * <p>
     * This constructor initializes the menu with 11 predefined options, such as displaying citations,
     * adding a new citation, sorting citations, and managing users.
     * </p>
     */
    public Menu() {
        menuChoice = new String[11];
        menuChoice[0] = "0. Quit";
        menuChoice[1] = "1. Display all the Citation data using CitationList.toString()";
        menuChoice[2] = "2. Display all Citations by chosen typeOfOffense";
        menuChoice[3] = "3. Search for a Citation by number";
        menuChoice[4] = "4. Search for a Citation by Person last name";
        menuChoice[5] = "5. Add a new Citation";
        menuChoice[6] = "6. Delete an existing Citation by number.";
        menuChoice[7] = "7. Sort Citations by number and display them (using toString)";
        menuChoice[8] = "8. Sort Citations by Person last name, first name and display them (using toString)";
        menuChoice[9] = "9. Sort Citations by type and display them (using toString)";
        menuChoice[10] = "10. Display Citations by User";
    }

    /**
     * Constructor that accepts custom menu options.
     * <p>
     * This constructor allows for custom menu options to be provided by passing an array of strings.
     * It replaces the default menu with the given custom options.
     * </p>
     *
     * @param menuChoice Array of custom menu options.
     */
    public Menu(String[] menuChoice) {
        Menu.menuChoice = menuChoice;
    }

    /**
     * Displays the menu and reads the user's choice.
     * <p>
     * This method constructs the menu display as a string and prompts the user to enter a choice.
     * If the user input is valid, it returns the corresponding integer choice; otherwise, it returns 0.
     * </p>
     *
     * @return The user's choice as an integer. Returns 0 if the input is invalid.
     */
    public static int displayMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter the number corresponding to the action:\n\n");
        for (String item : menuChoice) {
            sb.append(item).append("\n");
        }
        System.out.println(sb.toString());
        System.out.println("Enter your choice: ");
        try {
            return Integer.parseInt(hw2Main.scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0; // Return 0 if input is invalid.
        }
    }

    /**
     * Returns a string representation of the menu.
     * <p>
     * This method returns the full menu options as a string, each option on a new line.
     * </p>
     *
     * @return A string representing the full menu.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item : menuChoice) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
