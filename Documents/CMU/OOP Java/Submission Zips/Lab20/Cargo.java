import java.io.Serializable;

public abstract class Cargo implements CargoSizeable, Serializable {
    protected int length, width;
    protected String contents;

    @Override
    public String toString() {
        return "Cargo{" +
                "length=" + length +
                ", width=" + width +
                ", contents='" + contents + '\'' +
                '}';
    }

    public Cargo() {}

    public Cargo(int length, int width) {
        this.length = length;
        this.width = width;
        this.contents = "Cargo";
    }

    @Override
    public int getLength() { return length; }

    @Override
    public int getWidth() { return width; }

    public String getContents() { return contents; }
}
