import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of citations. Provides functionality for reading, writing, displaying, and adding citations.
 */
public class CitationList {
    private static ArrayList<Citation> listOfCitations;
    private String title, authority;
    public static Scanner fileScanner;

    /**
     * Default constructor initializing title and authority.
     */
    public CitationList() {
        listOfCitations = new ArrayList<>();
        this.title = "Chief";
        this.authority = "Barrett";
    }

    /**
     * Constructor that accepts title and authority.
     *
     * @param title Title of the citation list.
     * @param authority Authority managing the citations.
     */
    public CitationList(String title, String authority) {
        listOfCitations = new ArrayList<>();
        this.title = title;
        this.authority = authority;
    }

    /**
     * Reads citation data from a CSV file and populates the citation list.
     *
     * @param filename Path of the CSV file.
     */
    public static void readCitationFile(String filename) {
        File file = new File(filename);
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Parse each line of the CSV and create citation objects.
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            Person person = new Person(data[4], data[5], data[6], data[7]);
            Citation citation = new Citation(Integer.parseInt(data[0]), data[1], data[2], data[3], person);
            listOfCitations.add(citation);
        }
    }

    /**
     * Writes all citations to a CSV file.
     *
     * @param filename Path of the output CSV file.
     */
    public static void writeCitationFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Citation citation : listOfCitations) {
                String csvLine = citation.toCSV() + ',' + citation.getPerson().toCSV();
                writer.write(csvLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays citations that match a given type of offense.
     *
     * @param typeOfOffense The type of offense to search for.
     */
    public void displayCitationType(String typeOfOffense) {
        ArrayList<Citation> foundCitations = new ArrayList<>();
        for (Citation item : listOfCitations) {
            if ((item.getTypeOfOffense()).equalsIgnoreCase(typeOfOffense)) {
                foundCitations.add(item);
            }
        }
        if (foundCitations.isEmpty())
            System.out.println("No such offense type present.");
        else {
            StringBuilder sb = new StringBuilder();
            for (Citation item : foundCitations) {
                sb.append(item).append("\n\n");
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * Displays a citation by its unique number.
     *
     * @param number The number of the citation.
     */
    public void displayCitation(int number) {
        Citation foundCitation = null;
        for (Citation item : listOfCitations) {
            if (item.getNumber() == number) {
                foundCitation = item;
                break;
            }
        }
        if (foundCitation == null)
            System.out.println("No citation is present.");
        else
            System.out.println(foundCitation + "\n\n");
    }

    /**
     * Displays a citation by the last name of the person.
     *
     * @param lastName The last name to search for.
     */
    public void displayCitation(String lastName) {
        Citation foundCitation = null;
        for (Citation item : listOfCitations) {
            if ((item.getPerson().getLastName()).equalsIgnoreCase(lastName)) {
                foundCitation = item;
                break;
            }
        }
        if (foundCitation == null)
            System.out.println("No citation is present.");
        else
            System.out.println(foundCitation + "\n\n");
    }

    /**
     * Creates and adds a new citation to the list.
     * It prompts the user for citation details and person information.
     */
    public void newCitation() {
        Citation citationNew, lastCitation = listOfCitations.get(listOfCitations.size() - 1);
        Person person;
        String newOffense, newDescription, newDate, newFirstName, newLastName, newAddress, newPhone;
        int lastIndex = lastCitation.getNumber();

        // Collect citation details from the user.
        System.out.println("Enter type of offense: ");
        newOffense = hw1Main.scanner.nextLine();
        System.out.println("Enter description: ");
        newDescription = hw1Main.scanner.nextLine();
        System.out.println("Enter date: ");
        newDate = hw1Main.scanner.nextLine();
        System.out.println("Enter first name: ");
        newFirstName = hw1Main.scanner.nextLine();
        System.out.println("Enter last name: ");
        newLastName = hw1Main.scanner.nextLine();
        System.out.println("Enter address: ");
        newAddress = hw1Main.scanner.nextLine();
        System.out.println("Enter phone number: ");
        newPhone = hw1Main.scanner.nextLine();

        // Create person and citation objects.
        person = new Person(newFirstName, newLastName, newAddress, newPhone);
        citationNew = new Citation((lastIndex + 1), newOffense, newDescription, newDate, person);
        listOfCitations.add(citationNew); // Add new citation to the list.
    }

    /**
     * Function which will convert CitationList to a CSV format in the future.
     *
     * @return empty string.
     */
    public String toCSV()   {
        return "";
    }

    // Getters and Setters.
    public ArrayList<Citation> getListOfCitations() {
        return listOfCitations;
    }

    public void setListOfCitations(ArrayList<Citation> listOfCitations) {
        CitationList.listOfCitations = listOfCitations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

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
