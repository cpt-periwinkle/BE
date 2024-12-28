import CitationPackage.Citation;
import CitationPackage.CitationList;
import CitationPackage.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHomeworkp3 {
    static CitationList citationList = new CitationList("Test", "Test");
    static Person person = new Person("Bob", "Doe", "Home", "1");
    static Citation citation = new Citation(1,"Parking","Bad","1", person, 1);

    @BeforeAll
    public static void setup() {
        citationList.readCitationFile("citations.csv");
    }
    @Test
    public void testAddCitation() {
        citationList.add(citation);
        ArrayList<Citation> c = citationList.getListOfCitations();
        assertEquals(5, c.size());
        assertEquals("Parking", c.get(4).getTypeOfOffense());
        assertEquals("Bad", c.get(4).getDescription());
    }

    @Test
    public void testDeleteCitation() {
        // Empty CitationPackage.CitationList
        CitationList c = new CitationList("Test", "Test2");
        // Get citationList's ArrayList of CitationPackage.Citation objects
        ArrayList<Citation> c2 = citationList.getListOfCitations();
        int size = c2.size();
        // Get c's ArrayList of CitationPackage.Citation objects
        ArrayList<Citation> c3 = c.getListOfCitations();
        // Copy citationList's Citations into c
        // This is why you don't break encapsulation
        for (Citation cit: c2) {
            c3.add(cit);
        }
        // Add a new CitationPackage.Citation and then delete it
        Citation dummy = new Citation(6,"Parking", "x", "1", person, 7);
        String s = dummy.toString();
        c3.add(dummy);
        c.delete(6);
        assertEquals(size, c.getListOfCitations().size());
        assertEquals("None found", citationList.displayCitation(6));
    }

    @Test
    public void testSorts() {
        citationList.sortByName();
        ArrayList<Citation> c = citationList.getListOfCitations();
        assertEquals("Doe", c.get(0).getPerson().getLastName());
        assertEquals("Smith", c.get(3).getPerson().getLastName());
        citationList.sortByNumber();
        c = citationList.getListOfCitations();
        assertEquals("Smith", c.get(0).getPerson().getLastName());
        assertEquals("Long", c.get(3).getPerson().getLastName());
        citationList.sortByType();
        c = citationList.getListOfCitations();
        assertEquals("Farkus", c.get(0).getPerson().getLastName());
        assertEquals("Smith", c.get(3).getPerson().getLastName());
    }
}