import java.util.Random;

// Don't change this code
public class Parcel {
	private int id;
	private int deliveryTime;
	private static int idCount = 0;
	private Random random = new Random();
	
	public Parcel() {
		id = idCount++;
		deliveryTime = random.nextInt(20) + 5;
	}
	
	public int getDeliveryTime() {
		return deliveryTime;
	}
	
	public int getId() {
		return id;
	}

}
