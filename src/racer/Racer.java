package racer;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public class Racer {
	private int startNumber;
	private int startTime;
	private int finishTime;
	private String name;

	public Racer(int startNumber) {
		this.startNumber = startNumber;
		this.startTime = -1;
		this.finishTime = -1;
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

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getTotalTime() {
		return finishTime - startTime;
	}

	/**
	 * Returns the racer as a line of the format the Sorter wants.
	 */
	@Override
	public String toString() {
		return startNumber + "; " + name +"; " + getTotalTime() + "; " + startTime + "; "
				+ finishTime;
	}

	public boolean equals(Object obj) {
		if (((Racer) obj).getStartNumber() == startNumber) {
			return true;
		} else {
			return false;
		}
	}

}
