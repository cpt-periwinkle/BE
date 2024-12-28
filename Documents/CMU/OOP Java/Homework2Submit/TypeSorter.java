package CitationPackage;

import java.util.Comparator;

/**
 * A comparator for comparing two Citation objects based on the type of offense.
 * <p>
 * This class implements the Comparator interface and defines the comparison logic for sorting citations
 * by their type of offense in a case-insensitive manner.
 * </p>
 */
public class TypeSorter implements Comparator<Citation> {

    /**
     * Compares two Citation objects based on their type of offense.
     * <p>
     * The comparison is case-insensitive and is based on the lexicographical order of the offense type.
     * </p>
     *
     * @param c1 The first Citation object to be compared.
     * @param c2 The second Citation object to be compared.
     * @return A negative integer, zero, or a positive integer as the type of offense of the first
     *         Citation is less than, equal to, or greater than that of the second Citation.
     */
    public int compare(Citation c1, Citation c2) {
        return c1.getTypeOfOffense().compareToIgnoreCase(c2.getTypeOfOffense());
    }
}
