import java.io.*;
import java.util.Scanner;

public class Lab19Main {

    public static long printWriterTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(filename)))) {
            for (int i = 1; i <= n; i++) {
                writer.print('A');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static long bufferWriterTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= n; i++) {
                writer.write('A');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static long fileWriterTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 1; i <= n; i++) {
                writer.write('A'); // Write 'A' using FileWriter
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static long scannerTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (Scanner scanner = new Scanner(
                new BufferedReader(
                        new FileReader(filename)))) {
            scanner.useDelimiter("");
            for (int i = 1; i <= n && scanner.hasNext(); i++) {
                char ch = scanner.next().charAt(0);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static long bufferedReaderTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int i = 1; i <= n; i++) {
                reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static long fileReaderTest(String filename, int n)  {
        long startTime = System.nanoTime();

        try (FileReader reader = new FileReader(filename)) {
            for (int i = 1; i <= n; i++) {
                reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        String filename = "test.txt";

        // First test with 10000
        int n1 = 10000;
        System.out.printf("Testing with n = %d\n", n1);

        long printWriterTime1 = printWriterTest(filename, n1);
        long bufferWriterTime1 = bufferWriterTest(filename, n1);
        long fileWriterTime1 = fileWriterTest(filename, n1);
        long scannerTime1 = scannerTest(filename, n1);
        long bufferedReaderTime1 = bufferedReaderTest(filename, n1);
        long fileReaderTime1 = fileReaderTest(filename, n1);

        System.out.printf("%-20s %15d ns\n", "PrintWriter Test:", printWriterTime1);
        System.out.printf("%-20s %15d ns\n", "BufferedWriter Test:", bufferWriterTime1);
        System.out.printf("%-20s %15d ns\n", "FileWriter Test:", fileWriterTime1);
        System.out.printf("%-20s %15d ns\n", "Scanner Test:", scannerTime1);
        System.out.printf("%-20s %15d ns\n", "BufferedReader Test:", bufferedReaderTime1);
        System.out.printf("%-20s %15d ns\n", "FileReader Test:", fileReaderTime1);

        // Second test with 1000000
        int n2 = 1000000;
        System.out.printf("\nTesting with n = %d\n", n2);

        long printWriterTime2 = printWriterTest(filename, n2);
        long bufferWriterTime2 = bufferWriterTest(filename, n2);
        long fileWriterTime2 = fileWriterTest(filename, n2);
        long scannerTime2 = scannerTest(filename, n2);
        long bufferedReaderTime2 = bufferedReaderTest(filename, n2);
        long fileReaderTime2 = fileReaderTest(filename, n2);

        System.out.printf("%-20s %15d ns\n", "PrintWriter Test:", printWriterTime2);
        System.out.printf("%-20s %15d ns\n", "BufferedWriter Test:", bufferWriterTime2);
        System.out.printf("%-20s %15d ns\n", "FileWriter Test:", fileWriterTime2);
        System.out.printf("%-20s %15d ns\n", "Scanner Test:", scannerTime2);
        System.out.printf("%-20s %15d ns\n", "BufferedReader Test:", bufferedReaderTime2);
        System.out.printf("%-20s %15d ns\n", "FileReader Test:", fileReaderTime2);
    }
}
