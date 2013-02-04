package racer;

import java.util.ArrayList;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public class Racer implements Comparable {
	private RacerClass classType;
	private String startNumber;
	private ArrayList<RacerTime> startTimes;
	private ArrayList<RacerTime> finishTimes;
	private String name;

	public Racer(String startNumber) {
		this.classType = new RacerClass("");
		this.startNumber = startNumber;
		this.startTimes = new ArrayList<RacerTime>();
		this.finishTimes = new ArrayList<RacerTime>();
	}
	
	public void setName(String name){
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
		StringBuilder sb = new StringBuilder();
		sb.append(startNumber);
		sb.append("; ");
		sb.append(name);
		sb.append("; ");
		sb.append(getTotalTime());
		sb.append("; ");
		sb.append(getStartTime());
		sb.append("; ");
		sb.append(getFinishTime());
		
		if (startTimes.size() > 1) {
			sb.append("; Flera starttider?");
			for(int i = 1; i < startTimes.size(); i++) {
				sb.append(" ");
				sb.append(startTimes.get(i));
			}
		}
		
		return sb.toString();
	}

	public boolean equals(Object obj) {
	    // TODO: generera en ordentlig equals och även hashCode
		return ((Racer) obj).startNumber == startNumber;
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

	@Override
	public int compareTo(Object o) {
		return startNumber.compareTo(((Racer)o).getStartNumber());
	}

}
