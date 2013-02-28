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
		return RacerTime.formatDuration(lapTime);
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
		Distance newDistance = new Distance();
		for (int lap = 0; lap < distanceList.size(); lap++) {
			Distance currentDistance = distanceList.get(lap);
			long currentFinishTime = currentDistance.getFinishTime();
			long newFinishTime = racerTime.getTime();
			if (currentFinishTime > newFinishTime) {
				newDistance.addStartTime(new RacerTime(currentDistance.getStartTime()));
				newDistance.addFinishTime(racerTime);
				distanceList.add(lap, newDistance);

				Distance temp = new Distance();
				temp.addStartTime(racerTime);
				temp.addFinishTime(new RacerTime(currentFinishTime));
				distanceList.set(lap + 1, temp);
				return;
			}
		}
		lastDistance().addFinishTime(racerTime);
		newDistance.addStartTime(racerTime);
		distanceList.add(newDistance);
	}
}
