import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class CargoFileOperations {
    private String filename;

    public CargoFileOperations(String filename) {
        this.filename = filename;
    }

    public void writeList(ArrayList<Cargo> list) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            for (Cargo cargo : list) {
                try {
                    out.writeObject(cargo);
                } catch (IOException e) {
                    System.err.println("Failed to write cargo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Output file failed to open: " + e.getMessage());
            System.exit(1);
        }
    }

    public ArrayList<Cargo> readList() {
        ArrayList<Cargo> cargoList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (fileIn.available() > 0) {
                try {
                    Cargo cargo = (Cargo) in.readObject();
                    cargoList.add(cargo);
                } catch (ClassNotFoundException | IOException e) {
                    System.err.println("Failed to read cargo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Input file failed to open: " + e.getMessage());
            System.exit(1);
        }
        return cargoList;
    }

    // Method to display file information
    public void display() {
        Path path = Paths.get(filename);
        File file = path.toFile();
        System.out.println("Path: " + path.toString());
        System.out.println("Absolute Path: " + path.toAbsolutePath());
        System.out.println("Root: " + path.getRoot());

        System.out.println("File is directory: " + file.isDirectory());
        System.out.println("File absolute path: " + file.getAbsolutePath());

        System.out.println("isExecutable returns " + file.canExecute());
        System.out.println("isReadable returns " + file.canRead());
        System.out.println("isWritable returns" + file.canWrite());
    }
}
