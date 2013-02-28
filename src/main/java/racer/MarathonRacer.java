package racer;

public class MarathonRacer extends AbstractRacer {
	private Distance distance;
	public MarathonRacer(String startNumber) {
		super(startNumber);
		distance = new Distance();
	}

	/**
	 * Returns the racer as a line in the format the Sorter wants. Is only run
	 * if laps = 1
	 */
	@Override
	protected String racerSpecificString(int laps, boolean includeAbsoluteTimes) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getTotalTime());
		sb.append("; ");
		
		sb.append(distance.startTimeString());
		sb.append("; ");
		sb.append(distance.finishTimeString());

		appendErrorMessages(sb, distance);

		return sb.toString();
	}

	private void appendErrorMessages(StringBuilder sb, Distance distance) {
		sb.append(distance.possibleMultipleFinishTimes());

		sb.append(distance.possibleMultipleStartTimes());

		sb.append(distance.possibleImpossibleTime("Omöjlig Totaltid?"));
	}

	@Override
	public String getTotalTime() {
		String totalTime = distance.getLapTimeString();
		if (totalTime.equals("")){
			totalTime = "--.--.--";
		}
		return totalTime;
	}

	@Override
	public int getNumberOfDistances() {
		return 1;
	}

	@Override
	public void addStartTime(RacerTime racerTime) {
		distance.addStartTime(racerTime);
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		distance.addFinishTime(racerTime);
	}

	@Override
	public String startTimeString() {
		return distance.startTimeString();
	}
}
