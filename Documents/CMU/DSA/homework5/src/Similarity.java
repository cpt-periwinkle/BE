import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 5 Document Similarity
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class Similarity {
    /**
     * Data Structure: HashMap is used to store the word frequencies.
     * - Key: Word (String)
     * - Value: Frequency (BigInteger) - Used to allow for larger frequencies to be accommodated
     *
     * Reason: HashMap allows fast O(1) average-time complexity for inserting and retrieving keys,
     * making it efficient for counting word frequencies and performing similarity calculations. It also allows
     * to keep track of the duplicates in frequency variable, which would have been ignored in HashSet (unique values only).
     */
    private final HashMap<String, BigInteger> map;

    /** Used to track totalLines in file. */
    private int totalLines;

    /**
     * Constructs a Similarity object using given string.
     * The input string is processed to compute word frequencies and count number of lines.
     *
     * @param string the string to be analyzed. Can be null or empty.
     */
    public Similarity(String string) {
        map = new HashMap<>();
        if (string != null && !string.isEmpty()) {
            processWords(string);
            totalLines = 1;
        } else {
            totalLines = 0;
        }
    }

    /**
     * Constructs a Similarity object using a given file.
     * The file is read line by line, and each line is processed to compute word frequencies.
     *
     * @param file the file to be analyzed. Can be null or nonexistent.
     */
    public Similarity(File file) {
        map = new HashMap<>();
        totalLines = 0;
        if (file != null && file.exists()) {
            try (Scanner scanner = new Scanner(file, "latin1")) {
                while (scanner.hasNextLine()) {
                    processWords(scanner.nextLine());
                    totalLines++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    }

    /**
     * Processes a string to compute word frequencies and stores them in the map.
     *
     * @param input the string to be processed.
     */
    private void processWords(String input) {
        String[] words = input.toLowerCase().split("\\W");
        for (String word : words) {
            if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                map.put(word, map.getOrDefault(word, BigInteger.ZERO).add(BigInteger.ONE));
            }
        }
    }

    /**
     * Returns the total number of lines in the input data.
     *
     * @return the total number of lines.
     */
    public int numOfLines() {
        return totalLines;
    }

    /**
     * Returns the total number of words in the input data, including duplicates.
     *
     * @return the total number of words as a {@code BigInteger}.
     */
    public BigInteger numOfWords() {
        BigInteger sumOfWords = BigInteger.ZERO;
        for (BigInteger value : map.values()) {
            sumOfWords = sumOfWords.add(value);
        }
        return sumOfWords;
    }

    /**
     * Returns the total number of unique words in the input data.
     *
     * @return the total number of unique words.
     */
    public int numOfWordsNoDups() {
        return map.size();
    }

    /**
     * Computes the Euclidean norm of this object's default map.
     *
     * @return the Euclidean norm as a {@code double}.
     */
    public double euclideanNorm() {
        return euclideanNorm(map);
    }

    /**
     * Computes the Euclidean norm of the passed map.
     *
     * @param mapField the map containing word frequencies.
     * @return the Euclidean norm as a {@code double}.
     */
    private double euclideanNorm(Map<String, BigInteger> mapField) {
        if (mapField == null || mapField.isEmpty()) {
            return 0;
        }
        double squareSum = 0;
        for (BigInteger value : mapField.values()) {
            squareSum += Math.pow(value.doubleValue(), 2);
        }
        return Math.sqrt(squareSum);
    }

    /**
     * Computes the dot product between this object's default map and another map.
     *
     * The method does not go into quadratic time complexity as it only goes through the default map loop once,
     * while using the key to access the same key in otherMap, which takes O(1).
     * Therefore, the worst time complexity of the method is O(n) (the number of pairs in first default set).
     *
     * @param otherMap the other word frequency map.
     * @return the dot product as a {@code double}.
     */
    public double dotProduct(Map<String, BigInteger> otherMap) {
        if (otherMap == null || otherMap.isEmpty()) {
            return 0;
        }
        double productSum = 0;
        for (String key : map.keySet()) {
            if (otherMap.containsKey(key)) {
                productSum += map.get(key).doubleValue() * otherMap.get(key).doubleValue();
            }
        }
        return productSum;
    }

    /**
     * Computes the distance between this object's default map and another map.
     * If either or both map not present, the distance is put as dissimilar (Math.PI / 2)
     * If Euclidean normals of both maps are the same: the distance is put as equal (0)
     * If Euclidean normals or dotProduct is 0: the distance is put as dissimilar (Math.PI / 2)
     *
     * @param otherMap the other word frequency map.
     * @return the distance as a {@code double}. If the maps are not similar or empty, returns {@code Math.PI / 2}.
     */
    public double distance(Map<String, BigInteger> otherMap) {
        if (otherMap == null || otherMap.isEmpty() || this.map.isEmpty()) {
            return Math.PI / 2;
        }
        double dotProduct = this.dotProduct(otherMap);
        double norm1 = euclideanNorm();
        double norm2 = euclideanNorm(otherMap);

        if (norm1 == norm2) {
            return 0.0;
        }

        if (norm1 == 0 || norm2 == 0 || dotProduct == 0) {
            return Math.PI / 2;
        }

        return Math.acos(dotProduct / (norm1 * norm2));
    }

    /**
     * Returns a copy of the word frequency map.
     *
     * @return a copy of the map containing word frequencies.
     */
    public Map<String, BigInteger> getMap() {
        return new HashMap<>(map);
    }
}
