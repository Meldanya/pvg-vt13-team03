package racer;

import java.util.ArrayList;

public class CircuitRacer extends AbstractRacer {
	public CircuitRacer(String startNumber) {
		super(startNumber);
	}

	/**
	 * 
	 * @param laps
	 * @return
	 */
	public String toString() {
		// Skriv om - skriv rätt => Tester kommer gå igenom
		StringBuilder sb = new StringBuilder();
		sb.append(startNumber);
		sb.append("; ");
		sb.append(name);
		sb.append("; ");
		sb.append(getNumberOfLaps());
		sb.append("; ");
		sb.append(getTotalTime());

		// Varvtider:
		for (Distance lap: distanceList) {
			sb.append("; ");
			sb.append(lap.getLapTimeString());
		}

		sb.append("; ");
		sb.append(distanceList.get(0).getStartTime());

		for (int lap=1; lap < distanceList.size(); lap++) {
			sb.append("; ");
			sb.append(distanceList.get(lap).getStartTime().toString());
		}
		
		if (distanceList.size() == 1) {
			sb.append("; Slut?");
		}
		for (Distance lap: distanceList) {
			sb.append(lap.possibleImpossibleTotalTime());
		}		

		return sb.toString();
	}

	@Override
	public String getTotalTime() {
		return distanceList.get(0).timeFromStartToOtherStart(distanceList.get(distanceList.size()-1));
	}

	@Override
	public int getNumberOfLaps() {
		//-1 because addFinishTime should always start a new distance:
		return distanceList.size() - 1 ; 
	}

	@Override
	public void addStartTime(RacerTime racerTime) {
		distanceList.get(distanceList.size()-1).addStartTime(racerTime);
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		distanceList.get(distanceList.size()-1).addFinishTime(racerTime);
		distanceList.add(new Distance());
		addStartTime(racerTime); //"Start" the next lap immediately
	}
}
