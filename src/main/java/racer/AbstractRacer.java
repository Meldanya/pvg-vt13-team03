package racer;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public abstract class AbstractRacer implements Comparable<AbstractRacer> {

	private RacerClass classType;
	protected String startNumber;
	protected String name;

	public AbstractRacer(String startNumber) {
		classType = new RacerClass("");
		this.startNumber = startNumber;
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

	public abstract void addStartTime(RacerTime racerTime);

	public abstract void addFinishTime(RacerTime racerTime);

	public String racerString(int maxLapCount, boolean includeAbsoluteTimes) {
		StringBuilder sb = new StringBuilder();
		sb.append(startNumber);
		sb.append("; ");
		sb.append(name);
		sb.append("; ");
		appendRacerSpecificString(sb, maxLapCount);

		if (includeAbsoluteTimes) {
			appendAbsoluteTimes(sb, maxLapCount);
		}
		removeTrailingSpace(sb);

		appendErrorMessages(sb);
		return sb.toString();
	}

	protected abstract void appendRacerSpecificString(StringBuilder sb, int laps);

	protected abstract void appendAbsoluteTimes(StringBuilder sb,
			int maxLapCount);

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
		return Integer.parseInt(startNumber)
				- Integer.parseInt(o.getStartNumber());
	}

	public abstract String getTotalTime();

	public abstract int getNumberOfDistances();

	public abstract String startTimeString();

	protected abstract void appendErrorMessages(StringBuilder sb);

	protected void removeTrailingSpace(StringBuilder sb) {
		int indexOfLastChar = sb.length() - 1;
		if (sb.charAt(indexOfLastChar) == ' ') {
			sb.deleteCharAt(indexOfLastChar);
		}
	}
}
