import java.util.ArrayList;

public class CargoContainer {
    // List of actual cargo
    private ArrayList<Cargo> cargoList = new ArrayList<>();

    // Insert a new cargo item
    public void add(Cargo item) {
        cargoList.add(item);
    }

    // Delete the first cargo item with this size of cargo
    public Cargo remove(String contents) {
        for (Cargo item: cargoList) {
            if (item.getContents().equals(contents)) {
                cargoList.remove(item);
                // Return; don't delete all items of this size
                return item;
            }
        }
        return null;
    }
    public void display() {
        for (Cargo item: cargoList) {
            System.out.println(item.toString());
        }
    }

    public ArrayList<Cargo> getCargoList()   {
        return cargoList;
    }

}
