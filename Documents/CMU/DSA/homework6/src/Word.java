import java.util.HashSet;
import java.util.Set;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class represents a word with its frequency and the set of line numbers where it appears.
 * It implements the Comparable interface to allow sorting based on the word value.
 *
 * The Word object stores a word, its frequency (the number of times it appears), and a set of integers representing the line numbers
 * where the word occurs.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class Word implements Comparable<Word> {
    /** The word data. */
    private String word;
    /** Set of Line Numbers. */
    private Set<Integer> index;
    /** Frequency of word. */
    private int frequency;

    /**
     * Constructs a Word object with the given word.
     * The frequency is initialized to 1, and the index is an empty HashSet.
     *
     * @param w The word to be stored in the Word object.
     * @throws IllegalArgumentException if the word is null or empty.
     */
    public Word(String w) {
        if (w != null && !w.isEmpty()) {
            word = w;
            frequency = 1;
            index = new HashSet<>();
        } else {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
    }

    /**
     * Adds a line number to the index where the word appears.
     *
     * @param line The line number to be added to the index.
     * @throws IllegalArgumentException if the line number is null.
     */
    public void addToIndex(Integer line) {
        if (line != null) {
            this.index.add(line);
        } else {
            throw new IllegalArgumentException("Line number cannot be null");
        }
    }

    /**
     * Sets a new word value for this Word object.
     *
     * @param newWord The new word to set.
     * @throws IllegalArgumentException if the new word is null or empty.
     */
    public void setWord(String newWord) {
        if (newWord != null && !newWord.isEmpty()) {
            this.word = newWord;
        } else {
            throw new IllegalArgumentException("New word cannot be null or empty");
        }
    }

    /**
     * Gets the word value stored in this Word object.
     *
     * @return The word stored in the Word object.
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the frequency of this word.
     *
     * @param freq The frequency to set.
     * @throws IllegalArgumentException if the frequency is negative.
     */
    public void setFrequency(int freq) {
        if (freq < 0) {
            throw new IllegalArgumentException("Frequency cannot be negative");
        }
        this.frequency = freq;
    }

    /**
     * Gets the frequency of the word stored in this Word object.
     *
     * @return The frequency of the word.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Gets the set of line numbers where this word appears.
     *
     * @return A set of integers representing the line numbers.
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * Returns a string representation of this Word object.
     * The format is: word frequency index.
     *
     * @return A string representing the word, its frequency, and its index.
     */
    @Override
    public String toString() {
        return word + " " + frequency + " " + index;
    }

    /**
     * Compares this Word object to another Word object based on their word values.
     * The comparison is done lexicographically using the compareTo method of the String class.
     *
     * @param word2 The Word object to compare with.
     * @return A negative integer, zero, or a positive integer if this word is lexicographically
     * less than, equal to, or greater than the specified word.
     * @throws IllegalArgumentException if the specified word is null or its word is null.
     */
    @Override
    public int compareTo(Word word2) {
        if (word2 == null || word2.word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }

        return this.word.compareTo(word2.word);
    }
}
