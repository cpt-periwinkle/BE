// Don't change this class

import java.util.Arrays;

public class Stats {
	private int[] parcels;
	private int[] times;
	private long[] robotTimes;
	private int numberOfRobots;
	
	public Stats(int numberOfRobots) {
		this.numberOfRobots = numberOfRobots;
		this.parcels = new int[numberOfRobots];
		this.times = new int[numberOfRobots];
		this.robotTimes = new long[numberOfRobots];
	}
	
	public synchronized void putParcel(int robotNumber) {
		parcels[robotNumber]++;
	}

	public synchronized void putTime(int robotNumber, int time) {
		times[robotNumber] += time;
	}
	
	public synchronized void putRobotTime(int robotNumber, long time) {
		robotTimes[robotNumber] = time;
	}
	
	public synchronized int getParcel(int robotNumber) {
		return parcels[robotNumber];
	}

	public synchronized int getTimes(int robotNumber) {
		return times[robotNumber];
	}

	public synchronized long getRobotTime(int robotNumber) {
		return robotTimes[robotNumber];
	}

}
