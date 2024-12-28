public class Room {
    private double length, width;
    private String name;
    private int id;
    SensorCollection sensor_list;
    AlarmCollection alarm_list;
    Device device;

    public Room() {
        length = 10.0;
        width = 5.0;
        name = "porch";
        id = 10;
        sensor_list = new SensorCollection();
        alarm_list = new AlarmCollection();
    }

    public Room(double length, double width, String name, int id)   {
        this.length = length;
        this.width = width;
        this.name = name;
        this.id = id;
        this.sensor_list = new SensorCollection();
        this.alarm_list = new AlarmCollection();
    }

    public void addSensor(Sensor s) {
        sensor_list.add(s);
    }

    public void addAlarm(Alarm a) {
        alarm_list.add(a);
    }

    public void addDevice(Device d) {
        this.device = d;
    }

    public void display()   {
        System.out.println(this);
        System.out.println(device);
        sensor_list.display();
        alarm_list.display();
    }

    public double getArea() {
        return (length * width) ;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "length: " + length +
                ", width: " + width +
                ", name: '" + name + '\'' +
                ", id: " + id +
                '}';
    }
}
