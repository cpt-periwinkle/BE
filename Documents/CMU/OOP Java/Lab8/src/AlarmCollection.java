import java.util.ArrayList;

public class AlarmCollection {
    private ArrayList<Alarm> alarms;

    public AlarmCollection() {
        alarms = new ArrayList<>();
    }

    public void add(Alarm thing)  {
        alarms.add(thing);
    }

    public void display()  {
        for (Alarm item:alarms)  {
            System.out.println(item);
        }
    }
}
