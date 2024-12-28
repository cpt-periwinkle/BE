package DataPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSnooper {
    Data d = new Data();

    public void snoop() {
        System.out.println("Beginning DataPackage.Data");
        System.out.println(d.toString());

        // Problem 1
        System.out.println("Indirect access");
        System.out.println("iValue = " + d.getiValue());
        System.out.println("sValue = " + d.getsValue());
        System.out.println("iList = ");
        for (int item:d.getiList()) {
            System.out.println(item);
        }

        // Problem 2
        System.out.println("Change the simple data");
        d.setiValue(-1);
        d.setsValue("Dog");
        int[] newList = {0, 0, 0};
        d.setiList(newList);
        System.out.println(d.toString());

        // Problem 3
        System.out.println("Change the array");
        int[] mylist = {-1, -2, -3, -4, -5};
        d.setiList(mylist);
        System.out.println(d.toString());

        // Problem 4
        System.out.println("Change the ArrayList");
        ArrayList<Integer> yourlist = new ArrayList<>();
        yourlist.add(1000);
        d.setaList(yourlist);
        System.out.println(d.toString());

        // Problem 5
        Data newD = new Data(d);
        newD.setsValue("Cat");
        newD.getCc().setCcString("Elephant");
        System.out.println(newD.toString());
        System.out.println(d.toString());

        ContainedClass x = new ContainedClass();
        x.setCcString("Gopher");
        d.setCc(x);
        System.out.println(d);
        System.out.println(newD);
    }

    public void testCases() {
        System.out.println("Test Cases-");

        System.out.println("\nRule a Test: iValue must be positive");
        System.out.println("Attempting to set iValue to -5 (should not update):");
        d.setiValue(-5);
        System.out.println("iValue = " + d.getiValue()); // Should still be the original positive value

        System.out.println("Attempting to set iValue to 20 (should update):");
        d.setiValue(20);
        System.out.println("iValue = " + d.getiValue()); // Should be updated to 20

        System.out.println("\nRule b Test: sValue can have a maximum of 10 characters");
        System.out.println("Attempting to set sValue to a string longer than 10 characters:");
        d.setsValue("ThisIsAVeryLongString"); // Should not update or truncate
        System.out.println("sValue = " + d.getsValue()); // Should still be the previous valid value or truncated

        System.out.println("Attempting to set sValue to 'ShortStr':");
        d.setsValue("ShortStr");
        System.out.println("sValue = " + d.getsValue()); // Should update to 'ShortStr'

        System.out.println("\nRule c Test: iList can have a maximum of 4 entries");
        System.out.println("Attempting to set iList with more than 4 entries:");
        int[] tooLongList = {12, 13, 14, 15, 16}; // Should not be allowed
        d.setiList(tooLongList);
        System.out.println("iList = " + Arrays.toString(d.getiList())); // Should not update if size > 4

        System.out.println("Attempting to set iList with valid 4 entries:");
        int[] validList = {10, 11, 12, 13}; // Should be allowed
        d.setiList(validList);
        System.out.println("iList = " + Arrays.toString(d.getiList())); // Should update

        System.out.println("\nRule d Test: iList’s entries must be between 10 and 20 inclusive");
        System.out.println("Attempting to set iList with out-of-bound values (below 10 and above 20):");
        int[] invalidBoundList = {8, 9, 21, 22}; // Should not update
        d.setiList(invalidBoundList);
        System.out.println("iList = " + Arrays.toString(d.getiList())); // Should not update with out-of-bounds values

        System.out.println("Attempting to set iList with valid values between 10 and 20:");
        int[] validBoundList = {10, 15, 17, 20}; // Should be allowed
        d.setiList(validBoundList);
        System.out.println("iList = " + Arrays.toString(d.getiList())); // Should update

    }
}
