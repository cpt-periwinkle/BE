import java.util.ArrayList;

public class SensorCollection {
    private ArrayList<Sensor> sensors;

    public SensorCollection() {
        sensors = new ArrayList<>();
    }

    public void add(Sensor thing)  {
        sensors.add(thing);
    }

    public void display()  {
        for (Sensor item:sensors)  {
            System.out.println(item);
        }
    }

}
