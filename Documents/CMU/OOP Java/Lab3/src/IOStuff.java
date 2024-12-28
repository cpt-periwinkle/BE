// Lab3
// Your name: Shlok Kalekar
// Your Andrew id: skalekar

import java.util.Scanner;
import java.io.*;

// class IOSTuff
// Tests some issues of input and output

public class IOStuff {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String firstName, lastName;
        String id;
        String ageString, salaryString;
        int age;
        double salary;

        /* System.out.print("Enter first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();
        System.out.print("Enter id: ");
        id = scanner.nextLine();

        try {
            System.out.print("Enter age: ");
            ageString = scanner.nextLine();
            age = Integer.parseInt(ageString);
            System.out.print("Enter salary: ");
            salaryString = scanner.nextLine();
            salary = Double.parseDouble(salaryString);
        }   catch  (NumberFormatException e) {
            System.out.println(e);
            age = 0;
            salary = 0.0;
        } */

        /* System.out.println("First Name = " + firstName);
        System.out.println("Last Name = " + lastName);
        System.out.println("Age = " + age);
        System.out.println("ID = " + id);
        System.out.println("Salary = " + salary); */

        /* System.out.printf("%10s %20s %10s %10s %15s", "First Name", "Last Name", "ID", "Age", "Salary\n");
        System.out.printf("%10s %20s %10s %10d %14.2f\n", firstName, lastName, id, age, salary); */

        System.out.printf("%10s %20s %10s %10s %15s %15s", "First Name", "Last Name", "ID", "Age", "Salary", "Category\n");
        File file = new File("employee.csv");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            salary = 0.0d;
            String category;
            salary =  Double.parseDouble(data[4]);
            if (salary <  0 || salary > 150000)
                category = "Error";
            else if (salary >= 0 && salary < 35000)
                category = "Low";
            else if (salary >= 35000 && salary < 70000)
                category = "Medium";
            else
                category = "High";
            System.out.printf("%10s %20s %10s %10d %14.2f %15s\n", data[0], data[1], data[2], Integer.parseInt(data[3]), salary, category);
        }

        
    }
}
