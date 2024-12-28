public class Citation {
    private String typeOfOffense, description, date;
    private int number;
    private Person person;

    /**
     * Default constructor initializing fields with default values.
     */
    public Citation() {
        this.typeOfOffense = "Crime";
        this.description = " - ";
        this.date = " - ";
    }

    /**
     * Constructor that initializes all fields of a citation.
     *
     * @param number Citation number.
     * @param typeOfOffense Type of offense.
     * @param description Description of the offense.
     * @param date Date of the citation.
     * @param person Person associated with the citation.
     */
    public Citation(int number, String typeOfOffense, String description, String date, Person person) {
        this.number = number;
        this.typeOfOffense = typeOfOffense;
        this.description = description;
        this.date = date;
        this.person = person;
    }

    /**
     * Converts the citation to a CSV format string.
     *
     * @return CSV formatted string representing the citation.
     */
    public String toCSV() {
        return this.getNumber() + "," +
                this.getTypeOfOffense() + "," +
                this.getDescription() + "," +
                this.getDate();
    }

    // Getters and Setters.
    public String getTypeOfOffense() {
        return typeOfOffense;
    }

    public void setTypeOfOffense(String typeOfOffense) {
        this.typeOfOffense = typeOfOffense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Citation #" + number + "\nType of Offense: " + typeOfOffense + "\nDescription: " + description + "\nDate: " + date + "\n" + person.toString();
    }
}