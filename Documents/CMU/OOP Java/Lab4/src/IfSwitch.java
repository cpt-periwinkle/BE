// Lab4
// Your name: Shlok Kalekar
// Your Andrew id: skalekar


import java.util.Scanner;

public class IfSwitch {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int feet, inches;
        int height;
        String gender;

        System.out.println("Height Comparison");
        System.out.println("You'll be asked for your height as feet and inches");
        System.out.print("Enter the feet part of your height: ");
        feet = scanner.nextInt();
        System.out.print("Enter the inches part of your height: ");
        inches = scanner.nextInt();
        System.out.println("You entered " + feet + " ft " + inches + " inches");

        height = ((feet * 12) + inches);
        System.out.println("Your height in inches is: " + height);

        System.out.println("Enter your gender (M for Male, F for Female): ");
        gender = scanner.next().toUpperCase();

        if ( !gender.equals("M") )
            gender = "F";
        System.out.println("Gender set to " + gender);

        if ( height < 21 || height > 107 ) {
            System.out.println("Error in inputted height");
            if ( gender.equals("M") )
                height = 69;
            else 
                height = 64;
            System.out.println("Set to average height: " + height);
        }
        else if (  height < 64 && gender.equals("F") )
            System.out.println("Your shorter than average");
        else if ( height >= 64 && gender.equals("F") )
            System.out.println("Your taller than average");
        else if ( height < 69 && gender.equals("M") )
            System.out.println("Your shorter than average");
        else if ( height >= 69 && gender.equals("M") )
            System.out.println("Your taller than average");
        else
            System.out.println("Invalid Input");

        // switch (gender) {
        //     case "M":
        //         if ( height < 69)
        //             System.out.println("You are shorter than average");
        //         else
        //             System.out.println("You are taller than average");
        //         break;
        //     case "F":
        //         if ( height < 64)
        //             System.out.println("You are shorter than average");
        //         else
        //             System.out.println("You are taller than average");
        //         break;
        //     default:
        //         System.out.println("Error in inputted data");
        // }

        System.out.println("Enter your test score for grade: ");
        char grade_gpa;
        int test_score = scanner.nextInt();
        if ( test_score <= 100 && test_score >= 90 )
            grade_gpa = 'A';
        else if ( test_score < 90 && test_score >= 80 )
            grade_gpa = 'B';
        else if ( test_score < 80 && test_score >= 70 )
            grade_gpa = 'C';
        else if ( test_score < 70 && test_score >= 60 )
            grade_gpa  = 'D';
        else if ( test_score < 60 && test_score  >= 0 )
            grade_gpa = 'F';
        else {
            grade_gpa = '!'; 
            System.out.println("Errored values");
            System.exit(0);
        }

        System.out.print("GPA: ");
        switch (grade_gpa)  {
            case 'A':
                System.out.println("4.0");
                break;
            case 'B':
                System.out.println("3.0");
                break;
            case 'C':
                System.out.println("2.0");
                break;
            case 'D':
                System.out.println("1.0");
                break;
            case 'F':
                System.out.println("0.0");
                break;
            default:
                System.out.println("Errored Value");
        }
    }
}
