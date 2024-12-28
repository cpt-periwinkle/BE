import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class represents an index builder for processing a text file. It allows creating and managing a binary search
 * tree (BST) that stores words from the file, supporting various functionalities like sorting words alphabetically,
 * sorting by frequency, and getting the words with the highest frequency.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class Index {

    /**
     * Builds an index from the given file name using a default comparator for the words.
     *
     * The method reads the file, extracts words, and inserts them into a BST while tracking the line numbers.
     *
     * @param fileName The name of the file to build the index from.
     * @return A BST containing the indexed words.
     */
    public BST<Word> buildIndex(String fileName) {
        BST<Word> tree = new BST<>();
        return buildIndex(fileName, tree);
    }

    /**
     * Builds an index from the given file name using a specified comparator for the words.
     *
     * The method reads the file, extracts words, and inserts them into a BST while tracking the line numbers.
     * The comparator is used to define the ordering of the words in the tree.
     *
     * @param fileName The name of the file to build the index from.
     * @param comparator The comparator to use for word comparison.
     * @return A BST containing the indexed words.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> tree = new BST<>(comparator);
        return buildIndex(fileName, tree);
    }

    /**
     * A helper method that processes the file and inserts words into the tree, tracking line numbers.
     *
     * Base case: If the file is empty or there are no valid words to insert, the tree remains empty.
     * Recursion case: For each word found in the file, it is inserted into the tree, and the line number is recorded.
     *
     * @param fileName The name of the file to process.
     * @param tree The BST to insert the words into.
     * @return A BST containing the indexed words.
     */
    private BST<Word> buildIndex(String fileName, BST<Word> tree)   {
        try (Scanner scanner = new Scanner(new File(fileName), "latin1")) {
            int lineNumber = 0;
            boolean ignoreCase = tree.comparator() instanceof IgnoreCase;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                String[] wordsFromText = line.split("\\W");
                for (String w : wordsFromText) {
                    if (w != null && !w.isEmpty() && w.matches("[a-zA-Z]+"))   {
                        String wordValue = ignoreCase ? w.toLowerCase() : w;
                        Word word = new Word(wordValue);
                        Word wordPresent = tree.search(word);
                        if (wordPresent == null)    {
                            word.addToIndex(lineNumber);
                            tree.insert(word);
                        } else {
                            wordPresent.setFrequency(wordPresent.getFrequency() + 1);
                            wordPresent.addToIndex(lineNumber);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        }
        return tree;
    }

    /**
     * Builds an index from a list of words using the specified comparator.
     *
     * The method inserts each word in the list into the BST and returns the constructed tree.
     *
     * @param list The list of words to insert into the BST.
     * @param comparator The comparator to use for word comparison.
     * @return A BST containing the indexed words from the list.
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> tree = new BST<>(comparator);
        for (Word word : list) {
            tree.insert(word);
        }
        return tree;
    }

    /**
     * Sorts the words in the BST alphabetically using the AlphaFreq comparator.
     *
     * Base case: If the BST is empty, an empty list is returned.
     * Recursion case: The tree is traversed, and the words are added to a list in alphabetical order.
     *
     * @param tree The BST to sort.
     * @return A list of words sorted alphabetically.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> sortedList = new ArrayList<>();
        Iterator<Word> iter = tree.iterator();
        while (iter.hasNext())  {
            sortedList.add(iter.next());
        }
        return sortedList;
    }

    /**
     * Sorts the words in the BST by their frequency using the Frequency comparator.
     *
     * Base case: If the BST is empty, an empty list is returned.
     * Recursion case: The tree is traversed, and the words are added to a list, which is then sorted by frequency.
     *
     * @param tree The BST to sort.
     * @return A list of words sorted by frequency.
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> sortedList = new ArrayList<>();
        Iterator<Word> iter = tree.iterator();
        while (iter.hasNext())  {
            sortedList.add(iter.next());
        }
        sortedList.sort(new Frequency());
        return sortedList;
    }

    /**
     * Gets the words with the highest frequency from the BST.
     *
     * The method first sorts the words by frequency and then identifies the words that have the highest frequency.
     *
     * @param tree The BST to search.
     * @return A list of words with the highest frequency.
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> words = sortByFrequency(tree);
        ArrayList<Word> highestFreqWords = new ArrayList<>();
        Iterator<Word> iter = tree.iterator();
        if (!words.isEmpty()) {
            int highestFrequency = words.get(0).getFrequency();

            while (iter.hasNext()) {
                Word word = iter.next();
                if (word.getFrequency() == highestFrequency) {
                    highestFreqWords.add(word);
                }
            }
        }
        return highestFreqWords;
    }
}
