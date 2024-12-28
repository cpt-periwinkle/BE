package NotePackage;

import java.util.ArrayList;
import java.util.Collections;

public class NoteCollection {
    private static ArrayList<Note> noteList= new ArrayList<>();

    public static void add(Note note)  {
        noteList.add(note);
    }

    public ArrayList<Note> getAllNotes() {
        return noteList;
    }

    public Note getNoteByNumber(int num)    {
        for (Note note:noteList)    {
            if (note.noteNumber == num)
                return note;
        }
        return null;
    }

    public ArrayList<Note> getNoteByName(String name)   {
        ArrayList<Note> foundNotes = new ArrayList<>();
        for (Note note:noteList)    {
            if ((note.name).equalsIgnoreCase(name))
                foundNotes.add(note);
        }
        return foundNotes;
    }

    public void sortByName()    {
        Collections.sort(noteList);
    }

    public void sortByNumber()  {
        Collections.sort(noteList, new NumberSorter());
    }

    public void sortBySize()    {
        Collections.sort(noteList, new SizeSorter());
    }

    public static Note createNote(String type, String name, String body, String from, String to)    {
        Note noteMemo = null;
        if( type.equalsIgnoreCase("normal"))
            noteMemo = new Memo(name, body, from, to);
        else if (type.equalsIgnoreCase("timed")) {
            noteMemo = new TimedMemo(name, body, from, to);
        }
        else if( type.equalsIgnoreCase("polite"))
            noteMemo = new PoliteTimedMemo.Builder()
                    .name(name)
                    .body(body)
                    .from(from)
                    .to(to)
                    .build();
        add(noteMemo);
        return noteMemo;
    }
}
