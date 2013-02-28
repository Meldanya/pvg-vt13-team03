package racer;

public class MarathonRacer extends AbstractRacer {
	public MarathonRacer(String startNumber) {
		super(startNumber);
		
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


		Distance distance = firstDistance();
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
		String totalTime = firstDistance().getLapTimeString();
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
		if (firstDistance() == null) {
			Distance distance = new Distance();
			distance.addStartTime(racerTime);
			distanceList.add(distance);
		} else {
			firstDistance().addStartTime(racerTime);
		}
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		if (firstDistance() == null) {
			Distance distance = new Distance();
			distance.addFinishTime(racerTime);
			distanceList.add(distance);
		} else {
			firstDistance().addFinishTime(racerTime);
		}
	}
}
