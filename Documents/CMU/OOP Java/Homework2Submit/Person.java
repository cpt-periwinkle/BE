package CitationPackage;

/**
 * Represents a person with personal details such as first name, last name, address, and phone number.
 * Provides functionality to initialize, convert to CSV format, and display information.
 */
public class Person {
    private String firstName, lastName, address, phoneNumber;

    /**
     * Default constructor that initializes the person's fields with default values:
     * <ul>
     * <li>firstName: "First"</li>
     * <li>lastName: "Last"</li>
     * <li>address: "Address"</li>
     * <li>phoneNumber: "911"</li>
     * </ul>
     */
    public Person() {
        this.firstName = "First";
        this.lastName = "Last";
        this.address = "Address";
        this.phoneNumber = "911";
    }

    /**
     * Constructor that initializes all fields of a person.
     *
     * @param firstName  The first name of the person.
     * @param lastName   The last name of the person.
     * @param address    The address of the person.
     * @param phoneNumber The phone number of the person.
     */
    public Person(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Converts the person's details to a CSV format string. The CSV format includes:
     * <ul>
     * <li>First name</li>
     * <li>Last name</li>
     * <li>Address</li>
     * <li>Phone number</li>
     * </ul>
     *
     * @return A CSV formatted string representing the person's details.
     */
    public String toCSV() {
        return this.getFirstName() + "," +
                this.getLastName() + "," +
                this.getAddress() + "," +
                this.getPhoneNumber();
    }

    // Getters and Setters

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the address of the person.
     *
     * @return The address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the person.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the person, including their first name, last name,
     * address, and phone number.
     *
     * @return A string representing the person's details.
     */
    @Override
    public String toString() {
        return "Person: " + firstName + " " + lastName + " " + address + " " + phoneNumber;
    }
}
