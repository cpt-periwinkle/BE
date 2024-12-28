public class Sensor {
    private double minimum, maximum, currentValue, interval;
    private String location, type;
    private int id;

    public Sensor() {
        minimum = 0.0;
        maximum = 200.0;
        currentValue = 100.0;
        interval = 1.0;
        location = "porch";
        type = "temperature";
        id = 10;
    }

    public Sensor(double minimum, double maximum, double currentValue, double interval, String location, String type, int id){
        this.minimum = minimum;
        this.maximum = maximum;
        this.currentValue = currentValue;
        this.interval = interval;
        this.location = location;
        this.type = type;
        this.id = id;
    }

    public boolean trip (double currentValue)   {
        return currentValue < minimum || currentValue > maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "minimum: " + minimum +
                ", maximum: " + maximum +
                ", currentValue: " + currentValue +
                ", interval: " + interval +
                ", location: '" + location + '\'' +
                ", type: '" + type + '\'' +
                ", id: " + id +
                '}';
    }
}
