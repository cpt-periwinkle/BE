package UserPackage;

/**
 * Represents an Administrator, a subclass of User.
 * <p>
 * This class extends the `User` class and adds a `department` field to represent
 * the department to which the administrator belongs. It includes methods for accessing
 * and modifying the department as well as a method to return a string representation
 * of the administrator's details.
 * </p>
 */
public class Administrator extends User {

    private String department;

    /**
     * Default constructor initializing the Administrator with default values.
     * <p>
     * The default constructor sets the administrator's ID to 0, the username to "John Doe",
     * and the department to "Temp".
     * </p>
     */
    public Administrator() {
        this(0, "John Doe", "Temp");
    }

    /**
     * Constructor to initialize an Administrator with specified values.
     *
     * @param id The unique identifier for the administrator.
     * @param userName The username of the administrator.
     * @param department The department to which the administrator belongs.
     */
    public Administrator(int id, String userName, String department) {
        super(id, userName);
        this.department = department;
    }

    /**
     * Gets the department of the administrator.
     *
     * @return The department of the administrator.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the administrator.
     *
     * @param department The department to be set for the administrator.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Returns a string representation of the Administrator, which includes the details
     * from the parent `User` class and the department information.
     *
     * @return A string representation of the Administrator's details.
     */
    @Override
    public String toString() {
        // Call the toString() from the User class
        return super.toString() + "\n" +
                "Department: " + department;
    }
}
