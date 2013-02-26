package racer;

public class MarathonRacer extends AbstractRacer {
	public MarathonRacer(String startNumber) {
		super(startNumber);
		
	}

	Distance firstDistance() {
		return distanceList.get(0);
	}

	/**
	 * Returns the racer as a line in the format the Sorter wants. Is only run
	 * if laps = 1
	 */
	@Override
	protected String racerSpecificString(int laps) {
		StringBuilder sb = new StringBuilder();
		sb.append(getTotalTime());
		sb.append("; ");


		Distance distance = firstDistance();
		sb.append(distance.getStartTime());
		sb.append("; ");
		sb.append(distance.getFinishTime());

		sb.append(distance.possibleMultipleFinishTimes());

		sb.append(distance.possibleMultipleStartTimes());

		sb.append(distance.possibleImpossibleTotalTime());

		return sb.toString();
	}

	@Override
	public String getTotalTime() {
		String totalTime = firstDistance().getLapTimeString();
		if (totalTime.equals("")){
			totalTime = "; --.--.--";
		}
		return totalTime;
	}

	@Override
	public int getNumberOfLaps() {
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
