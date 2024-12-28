import java.util.concurrent.BlockingQueue;

public class Robot implements Runnable {
	private int id;
	private static int idCount = 0;
	private long beginTime, endTime;
	private int battery = 100;   // Use this for the loop condition
	private BlockingQueue<Parcel> queue;
	private Stats stats;
	
	public Robot(BlockingQueue<Parcel> queue, Stats stats) {
		this.queue = queue;
		this.stats = stats;
		id = idCount++;
	}

	@Override
	public void run() {
		beginTime = System.currentTimeMillis();
		Parcel p = null;

        // Loop until the battery dies
        while (battery > 0) {
            // YOUR CODE HERE
            // take() a parcel
            try {
                p = queue.take();
            } catch (InterruptedException e) {
				System.out.println("Error: " + e.getMessage());
            }
            // Thread.sleep() for the parcel's delivery time
			try {
				Thread.sleep(p.getDeliveryTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            // decrement the battery
			battery -= p.getDeliveryTime();
            // print parcel data in a synchronized block
            synchronized (stats) {
                // This is just for spacing
                for(int i=0; i<id; i++) {
                    System.out.print("\t\t\t");
                }
                // Delivery time
				System.out.println("parcel #" + p.getId() + " in " + p.getDeliveryTime());
            }

            // update the stats for robot #id
            // Don't change these lines
			stats.putParcel(id);
			stats.putTime(id, p.getDeliveryTime());
        }

		endTime = System.currentTimeMillis();
		// update robot running time
		stats.putRobotTime(id, endTime-beginTime);
		// update the shared counter
		Lab24Main.counter.getAndDecrement();
	}

}
