public class Alarm {
    private String message;
    private int id;

    public Alarm() {
        message = "Ding Dong";
        id = 10;
    }

    public Alarm(String message, int id)    {
        this.message = message;
        this.id = id;
    }

    public void soundTheAlarm ()    {
        System.out.println(message);
        System.out.println("Calling 911 . . .");
        System.out.println("This is the fire department, we have dispatched units to your area");
        System.out.println("We have fixed your problem");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "message: '" + message + '\'' +
                ", id: " + id +
                '}';
    }
}
