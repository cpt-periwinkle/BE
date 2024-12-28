import java.util.Comparator;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class implements the Comparator interface to sort Word objects first alphabetically
 * by their text and then by frequency in ascending order if the words are identical.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class AlphaFreq implements Comparator<Word> {

    /**
     * Compares two Word objects. Words are compared alphabetically by their text values.
     * If the text values are the same, the comparison is made based on their frequencies
     * in ascending order.
     *
     * @param w1 the first Word object to compare.
     * @param w2 the second Word object to compare.
     * @return a negative integer, zero, or a positive integer as w1 is less than, equal to,
     *         or greater than w2 in terms of alphabetical order or frequency.
     */
    @Override
    public int compare(Word w1, Word w2) {
        int alphabetResult = w1.getWord().compareTo(w2.getWord());
        if (alphabetResult != 0) {
            return alphabetResult;
        }
        return Integer.compare(w1.getFrequency(), w2.getFrequency());
    }
}
