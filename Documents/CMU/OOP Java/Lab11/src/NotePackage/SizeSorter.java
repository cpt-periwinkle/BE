package NotePackage;

import java.util.Comparator;

public class SizeSorter implements Comparator<Note> {

    @Override
    public int compare(Note n1, Note n2) {
        return Integer.compare(n1.toString().length(), n2.toString().length());
    }
}
