// Test cases for Homework 2
// *** DO NOT CHANGE THIS FILE ***

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import CitationPackage.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestHomeworkp1 {
    static Person person = new Person("Bob", "Doe", "Home", "1");
    static Citation citation = new Citation(1,"Parking","Bad","1", person, 1);
    static CitationList citationList;

    @Test
    public void testPerson() {
        assertEquals("Bob", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("Home", person.getAddress());
        assertEquals("1", person.getPhoneNumber());
        assertEquals("Bob,Doe,Home,1", person.toCSV());
    }

    @Test
    public void testCitation() {
        assertEquals(1, citation.getNumber());
        assertEquals("Parking", citation.getTypeOfOffense());
        assertEquals("Bad", citation.getDescription());
        assertEquals("1", citation.getDate());
        assertEquals(person, citation.getPerson());
        assertEquals(1, citation.getUserID());
    }

    @Test
    public void testCitationListRead() {
        citationList = new CitationList("Boss", "Me");
        assertEquals("Me", citationList.getAuthority());
        assertEquals("Boss", citationList.getTitle());
        citationList.readCitationFile("citations.csv");
        ArrayList<Citation> c = citationList.getListOfCitations();
        assertEquals("Speeding", c.get(0).getTypeOfOffense());
        assertEquals("Forbes Avenue", c.get(1).getDescription());
        assertEquals("12-Sept-2023", c.get(2).getDate());
        assertEquals("Long", c.get(3).getPerson().getLastName());
        assertEquals(2, c.get(3).getUserID());
    }

    @Test
    public void testCitationListWrite() {
        citationList = new CitationList("Me", "Boss");
        citationList.readCitationFile("citations.csv");
        citationList.writeCitationFile("temp.csv");
        File file = new File("temp.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {}
        String s = scanner.nextLine();
        assertEquals("1,Speeding,50mph,23-Aug-2023,Mary,Smith,123 Main Street,412-555-5555,2", s);
        s = scanner.nextLine();
        String[] s2 = s.split(",");
        assertEquals("Jaywalking", s2[1]);
        scanner.close();
    }

}
