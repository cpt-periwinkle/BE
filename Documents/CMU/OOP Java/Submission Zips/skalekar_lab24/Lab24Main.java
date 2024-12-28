import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab24Main implements Runnable {

	// Shared queue for Parcel objects
	private BlockingQueue<Parcel> queue;
	
	// Shared Stats object
	private Stats stats;
	
	// Shared counter
	public static AtomicInteger counter;

    // Robots and threads
	private Robot[] robots;
	private Thread[] threads;

	private static Scanner scanner = new Scanner(System.in);
	private int numbots;
	private long beginTime, endTime;

    // Sets up the problem
    // Don't change this code
	public static void main(String[] args) {
		Lab24Main lab = new Lab24Main();

		System.out.print("Enter the number of robots: ");
		int count = scanner.nextInt();

        // Header for robot output
        System.out.println("\nPackage Delivery Times, per robot\n");
		for (int i=0; i<count; i++) {
			System.out.print("Robot#" + i + "\t\t\t");
		}
		System.out.println("\n------------------------------------------------");

        // Call setup to create the shared data structures,
        // the robots, and start the robot threads
		lab.setup(count);

        // Create the warehouse thread and run it
		Thread warehouse = new Thread(lab);
		warehouse.start();
	}
	
    // Creates the data structures, robots, and threads
    // Starts the threads
	private void setup(int count) {

        // New up the data structures
		numbots = count;
		queue = new LinkedBlockingQueue<>();
		stats = new Stats(numbots);
		counter = new AtomicInteger(numbots);

		// Create robot and thread arrays
		robots = new Robot[numbots];
		threads = new Thread[numbots];

		for (int i=0; i< numbots; i++) {
            // YOUR CODE HERE
            // In this loop, for each robot:
            // 1. new up a robot with the 
            //    shared data structures
             robots[i] = new Robot(queue, stats);

            // 2. new up a thread with a robot
             threads[i] = new Thread(robots[i]);

		    // 3. start the thread
             threads[i].start();

		}
	}

	@Override
	public void run() {
        // This is the warehouse code
        // The warehouse "receives" packages by
        //    new'ing them up; then it puts them
        //    on the shared queue; then it sleeps for
        //    5 time units to slow it down a little;
        //    continue while the shared counter is positive
        //    - the robots will decrement the counter, not
        //    the warehouse.
        //  At the end, it should wait for the robot
        //    threads to finish their work
		beginTime = System.currentTimeMillis();
        // Loop while the counter is positive
        while (counter.get() > 0) {
            // YOUR CODE HERE
            // Create a Parcel
			Parcel parcel = new Parcel();
            // offer it to the queue
			queue.offer(parcel);
            // Sleep for 5 units
            try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        // Loop to join the threads
        // YOUR CODE HERE
		for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

		endTime = System.currentTimeMillis();
		printStats();
	}
	
    // Print the final simulation statistics
    // Don't change this code
	public void printStats() {
		System.out.println("\n\nRobot Summary");
		System.out.format("%9s %10s %15s %15s\n", "Robot#", "# parcels", "Parcel Time", "Running Time");
		int totalParcels = 0;
		int totalTime = 0;
		long totalRunningTime = 0;
		for (int i=0; i<numbots; i++) {
			totalParcels += stats.getParcel(i);
			totalTime += stats.getTimes(i);
			totalRunningTime += stats.getRobotTime(i);
			System.out.format("%9d %10d %15d %15d\n", i, stats.getParcel(i), stats.getTimes(i), stats.getRobotTime(i));
		}
		System.out.println("------------------------------------------------------");
		System.out.format("%9s %10d %15d %15d\n", 
				"Totals: "+numbots, totalParcels, totalTime, totalRunningTime);
		System.out.println("\nTotal elapsed time: " + (endTime-beginTime));
		System.out.println("Number of parcels left on queue: " + queue.size());
	}

}
