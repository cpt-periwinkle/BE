package NotePackage;

import java.util.Comparator;

public class NumberSorter implements Comparator<Note> {

    @Override
    public int compare(Note n1, Note n2) {
        return Integer.compare(n1.getNoteNumber(), n2.getNoteNumber());
    }
}
