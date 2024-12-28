import java.util.Arrays;

/**
 * Menu class to display options to the user and handle menu selections.
 */
public class Menu {
    private static String[] menuChoice;

    /**
     * Default constructor that initializes menu options.
     */
    public Menu() {
        menuChoice = new String[6];
        menuChoice[0] = "0. Quit";
        menuChoice[1] = "1. Display all the Citation data using CitationList.toString()";
        menuChoice[2] = "2. Display all Citations by chosen typeOfOffense";
        menuChoice[3] = "3. Search for a Citation by number";
        menuChoice[4] = "4. Search for a Citation by Person last name";
        menuChoice[5] = "5. Add a new Citation";
    }

    /**
     * Constructor that accepts custom menu options.
     *
     * @param menuChoice Array of custom menu options.
     */
    public Menu(String[] menuChoice) {
        Menu.menuChoice = menuChoice;
    }

    /**
     * Displays the menu and reads the user's choice.
     *
     * @return The user's choice as an integer.
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
            return Integer.parseInt(hw1Main.scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0; // Return 0 if input is invalid.
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item : menuChoice) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}