package NotePackage;

public abstract class Note implements Comparable<Note> {
    String name, body;
    int noteNumber = 0;
    static String FOOTER = "*****Powered by Make-A-Note*****";
    static int noteCount = 0;

    public Note() {
    }

    public Note(String name, String body) {
        this.name = name;
        this.body = body;
        noteCount++;
        this.noteNumber = noteCount;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Body: " + body + '\n' +
                "Note#" + noteNumber + '\n' +
                FOOTER + '\n';
    }

    @Override
    public int compareTo(Note note) {
        return (this.name).compareToIgnoreCase(note.name);
    }
}
