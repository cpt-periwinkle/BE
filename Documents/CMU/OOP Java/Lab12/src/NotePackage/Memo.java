package NotePackage;

public class Memo extends Note {
    String from, to;

    Memo() {
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    Memo(String name, String body, String from, String to) {
        super(name, body);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
