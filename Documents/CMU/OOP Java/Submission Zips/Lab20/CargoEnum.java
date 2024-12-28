public enum CargoEnum {
    SMALL(8, 10),
    MEDIUM(12, 20),
    LARGE(24, 30);
    private int length, width;
    private CargoEnum(int length, int width) {
        this.length = length;
        this.width = width;}
    public int getLength() {return length;}
    public int getWidth() { return width;}
}
