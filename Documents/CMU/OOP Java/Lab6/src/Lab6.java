// Name: Shlok Kalekar
// Andrew ID: skalekar

import java.util.Scanner;

public class Lab6 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String new_flag;
        boolean trip_flag;
        double normal_temp;
        Sensor sensor = new Sensor(0.0, 120.0, 68.0, 1.0, "kitchen", "temperature", 1);
        Device device = new Device("fire extinguisher", "kitchen", 1);
        Room room = new Room(12.0, 15.0, "kitchen", 1);
        Alarm alarm = new Alarm("Ding! Ding!", 1);
        System.out.println(sensor); //toString is implicitly called when object is output using System.print
        System.out.println(device);
        System.out.println(room);
        System.out.println(alarm);

        System.out.println("Welcome!");
        System.out.println(room);

        while (true)    {
            System.out.println("Do you want to enter a new value? Use Y for yes, N for no");
            new_flag = scanner.nextLine().toUpperCase();
            if (new_flag.equals("Y"))   {
                normal_temp = sensor.getCurrentValue();
                System.out.println("Current " + sensor.getLocation() + " temperature: " + normal_temp);
                System.out.println("Enter new temperature: ");
                sensor.setCurrentValue(Double.parseDouble(scanner.nextLine()));
                trip_flag = sensor.trip(sensor.getCurrentValue());
                if (trip_flag)  {
                    device.actuate();
                    alarm.soundTheAlarm();
                    sensor.setCurrentValue(normal_temp);
                    System.out.println("Current " + sensor.getLocation() + " temperature: " + sensor.getCurrentValue());
                }
                else    {
                    System.out.println("No issues");
                }
            }
            else if (new_flag.equals("N"))  {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid entry");
            }


        }
    }
}

/*

Q. There are four related classes. How should they be organized? And how should they communicate with each
other? Write your answer as a comment at the end of main( ); include in it: (a.) why you decided on this
organization, and (b.) how the communication in main's loop would occur – which object/method would main
call, then which object/method would that call, to get all the same work done.

Ans.
a) I organized the 4 classes in their own separate files as they all have different characteristics. Each of them would
represent a different part of a home monitoring system, each of which plays its own role as a part of that system as named.
Sensor detects the problem, device takes in the data type, the room tells the dimensions and location and alarm rings for help.

b) Communication works through the main function, which basically calls the different instances as required by the system.
In my program, the different entities only communicate through the main method, so you could consider the main method the communication hub
- main calls temperature.setCurrentValue() to update the temperature
- it then calls temperature.trip() to check if the value is out of range
- if tripped, main calls extinguisher.actuate(), which prints the extinguisher’s data
- then, main calls bell.soundTheAlarm(), which prints the alarm message

*/