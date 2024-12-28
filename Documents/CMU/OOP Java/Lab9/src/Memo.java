public class Memo extends Note {
    String from, to;

    public Memo() {
    }

    public Memo(String name, String body, String from, String to) {
        super(name, body);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
