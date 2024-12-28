package UserPackage;

/**
 * Represents an Officer, a subclass of User, with an associated rank.
 * <p>
 * The Officer class extends the `User` class and adds a `rank` field, which
 * represents the officer's rank (e.g., Regular, Sergeant, Captain, Inspector).
 * It includes methods for accessing and modifying the rank as well as a method
 * to return a string representation of the officer's details.
 * </p>
 */
public class Officer extends User {

    private Rank rank;

    /**
     * Default constructor initializing the Officer with default values.
     * <p>
     * The default constructor sets the officer's ID to 0, the username to "John Doe",
     * and the rank to "REGULAR".
     * </p>
     */
    public Officer() {
        this(0, "John Doe", Rank.REGULAR);
    }

    /**
     * Constructor to initialize an Officer with specified values.
     *
     * @param id The unique identifier for the officer.
     * @param userName The username of the officer.
     * @param rank The rank of the officer (e.g., Regular, Sergeant, Captain, Inspector).
     */
    public Officer(int id, String userName, Rank rank) {
        super(id, userName);
        this.rank = rank;
    }

    /**
     * Gets the rank of the officer.
     *
     * @return The rank of the officer.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the rank of the officer.
     *
     * @param rank The rank to be set for the officer (e.g., Regular, Sergeant, Captain, Inspector).
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     * Enum representing the possible ranks of an officer.
     */
    public enum Rank {
        REGULAR,      // Regular officer rank
        SERGEANT,     // Sergeant officer rank
        CAPTAIN,      // Captain officer rank
        INSPECTOR;    // Inspector officer rank
    }

    /**
     * Returns a string representation of the Officer, including the details
     * from the parent `User` class and the rank information.
     *
     * @return A string representation of the Officer's details.
     */
    @Override
    public String toString() {
        return super.toString() + "\nRank: " + rank;
    }
}
