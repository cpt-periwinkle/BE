package UserPackage;

/**
 * Represents a Court Official, a subclass of User.
 * <p>
 * This class extends the `User` class and adds a `title` field to represent
 * the title of the court official (e.g., Judge). It includes methods for accessing
 * and modifying the title as well as a method to return a string representation
 * of the court official's details.
 * </p>
 */
public class CourtOfficial extends User {

    private String title;

    /**
     * Default constructor initializing the CourtOfficial with default values.
     * <p>
     * The default constructor sets the court official's ID to 0, the username to "John Doe",
     * and the title to "Temp Judge".
     * </p>
     */
    public CourtOfficial() {
        this(0, "John Doe", "Temp Judge");
    }

    /**
     * Constructor to initialize a CourtOfficial with specified values.
     *
     * @param id The unique identifier for the court official.
     * @param userName The username of the court official.
     * @param title The title of the court official (e.g., "Judge").
     */
    public CourtOfficial(int id, String userName, String title) {
        super(id, userName);
        this.title = title;
    }

    /**
     * Gets the title of the court official.
     *
     * @return The title of the court official.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the court official.
     *
     * @param title The title to be set for the court official (e.g., "Judge").
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a string representation of the CourtOfficial, which includes the details
     * from the parent `User` class and the title information.
     *
     * @return A string representation of the CourtOfficial's details.
     */
    @Override
    public String toString() {
        // Use the toString() method from the User class
        return super.toString() + "\n" +
                "Title: " + title;
    }
}
