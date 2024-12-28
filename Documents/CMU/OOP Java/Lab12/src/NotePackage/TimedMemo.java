package NotePackage;

public class TimedMemo extends Memo {
    String today;

    TimedMemo (){
    }

    TimedMemo(String name, String body, String from, String to) {
        super(name, body, from, to);
        this.today = java.time.LocalDate.now().toString();
    }

    public String getToday() {
        return today;
    }

    @Override
    public String toString() {
        return '\n' + "Date: " + today + '\n' +
                super.toString();
    }
}
