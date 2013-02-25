package racer;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public abstract class AbstractRacer implements Comparable<AbstractRacer> {

	private RacerClass classType;
	protected String startNumber;
	protected String name;
	protected List<Distance> distanceList;

	public AbstractRacer(String startNumber) {
		classType = new RacerClass("");
		this.startNumber = startNumber;
		distanceList = new ArrayList<Distance>();
		Distance emptyDistance = new Distance();
		distanceList.add(emptyDistance);
		name = "";
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

	
	public void addStartTime(String time){
		addStartTime(new RacerTime(time));
	}
	public abstract void addStartTime(RacerTime racerTime);
	
	public void addFinishTime(String time){
		addFinishTime(new RacerTime(time));
	}
	public abstract void addFinishTime(RacerTime racerTime);



	public abstract String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((startNumber == null) ? 0 : startNumber.hashCode());
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
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(AbstractRacer o) {
		return startNumber.compareTo(o.getStartNumber());
	}

	public void sortFinishTimes() {
//		TODO: fix this!
//		Collections.sort(finishTimes);
	}

	public abstract String getTotalTime();// {
//		long totalTime = distanceList.get(0).getLapTime();
//		//RacerTime totalTime = new RacerTime(0);
//		for (int i = 1; i < distanceList.size(); i++) {
//			totalTime = totalTime+(distanceList.get(i).getLapTime());
//		}
//		return new RacerTime(totalTime).toString();
//	}

	public abstract int getNumberOfLaps();

}
