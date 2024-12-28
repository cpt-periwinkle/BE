import CitationPackage.Citation;
import CitationPackage.CitationList;
import CitationPackage.Person;
import UserPackage.User;
import UserPackage.UserList;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The main class that drives the program.
 * <p>
 * This class handles the menu-driven interface for interacting with users, citations, and configuration files.
 * It provides operations such as adding citations, searching for citations by various criteria,
 * and interacting with users based on their ID. It also manages reading and writing citation data to/from files.
 * </p>
 */
public class hw2Main {

    // Global scanner object for user input.
    public static Scanner scanner = new Scanner(System.in);

    private static HashMap<String, String> configData;
    private static CitationList citationList = new CitationList();
    private static UserList userList = new UserList();

    private static final String CONFIG_PATH = "configuration.csv";

    /**
     * Prompts the user for citation details and adds a new citation to the citation list.
     * <p>
     * The method requests details about the offense type, description, date, violator's information (name, address, phone),
     * and the user ID of the person issuing the citation. A new `Citation` object is created and added to the citation list.
     * </p>
     */
    public static void newCitation() {
        System.out.println("Enter type of offense: ");
        String offenseType = scanner.nextLine();

        System.out.println("Enter description of offense: ");
        String description = scanner.nextLine();

        System.out.println("Enter date of offense: ");
        String date = scanner.nextLine();

        System.out.println("Enter violator's first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter violator's last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter violator's address: ");
        String address = scanner.nextLine();

        System.out.println("Enter violator's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Enter User ID (for the person issuing the citation): ");
        int userID = Integer.parseInt(scanner.nextLine());

        User issuingUser = findUserById(userID);

        if (issuingUser == null) {
            System.out.println("User with ID " + userID + " not found!");
            return;
        }

        Person violator = new Person(firstName, lastName, address, phone);

        // Generate a citation number based on the last citation number
        int citationNumber = citationList.getListOfCitations().size() + 1;

        Citation newCitation = new Citation(citationNumber, offenseType, description, date, violator, userID);

        citationList.getListOfCitations().add(newCitation);

        System.out.println("New citation added successfully!");
    }

    /**
     * Reads the configuration file and loads key-value pairs into the configData map.
     * <p>
     * The configuration file is expected to contain key-value pairs separated by commas.
     * The data is stored in a HashMap for easy retrieval of configuration values.
     * </p>
     *
     * @return A HashMap containing the configuration data with keys and their corresponding values.
     */
    private static HashMap<String, String> readConfigFile() {
        configData = new HashMap<>();
        try (Scanner configScanner = new Scanner(new File(CONFIG_PATH))) {
            while (configScanner.hasNextLine()) {
                String line = configScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] keyValue = line.split(",");
                    if (keyValue.length == 2) {
                        configData.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
        }
        return configData;
    }

    /**
     * Helper method to find a user by ID.
     * <p>
     * This method searches through the list of users and returns the user with the matching ID.
     * If no user is found, it returns null.
     * </p>
     *
     * @param userID The ID of the user to search for.
     * @return The User object if found, or null if no user matches the given ID.
     */
    private static User findUserById(int userID) {
        for (User user : userList.getListOfUsers()) {
            if (user.getId() == userID) {
                return user;
            }
        }
        return null;
    }

    /**
     * The main method that runs the menu-driven interface.
     * <p>
     * The method presents the user with a menu of options and processes the user's choice.
     * Operations include displaying citations, searching citations by offense type, citation number, or violator's last name,
     * adding new citations, deleting citations, and displaying citations sorted by various criteria.
     * </p>
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int choice, searchNumber;
        String searchType, searchLastName;

        HashMap<String, String> configMap = readConfigFile();

        Menu menu = new Menu();

        // Read citations from the input file specified in the configuration file.
        CitationList.readCitationFile(configMap.get("input file"));

        do {
            choice = Menu.displayMenu();

            if (choice == 0) {
                break; // Exit loop if user selects 0.
            } else if (choice == 1) {
                System.out.println(citationList); // Display all citations.
            } else if (choice == 2) {
                System.out.println("Enter type of offense: ");
                searchType = scanner.nextLine();
                System.out.println(citationList.displayCitationType(searchType)); // Search citations by offense type.
            } else if (choice == 3) {
                System.out.println("Enter offense number: ");
                try {
                    searchNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println(citationList.displayCitation(searchNumber)); // Search citation by number.
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
            } else if (choice == 4) {
                System.out.println("Enter last name: ");
                searchLastName = scanner.nextLine();
                System.out.println(citationList.displayCitation(searchLastName)); // Search citation by person's last name.
            } else if (choice == 5) {
                newCitation(); // Add a new citation.
            } else if (choice == 6) {
                String response;
                System.out.println("Enter citation number of the citation you wish to delete: ");
                try {
                    searchNumber = Integer.parseInt(scanner.nextLine());
                    response = citationList.delete(searchNumber); // Delete citation by number.
                } catch (NumberFormatException e) {
                    response = "Invalid Input";
                }
                System.out.println(response);
            } else if (choice == 7) {
                citationList.sortByNumber();
                System.out.println(citationList);
            } else if (choice == 8) {
                citationList.sortByName();
                System.out.println(citationList);
            } else if (choice == 9) {
                citationList.sortByType();
                System.out.println(citationList);
            } else if (choice == 10) {
                userList.readUserFile(configMap.get("user file"));
                System.out.println(userList.toString());
                System.out.println("Enter User ID: ");
                int userID = Integer.parseInt(scanner.nextLine());
                System.out.println(citationList.findByUser(userID));
            } else {
                System.out.println("Invalid Input. Put in a valid number.");
            }
        } while (true);

        // Write all citations back to the file before exiting.
        CitationList.writeCitationFile("citations.csv");
    }
}
