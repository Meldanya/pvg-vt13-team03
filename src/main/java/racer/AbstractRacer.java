package racer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public abstract class AbstractRacer implements Comparable<AbstractRacer> {

	private RacerClass classType;
	protected String startNumber;
	protected ArrayList<RacerTime> startTimes;
	protected ArrayList<RacerTime> finishTimes;
	protected String name;

	public AbstractRacer(String startNumber) {
		this.classType = new RacerClass("");
		this.startNumber = startNumber;
		this.startTimes = new ArrayList<RacerTime>();
		this.finishTimes = new ArrayList<RacerTime>();
		this.name = "";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public String getStartTime() {
		if (startTimes.size() > 0) {
			return startTimes.get(0).toString();
		}

		return "Start?";
	}

	public void addStartTime(RacerTime startTime) {
		this.startTimes.add(startTime);
	}

	public String getFinishTime() {
		if (finishTimes.size() > 0) {
			return finishTimes.get(finishTimes.size() - 1).toString();
		}

		return "Slut?";
	}

	public void addFinishTime(RacerTime finishTime) {
		this.finishTimes.add(finishTime);
	}

	public String getTotalTime() {
		if (startTimes.size() < 1 || finishTimes.size() < 1) {
			return "--.--.--";
		}

		RacerTime startTime = startTimes.get(0);
		RacerTime finishTime = finishTimes.get(finishTimes.size() - 1);

		return startTime.getDifferenceTo(finishTime);
	}
	public abstract String toString();
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startNumber == null) ? 0 : startNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractRacer))
			return false;
		AbstractRacer other = (AbstractRacer) obj;
		if (startNumber == null) {
			if (other.startNumber != null)
				return false;
		} else if (!startNumber.equals(other.startNumber))
			return false;
		return true;
	}

	public RacerClass getClassType() {
		return classType;
	}

	public void setClassType(RacerClass classType) {
		this.classType = classType;
	}

	public void setClassType(String className) {
		this.classType = new RacerClass(className);
	}

	/**
	 * Used by TreeMap in Competition.getRacers()
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(AbstractRacer o) {
		return startNumber.compareTo(o.getStartNumber());
	}

	public ArrayList<String> getLapTimes() {
		ArrayList<String> lapTimes = new ArrayList<String>();

		if (finishTimes.size() > 0) {
			if (startTimes.size() > 0) {
				String lapOne = startTimes.get(0).getDifferenceTo(
						finishTimes.get(0));
				lapTimes.add(lapOne);
			} else {
				lapTimes.add("");
			}
			for (int i = 1; i < finishTimes.size(); i++) {
				String lapTime = finishTimes.get(i - 1).getDifferenceTo(
						finishTimes.get(i));

				lapTimes.add(lapTime);
			}
		}

		return lapTimes;
	}

	public int getNumberOfLaps() {
		int laps = finishTimes.size();

		// Subtract one lap if the racer lacks a start time
		laps -= startTimes.size() > 0 ? 0 : 1;

		return laps;
	}

	public void sortFinishTimes() {
		Collections.sort(finishTimes);
	}

}
