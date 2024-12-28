import java.util.Scanner;

public class Lab13Driver {
    private static Scanner scanner = new Scanner(System.in);

    public static void problem1()   {
        System.out.println("Enter a day of the week: ");
        String entry = scanner.nextLine();
        if (!BadWeekday.WEEKDAYS.contains(entry)) {
            System.out.println("Bad day entered");
        } else {
            System.out.println(entry + ": Nice job!");
        }
    }

    public static void problem2() throws BadWeekday {
        System.out.println("Enter a day of the week: ");
        String entry = scanner.nextLine();
        if (!BadWeekday.WEEKDAYS.contains(entry)) {
            throw new BadWeekday("Bad day entered");
        } else {
            System.out.println("Nice job!");
        }
    }

    public static void problem3() {
        try {
            problem2();
        } catch (BadWeekday e)  {
            System.out.println(e.getMessage());
        }
    }

    public static void problem4() throws BadWeekday {
        problem2();
    }

    public static void problem5() throws BadWeekday{
        try {
            problem2();
        } catch (BadWeekday e) {
            System.out.println("Caught " + e.getMessage());
            throw e;
        }
    }

    public static void problem6() throws BadWeekday {
        problem2();
    }

    public static void main(String[] args) throws BadWeekday {
        System.out.println("Problem 1");
        problem1();

        System.out.println("\nProblem 2");
        try {
            problem2();
        } catch (BadWeekday e)  {
            System.out.println(e.getMessage());
        }

        System.out.println("\nProblem 3");
        problem3();

        System.out.println("\nProblem 4");
        try {
            problem4();
        } catch (BadWeekday e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nProblem 5");
        try {
            problem5();
        } catch (BadWeekday e) {
            System.out.println("Caught exception again: " + e.getMessage());
        }

        System.out.println("\nProblem 6");
        problem6();
        /*
        In Problem 6, the biggest difference is that the main() method itself has the 'throws BadWeekday' declaration,
        meaning that it also doesn't handle the exception. If an invalid day is entered and BadWeekday is thrown,
        the exception wouldn't be caught within the program. Instead, it will pass outside the main() method and
        cause the program to terminate with an unhandled exception. This is different from previous problems where
        exceptions were caught and handled within the program using try-catch blocks.
        */
    }
}
