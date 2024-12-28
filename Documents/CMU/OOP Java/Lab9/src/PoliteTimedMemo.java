public class PoliteTimedMemo extends TimedMemo {
    String DEFAULT_GREETING = "Dear", DEFAULT_CLOSING = "Yours truly,";

    public PoliteTimedMemo(String name, String body, String from, String to) {
        super(name, body, from, to);
    }

    @Override
    public String toString() {
        return '\n' + "Date: " + today + '\n' +
                "Name: " + name + '\n' +
                DEFAULT_GREETING + " " + to + '\n' +
                body + '\n' +
                DEFAULT_CLOSING + '\n' +
                from + '\n' +
                "Note#" + noteNumber + '\n' +
                FOOTER;
    }
}
