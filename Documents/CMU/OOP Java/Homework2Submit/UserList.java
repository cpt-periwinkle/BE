package UserPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Manages a list of users by reading user data from a file and storing the users in a list.
 * <p>
 * The `UserList` class provides functionality for reading user data from a file, creating `User` objects,
 * and storing them in an internal list. The users can then be displayed or processed further.
 * </p>
 */
public class UserList {
    private ArrayList<User> listOfUsers;

    /**
     * Default constructor that initializes an empty list of users.
     */
    public UserList() {
        listOfUsers = new ArrayList<>();
    }

    /**
     * Reads user data from a CSV file and populates the list of users.
     * <p>
     * The file is expected to have the following format for each line:
     * <code>id,userType,userName,other</code>, where:
     * <ul>
     *   <li><code>id</code> is the user's unique identifier (integer).</li>
     *   <li><code>userType</code> is the type of user (e.g., "ADMINISTRATOR", "OFFICER", "COURTOFFICIAL").</li>
     *   <li><code>userName</code> is the user's name (String).</li>
     *   <li><code>other</code> is additional information such as department, rank, or title.</li>
     * </ul>
     * </p>
     *
     * @param filename The path to the CSV file containing user data.
     */
    public void readUserFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            // Read each line and process the user data
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] userData = line.split(",", 4); // Expecting: id, userType, userName, other
                    if (userData.length == 4) {
                        int id = Integer.parseInt(userData[0].trim());
                        String userType = userData[1].trim();
                        String userName = userData[2].trim();
                        String other = userData[3].trim();

                        // Create user using the factory method
                        User user = UserFactory.createUser(id, userType, userName, other);
                        if (user != null) {
                            listOfUsers.add(user);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("User file not found: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of all users in the list.
     * <p>
     * This method iterates over all users in the list and returns their string representations
     * by calling each user's {@link User#toString()} method.
     * </p>
     *
     * @return A string containing the details of all users, each separated by two newlines.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : listOfUsers) {
            sb.append(user.toString()).append("\n\n");
        }
        return sb.toString();
    }

    /**
     * Gets the list of users.
     * <p>
     * This method returns the internal list of users for further processing or testing.
     * </p>
     *
     * @return The list of `User` objects.
     */
    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }
}
