package racer;

public class CircuitRacer extends AbstractRacer {
	public CircuitRacer(String startNumber) {
		super(startNumber);
	}

	/** @param maxLapCount
	 * @return */
	@Override
	public String racerSpecificString(int maxLapCount) {
		StringBuilder sb = new StringBuilder();

		sb.append("; ");
		sb.append(getNumberOfDistances());
		sb.append("; ");
		sb.append(getTotalTime());

		// Varvtider:

		for (int lap = 0; lap < maxLapCount; lap++) {
			sb.append("; ");
			String lapTime;
			if (lap < distanceList.size() - 1) {
				lapTime = distanceList.get(lap).getLapTimeString();
				if (!lapTime.equals("")) {
					sb.append(lapTime);
				}
			}
		}

		// Den första starttiden:
		sb.append("; ");
		sb.append(firstDistance().startTimeString());

		for (int lap = 1; lap < distanceList.size(); lap++) {
			sb.append("; ");
			sb.append(distanceList.get(lap).startTimeString());
		}

		if (distanceList.size() == 1) {
			sb.append("; Slut?");
		}
		for (Distance lap : distanceList) {
			sb.append(lap.possibleImpossibleTotalTime());
		}

		return sb.toString();
	}

	@Override
	public String getTotalTime() {
		long lapTime = firstDistance().timeFromStartToOtherStart(lastDistance());
		return RacerTime.format(lapTime);
	}

	private Distance lastDistance() {
		return distanceList.get(distanceList.size() - 1);
	}

	@Override
	public int getNumberOfDistances() {
		// -1 because addFinishTime should always start a new distance:
		return distanceList.size() - 1;
	}

	@Override
	public void addStartTime(RacerTime racerTime) {
		lastDistance().addStartTime(racerTime);
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		// TODO CircuitRacer needs to make sure it's distanceList is always in
		// order!
		lastDistance().addFinishTime(racerTime);
		distanceList.add(new Distance());
		addStartTime(racerTime); // "Start" the next lap immediately
	}
}
