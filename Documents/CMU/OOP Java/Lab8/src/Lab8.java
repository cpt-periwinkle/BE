public class Lab8 {
    public static void main(String[] args) {
        Room room1 = new Room(12.0, 15.0, "kitchen", 1);
        Device device = new Device("fire extinguisher", "kitchen", 1);
        room1.addDevice(device);
        for (int i = 1; i <= 5; i++) {
            Sensor sensor = new Sensor(0.0, 120.0, 68.0, 1.0, "kitchen", "temperature", i);
            room1.addSensor(sensor);
        }
        for (int j = 1; j<= 3; j++) {
            Alarm alarm = new Alarm("Ding! Ding!", j);
            room1.addAlarm(alarm);
        }
        room1.display();
        Device chemicalFoamer = new Device("chemical foamer", "kitchen", 2);
        room1.addDevice(chemicalFoamer);
        room1.display();
    }
}
