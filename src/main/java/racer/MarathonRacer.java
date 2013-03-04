package racer;

public class MarathonRacer extends AbstractRacer {
	private Distance distance;

	public MarathonRacer(String startNumber) {
		super(startNumber);
		distance = new Distance();
	}

	/* @see
	 * racer.AbstractRacer#appendRacerSpecificString(java.lang.StringBuilder,
	 * int, boolean)
	 */
	@Override
	protected void appendRacerSpecificString(StringBuilder sb, int maxLapCount) {
		sb.append(getTotalTime());
		sb.append("; ");
		
	}

	@Override
	protected void appendAbsoluteTimes(StringBuilder sb, int maxLapCount) {
		sb.append(distance.startTimeString());
		sb.append("; ");
		sb.append(distance.finishTimeString());
	}

	@Override
	protected void appendErrorMessages(StringBuilder sb) {
		sb.append(distance.possibleMultipleFinishTimes());

		sb.append(distance.possibleMultipleStartTimes());

		sb.append(distance.possibleImpossibleTime("Omöjlig Totaltid?"));
	}

	@Override
	public String getTotalTime() {
		String totalTime = distance.getLapTimeString();
		if (totalTime.equals("")) {
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
