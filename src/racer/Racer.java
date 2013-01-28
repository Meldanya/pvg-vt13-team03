package racer;

import java.util.ArrayList;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public class Racer {
	private int startNumber;
	private ArrayList<RacerTime> startTimes;
	private ArrayList<RacerTime> finishTimes;
	private String name;

	public Racer(int startNumber) {
		this.startNumber = startNumber;
		this.startTimes = new ArrayList<RacerTime>();
		this.finishTimes = new ArrayList<RacerTime>();
	}
	
	public void setName(String name){
		this.name = name;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public String getStartTime() {
		if (startTimes.size() > 0) {
			return startTimes.get(0).toString();
		}
		
		return "Starttid?";
	}

	public void addStartTime(RacerTime startTime) {
		this.startTimes.add(startTime);
	}

	public String getFinishTime() {
		if (finishTimes.size() > 0) {
			return finishTimes.get(0).toString();
		}

		return "Sluttid?";
	}

	public void addFinishTime(RacerTime finishTime) {
		this.finishTimes.add(finishTime);
	}

	public String getTotalTime() {
		if (startTimes.size() < 1 || finishTimes.size() < 1) {
			return "--.--.--";
		}

		RacerTime startTime = startTimes.get(0);
		RacerTime finishTime = finishTimes.get(0);
		
		return startTime.getDifferenceTo(finishTime);
	}

	/**
	 * Returns the racer as a line of the format the Sorter wants.
	 */
	@Override
	public String toString() {
		return startNumber + "; " + name +"; " + getTotalTime() + "; " + getStartTime() + "; "
				+ getFinishTime();
	}

	public boolean equals(Object obj) {
		return ((Racer) obj).startNumber == startNumber;
	}

}
