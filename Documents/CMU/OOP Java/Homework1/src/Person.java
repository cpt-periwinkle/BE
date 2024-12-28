public class Person {
    private String firstName, lastName, address, phoneNumber;

    /**
     * Default constructor initializing fields with default values.
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
     * @param firstName First name of the person.
     * @param lastName Last name of the person.
     * @param address Address of the person.
     * @param phoneNumber Phone number of the person.
     */
    public Person(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Converts the person's details to a CSV format string.
     *
     * @return CSV formatted string representing the person.
     */
    public String toCSV() {
        return this.getFirstName() + "," +
                this.getLastName() + "," +
                this.getAddress() + "," +
                this.getPhoneNumber();
    }

    // Getters and Setters.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person: " + firstName + " " + lastName + " " + address + " " + phoneNumber;
    }
}