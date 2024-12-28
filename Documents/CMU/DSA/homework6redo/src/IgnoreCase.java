import java.util.Comparator;

/**
 * 17683 Data Structures for Applications Programmers.
 * Homework 6
 *
 * This class implements the Comparator interface to compare Word objects
 * in a case-insensitive manner based on their text values.
 *
 * Andrew ID: skalekar
 * @author Shlok Kalekar
 */
public class IgnoreCase implements Comparator<Word> {

    /**
     * Compares two Word objects in a case-insensitive manner. The comparison is based on
     * the text values of the Word objects, ignoring differences in letter case.
     *
     * @param w1 the first Word object to compare.
     * @param w2 the second Word object to compare.
     * @return a negative integer, zero, or a positive integer as w1 is less than, equal to,
     *         or greater than w2, ignoring case differences.
     */
    @Override
    public int compare(Word w1, Word w2) {
        return w1.getWord().compareToIgnoreCase(w2.getWord());
    }
}
