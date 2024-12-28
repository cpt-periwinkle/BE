import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Lab18 {
    public static ArrayList<Customer> readCustomers(String filename)
            throws IOException {

        // Array list of Customer objects
        ArrayList<Customer> list = new ArrayList<>();
        // Create a new File object
        File file = new File(filename);
        // If the file does not exist, throw a FileNotFound exception
        if (!file.exists())
            throw new FileNotFoundException(filename + " not found");
        // Create a new Scanner on the file object
        Scanner fileScanner  = new Scanner(file);
        // While fileScanner has a next line
        while(fileScanner.hasNextLine())    {
            int rating;
            double balance;
            // Read the next line and split it
            String line = fileScanner.nextLine();
            String[] fields = line.split(",");

            String firstName = fields[0];
            String lastName = fields[1];

            // Convert the rating to an int; throw NumberFormatException if bad
            try {
                rating = Integer.parseInt(fields[2].trim());
            } catch (NumberFormatException e)   {
                System.out.println("Invalid format for rating, an integer should be used!");
                rating = 0;
            }
            // Convert the balance to a double; throw NumberFormatException if bad
            try {
                balance = Double.parseDouble(fields[3].trim());
            } catch (NumberFormatException e)   {
                System.out.println("Invalid format for balance, a double should be used!");
                balance = 0.0d;
            }
            // Create a new customer object, add it to list
            Customer customer = new Customer(firstName, lastName, rating, balance);
            list.add(customer);
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Customer> clist = null;
        // Problem 3
        // Call readCustomers with the data file as a parameter
        try {
            clist = readCustomers("customers.csv");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Print the array list
        System.out.println("Original list");
        for (Customer customer : clist) {
            System.out.println(customer);
        }

        // Problem #4
        // Create PriorityQueue queue1
        PriorityQueue<Customer> queue1 = new PriorityQueue<>();
        try {
            for (Customer customer : clist) {
                queue1.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error while adding to queue1: " + e.getMessage());
        }

        // Problem #5
        // Create PriorityQueue queue2
        PriorityQueue<Customer> queue2 = new PriorityQueue<>(Comparator.comparingDouble(Customer::getBalance));
        try {
            for (Customer customer : clist) {
                queue2.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error while adding to queue2: " + e.getMessage());
        }

        // Problem #6
        // Remove things one at a time from queue1 and print them
        System.out.println("Queue1 processing");
        while (true) {
            try {
                Customer customer = queue1.element();
                System.out.println(customer);
                queue1.remove();
            } catch (Exception e) {
                System.out.println("Done");
                break;
            }
        }

        // Problem #7
        // Remove things one at a time from queue2 and print them
        System.out.println("Queue2 processing");
        while (true) {
            try {
                Customer customer = queue2.element();
                System.out.println(customer);
                queue2.remove();
            } catch (Exception e) {
                System.out.println("Done");
                break;
            }
        }

        // Problem #8
        // Try this on your own

        // Problem #9
        problem9();
    }

    public static void problem9() {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> patterns = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("testdata.txt"))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("testdata.txt not found.");
            return;
        }

        System.out.println("Contents of testdata.txt:");
        for (String line : lines) {
            System.out.println(line);
        }

        // Given patterns-
        patterns.add("\\d"); // any digit
        patterns.add("[a-zA-Z]"); // any letter, either case
        patterns.add("\\b\\d+\\b"); // an integer
        patterns.add("^a"); // starts with "a"
        patterns.add("s$"); // ends with "s"
        patterns.add("\\("); // contains a left parenthesis
        patterns.add("a.*e|e.*a"); // contains "a" and "e" in either order
        patterns.add("aeiou"); // contains vowels a, e, i, o, u in sequence
        patterns.add("a.*e.*i.*o.*u"); // contains vowels a, e, i, o, u in order, not necessarily together

        for (String patternString : patterns) {
            System.out.println("\nPattern: " + patternString);
            Pattern pattern = Pattern.compile(patternString);

            for (String line : lines) {
                if (pattern.matcher(line).find()) {
                    System.out.println("Matched: " + line);
                }
            }
        }
    }
}
