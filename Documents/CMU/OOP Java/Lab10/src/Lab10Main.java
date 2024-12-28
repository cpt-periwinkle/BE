import DataPackage.DataSnooper;

public class Lab10Main {
    public static void main(String[] args) {
        DataSnooper snooper = new DataSnooper();
        snooper.snoop();
        System.out.println("\n\n");
        DataSnooper testing = new DataSnooper();
        testing.testCases();
    }
}
