public class Room {
    private double length, width;
    private String name;
    private int id;

    public Room() {
        length = 10.0;
        width = 5.0;
        name = "porch";
        id = 10;
    }

    public Room(double length, double width, String name, int id)   {
        this.length = length;
        this.width = width;
        this.name = name;
        this.id = id;
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
