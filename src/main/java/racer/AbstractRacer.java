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
	protected String name;

	public AbstractRacer(String startNumber) {
		this.classType = new RacerClass("");
		this.startNumber = startNumber;
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
	
	public abstract void addStartTime(String string);
	public abstract void addFinishTime(String string);


	public abstract String getTotalTime();
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


	public void sortFinishTimes() {
		Collections.sort(finishTimes);
	}
	
	public abstract int getNumberOfLaps();

}
