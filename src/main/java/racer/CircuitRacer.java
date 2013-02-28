package racer;

import java.util.ArrayList;
import java.util.List;

public class CircuitRacer extends AbstractRacer {
	protected List<Distance> distanceList;
	public CircuitRacer(String startNumber) {
		super(startNumber);
		distanceList = new ArrayList<Distance>();
		distanceList.add(new Distance());
	}

	/**
	 * @param maxLapCount
	 */
	@Override
	public void appendRacerSpecificString(StringBuilder sb,
			int maxLapCount, boolean includeAbsoluteTimes) {
		appendStatistics(sb);

		appendLapTimes(sb, maxLapCount);

		if (includeAbsoluteTimes) {
			appendAbsoluteTimes(sb, maxLapCount);
		}		
	}

	private void appendStatistics(StringBuilder sb) {
		sb.append(getNumberOfDistances());
		sb.append("; ");
		sb.append(getTotalTime());
	}

	private void appendLapTimes(StringBuilder sb, int maxLapCount) {
		for (int lap = 0; lap < maxLapCount; lap++) {
			sb.append("; ");
			if (lap < distanceList.size() - 1) {
				String lapTime = distanceList.get(lap).getLapTimeString();
				sb.append(lapTime);
			}
		}
	}

	protected void appendErrorMessages(StringBuilder sb) {
		sb.append(firstDistance().possibleMultipleStartTimes());

		for (Distance lap : distanceList) {
			sb.append(lap.possibleImpossibleTime("Omöjlig varvtid?"));
		}
	}

	private void appendAbsoluteTimes(StringBuilder sb, int maxLapCount) {
		// Den första starttiden:
		sb.append("; ");
		sb.append(firstDistance().startTimeString());

		for (int lap = 0; lap < maxLapCount; lap++) {
			sb.append("; ");
			if (lap < distanceList.size() - 1) {
				String finishTime = distanceList.get(lap).finishTimeString();
				sb.append(finishTime);
			}
		}

		if (distanceList.size() == 1) {
			sb.append("Slut?");
		}
		removeTrailingSpace(sb);
	}

	private void removeTrailingSpace(StringBuilder sb) {
		int indexOfLastChar = sb.length() - 1;
		if (sb.charAt(indexOfLastChar) == ' ') {
			sb.deleteCharAt(indexOfLastChar);
		}
	}

	@Override
	public String getTotalTime() {
		long lapTime = firstDistance()
				.timeFromStartToOtherStart(lastDistance());
		return RacerTime.formatDuration(lapTime);
	}

	private Distance lastDistance() {
		return distanceList.get(distanceList.size() - 1);
	}

	@Override
	public int getNumberOfDistances() {
		// -1 because addFinishTime should always start a new distance:
		int numberOfDistances = distanceList.size() - 1;
		if (!firstDistance().finishTimeString().equals("Slut?"))
			if (firstDistance().startTimeString().equals("Start?"))
				numberOfDistances--;
		return numberOfDistances;
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
				newDistance.addStartTime(new RacerTime(currentDistance
						.getStartTime()));
				newDistance.addFinishTime(racerTime);
				distanceList.add(lap, newDistance);

				Distance temp = new Distance();
				temp.addStartTime(racerTime);
				temp.addFinishTime(new RacerTime(currentFinishTime));
				distanceList.set(lap + 1, temp);
				return;
			}
		}
		// The new time should be added at the end:
		lastDistance().addFinishTime(racerTime);
		newDistance.addStartTime(racerTime);
		distanceList.add(newDistance);
	}

	@Override
	public String startTimeString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Distance firstDistance(){
		return distanceList.get(0);
	}
}
