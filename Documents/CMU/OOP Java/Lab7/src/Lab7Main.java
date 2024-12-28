// Lab7
// Your name: Shlok Kalekar
// Your Andrew id: skalekar

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab7Main {
    File myfile = null;
    static Scanner fileScanner = null;
    static ArrayList<YearPop> list = null;

    public boolean openFile(String filename)  {
        myfile = new File(filename);
        boolean file_flag;
        try {
            fileScanner = new Scanner(myfile);
            file_flag = true;
        } catch (FileNotFoundException e) {
            file_flag = false;
        }
        return file_flag;
    }

    public YearPop makeData(String line)    {
        String[] data = line.split(",");
        return new YearPop(Integer.parseInt(data[0]), Double.parseDouble(data[1]));
    }

    public double findYear(int year)    {
        double value = -1;
        for (YearPop item: list) {
            if(item.getYear() == year)
                value = item.getPop();
        }
        return value;
    }

    public void createList(YearPop item)    {
        list.add(item);
    }

    public static int computeFibonacci(int first, int second, int n)    {
        int next_number;
        if ( n==0 ) {
            return first;
        }
        next_number = first + second;
        return computeFibonacci(second, next_number, n-1);
    }

    public static void main(String[] args) {
        int year_input, n;
        String next_flag;
        double population;
        boolean file_flag;
        list = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);
        Lab7Main lab = new Lab7Main();
        file_flag = lab.openFile("paPop.csv");
        if ( !file_flag )   {
            System.exit(0);
        }
        while (fileScanner.hasNextLine()) {
            lab.createList(lab.makeData(fileScanner.nextLine()));
        }
        do {
            System.out.println("Enter a year for its population: ");
            year_input = Integer.parseInt(inputScanner.nextLine());
            population = lab.findYear(year_input);
            System.out.println("Year: " + year_input + "\nPopulation Density: " + population);
            System.out.println("Check another year? (Y/N)");
            do {
                next_flag = inputScanner.nextLine();
                if (!next_flag.equalsIgnoreCase("Y") && !next_flag.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } while (!next_flag.equalsIgnoreCase("Y") && !next_flag.equalsIgnoreCase("N"));
        } while (!next_flag.equalsIgnoreCase("N"));


        System.out.println("Give the nth number of Fibonacci Sequence you want to know: ");
        n = Math.abs(Integer.parseInt(inputScanner.nextLine()));
        System.out.println("Fibonacci #" + n + " = " + computeFibonacci(0,1, n));
    }
}