import java.util.Comparator;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class implements the Comparator interface to compare the frequencies
 * of Word objects. Words are sorted in descending order of frequency, with
 * higher frequency words appearing before lower frequency ones.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class Frequency implements Comparator<Word> {

    /**
     * Compares two Word objects based on their frequencies in descending order.
     *
     * @param w1 the first Word object to compare.
     * @param w2 the second Word object to compare.
     * @return a negative integer, zero, or a positive integer as the frequency
     *         of w2 is greater than, equal to, or less than the frequency of w1.
     */
    @Override
    public int compare(Word w1, Word w2) {
        return Integer.compare(w2.getFrequency(), w1.getFrequency());
    }
}
