import java.util.Arrays;
import java.util.List;

public class BadWeekday extends Exception{
    private static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    public final static List WEEKDAYS = Arrays.asList(days);
    public BadWeekday() {
        super();
    }
    public BadWeekday(String message) {
        super("Must be one of these: Monday, Tuesday, Wednesday, Thursday, or Friday. " + message);
    }
}
