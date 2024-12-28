package CitationPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of citations. Provides functionality for reading, writing, displaying,
 * adding, deleting, and sorting citations based on different criteria.
 */
public class CitationList {

    // List of citations
    public static ArrayList<Citation> listOfCitations;
    private String title, authority;
    public static Scanner fileScanner;

    /**
     * Default constructor initializing the citation list with a default title and authority.
     * The default values are:
     * <ul>
     * <li>title: "Chief"</li>
     * <li>authority: "Barrett"</li>
     * </ul>
     */
    public CitationList() {
        listOfCitations = new ArrayList<>();
        this.title = "Chief";
        this.authority = "Barrett";
    }

    /**
     * Adds a citation to the list of citations.
     *
     * @param citation The citation to add to the list.
     */
    public void add(Citation citation) {
        listOfCitations.add(citation);
    }

    /**
     * Constructor that accepts a custom title and authority.
     *
     * @param title     The title of the citation list.
     * @param authority The authority managing the citations.
     */
    public CitationList(String title, String authority) {
        listOfCitations = new ArrayList<>();
        this.title = title;
        this.authority = authority;
    }

    /**
     * Reads citation data from a CSV file and populates the citation list with the parsed data.
     * Each line in the CSV represents a citation and is parsed into a Citation object.
     *
     * @param filename The path of the CSV file to read.
     */
    public static void readCitationFile(String filename) {
        File file = new File(filename);
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to read the file: " + e.getMessage());
        }

        // Parse each line of the CSV and create citation objects.
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            Person person = new Person(data[4], data[5], data[6], data[7]);
            Citation citation = new Citation(Integer.parseInt(data[0]), data[1], data[2], data[3], person, Integer.parseInt(data[8]));
            listOfCitations.add(citation);
        }
    }

    /**
     * Writes all citations to a CSV file.
     *
     * @param filename The path of the output CSV file to write.
     */
    public static void writeCitationFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Citation citation : listOfCitations) {
                String csvLine = citation.toCSV();
                writer.write(csvLine);
                writer.write("\n"); // Manually adding a newline after each line
            }
        } catch (IOException e) {
            System.err.println("Unable to write the file: " + e.getMessage());
        }
    }

    /**
     * Displays all citations that match the specified type of offense.
     *
     * @param typeOfOffense The type of offense to search for in the citation list.
     * @return A string representation of the citations with the matching offense type, or
     *         "None found" if no matching citations are found.
     */
    public String displayCitationType(String typeOfOffense) {
        String returnString;
        ArrayList<Citation> foundCitations = new ArrayList<>();
        for (Citation item : listOfCitations) {
            if ((item.getTypeOfOffense()).equalsIgnoreCase(typeOfOffense)) {
                foundCitations.add(item);
            }
        }
        if (foundCitations.isEmpty())
            returnString = "None found";
        else {
            StringBuilder sb = new StringBuilder();
            for (Citation item : foundCitations) {
                sb.append(item).append("\n\n");
            }
            returnString = sb.toString();
        }
        return returnString;
    }

    /**
     * Displays a citation by its unique citation number.
     *
     * @param number The citation number to search for.
     * @return A string representation of the citation, or "None found" if no matching citation
     *         is found.
     */
    public String displayCitation(int number) {
        String returnString;
        Citation foundCitation = null;
        for (Citation item : listOfCitations) {
            if (item.getNumber() == number) {
                foundCitation = item;
                break;
            }
        }
        if (foundCitation == null)
            returnString = "None found";
        else
            returnString = foundCitation + "\n\n";
        return returnString;
    }

    /**
     * Displays a citation by the last name of the associated person.
     *
     * @param lastName The last name to search for in the citation list.
     * @return A string representation of the citation, or "None found" if no matching citation
     *         is found.
     */
    public String displayCitation(String lastName) {
        String returnString;
        Citation foundCitation = null;
        for (Citation item : listOfCitations) {
            if ((item.getPerson().getLastName()).equalsIgnoreCase(lastName)) {
                foundCitation = item;
                break;
            }
        }
        if (foundCitation == null)
            returnString = "None found";
        else
            returnString = foundCitation + "\n\n";
        return returnString;
    }

    /**
     * Deletes a citation from the list by its citation number.
     *
     * @param number The citation number to delete.
     * @return A message indicating whether the citation was successfully deleted or not.
     */
    public String delete(int number) {
        String returnString;
        boolean deleteFlag = false;
        for (int i = 0; i < listOfCitations.size(); i++) {
            if (listOfCitations.get(i).getNumber() == number) {
                listOfCitations.remove(i);
                deleteFlag = true;
                break;
            }
        }
        if (deleteFlag)
            returnString = "Deleted";
        else
            returnString = "None found";
        return returnString;
    }

    /**
     * Sorts the citations by their unique citation number.
     */
    public void sortByNumber() {
        listOfCitations.sort(null);
    }

    /**
     * Sorts the citations by the last name and first name of the associated person.
     */
    public void sortByName() {
        listOfCitations.sort((c1, c2) -> {
            int lastNameComparison = c1.getPerson().getLastName().compareToIgnoreCase(c2.getPerson().getLastName());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }
            return c1.getPerson().getFirstName().compareToIgnoreCase(c2.getPerson().getFirstName());
        });
    }

    /**
     * Sorts the citations by the type of offense.
     */
    public void sortByType() {
        listOfCitations.sort(new TypeSorter());
    }

    /**
     * Finds citations by the user ID of the person who issued them.
     *
     * @param userID The user ID to search for.
     * @return A string representation of the citations with the matching user ID, or "None found"
     *         if no matching citations are found.
     */
    public String findByUser(int userID) {
        StringBuilder sb = new StringBuilder();
        for (Citation citation : listOfCitations) {
            if (citation.getUserID() == userID) {
                sb.append(citation.toString()).append("\n\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "None found";
    }

    /**
     * Converts the CitationList object to a CSV format string. Currently returns an empty string.
     *
     * @return An empty string.
     */
    public String toCSV() {
        return "";
    }

    // Getter and Setter Methods:

    /**
     * Returns the list of citations.
     *
     * @return The list of citations.
     */
    public ArrayList<Citation> getListOfCitations() {
        return listOfCitations;
    }

    /**
     * Sets the list of citations.
     *
     * @param listOfCitations The list of citations to set.
     */
    public void setListOfCitations(ArrayList<Citation> listOfCitations) {
        CitationList.listOfCitations = listOfCitations;
    }

    /**
     * Returns the title of the citation list.
     *
     * @return The title of the citation list.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the citation list.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the authority managing the citations.
     *
     * @return The authority managing the citations.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the authority managing the citations.
     *
     * @param authority The authority to set.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Returns a string representation of the citation list, including the title, authority,
     * and all citations in the list.
     *
     * @return A string representation of the citation list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" ").append(authority).append("\n\n");
        for (Citation item : listOfCitations) {
            sb.append(item).append("\n\n");
        }
        return sb.toString();
    }
}