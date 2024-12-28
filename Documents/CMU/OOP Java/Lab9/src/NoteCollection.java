import java.util.ArrayList;

public class NoteCollection {
    private ArrayList<Note> noteList= new ArrayList<>();

    public void add(Note note)  {
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
}
