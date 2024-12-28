package CitationPackage;

public class Citation implements Comparable<Citation> {

    private String typeOfOffense, description, date;
    private int number, userID;
    private Person person;

    /**
     * Default constructor that initializes the citation fields with default values.
     * The default values are:
     * <ul>
     * <li>typeOfOffense: "Crime"</li>
     * <li>description: " - "</li>
     * <li>date: " - "</li>
     * </ul>
     */
    public Citation() {
        this.typeOfOffense = "Crime";
        this.description = " - ";
        this.date = " - ";
    }

    /**
     * Constructor to initialize a citation with specific details.
     *
     * @param number        The unique identifier for the citation.
     * @param typeOfOffense The type of offense that led to the citation.
     * @param description   A brief description of the offense.
     * @param date          The date on which the citation was issued.
     * @param person        The person associated with the citation (violator).
     * @param userID        The ID of the user issuing the citation.
     */
    public Citation(int number, String typeOfOffense, String description, String date, Person person, int userID) {
        this.number = number;
        this.typeOfOffense = typeOfOffense;
        this.description = description;
        this.date = date;
        this.person = person;
        this.userID = userID;
    }

    /**
     * Compares this citation to another citation based on the citation number.
     * Used to allow sorting of citations.
     *
     * @param citation The citation to compare this citation to.
     * @return A negative integer, zero, or a positive integer as this citation's number
     *         is less than, equal to, or greater than the specified citation's number.
     */
    @Override
    public int compareTo(Citation citation) {
        return Integer.compare(this.number, citation.number);
    }

    /**
     * Converts the citation object into a CSV formatted string.
     * The CSV string includes the citation's number, type of offense, description, date,
     * associated person's data, and the user ID.
     *
     * @return A CSV formatted string representing this citation.
     */
    public String toCSV() {
        return this.getNumber() + "," +
                this.getTypeOfOffense() + "," +
                this.getDescription() + "," +
                this.getDate() + "," +
                this.getPerson().toCSV() + "," +
                this.getUserID();
    }

    // Getter and Setter Methods:

    /**
     * Returns the type of offense for this citation.
     *
     * @return The type of offense.
     */
    public String getTypeOfOffense() {
        return typeOfOffense;
    }

    /**
     * Sets the type of offense for this citation.
     *
     * @param typeOfOffense The type of offense to set.
     */
    public void setTypeOfOffense(String typeOfOffense) {
        this.typeOfOffense = typeOfOffense;
    }

    /**
     * Returns the description of the offense for this citation.
     *
     * @return The offense description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the offense for this citation.
     *
     * @param description The description of the offense.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the date when the citation was issued.
     *
     * @return The citation issue date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date when the citation was issued.
     *
     * @param date The citation issue date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the unique number of the citation.
     *
     * @return The citation number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the unique number for this citation.
     *
     * @param number The citation number to set.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the person associated with the citation (violator).
     *
     * @return The person object associated with this citation.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person associated with the citation.
     *
     * @param person The person object to associate with this citation.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Returns the ID of the user who issued the citation.
     *
     * @return The user ID of the person who issued the citation.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID for the person issuing the citation.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Returns a string representation of the citation, including its number, type of offense,
     * description, issue date, violator information, and user ID.
     *
     * @return A string representation of this citation.
     */
    @Override
    public String toString() {
        return "Citation #" + number + "\nType of Offense: " + typeOfOffense + "\nDescription: "
                + description + "\nDate: " + date + "\n" + person.toString() + "\nUser ID: " + userID;
    }
}