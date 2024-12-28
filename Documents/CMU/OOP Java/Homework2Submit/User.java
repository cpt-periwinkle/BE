package UserPackage;

/**
 * Represents a user with an ID and a username.
 * <p>
 * The `User` class serves as a base class for different types of users (such as Officer, Administrator, etc.).
 * It provides basic functionality to store and manage a user's ID and username, with getter and setter methods.
 * </p>
 */
public abstract class User {
    int id;
    String userName;

    /**
     * Default constructor initializing the user with default values.
     * <p>
     * The default constructor sets the user's ID to 0 and the username to "John Doe".
     * </p>
     */
    public User() {
        this(0, "John Doe");
    }

    /**
     * Constructor to initialize a user with specified ID and username.
     *
     * @param id The unique identifier for the user.
     * @param userName The username of the user.
     */
    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID to be set for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The username to be set for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns a string representation of the user's details, including the ID and username.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User ID: " + id + "\n" +
                "User Name: " + userName;
    }
}
