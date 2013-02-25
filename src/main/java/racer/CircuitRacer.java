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

		for (Distance lap: distanceList) {
			sb.append("; ");
			sb.append(lap.getLapTime().toString());
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
		RacerTime startTime = new RacerTime(distanceList.get(0).getStartTime());
		RacerTime finishTime = new RacerTime(distanceList.get(distanceList.size()-1).getStartTime());
		String totalTime = finishTime.getDifferenceTo(startTime);
		return totalTime;
		//Vi måste lägga till funktionalitet för att jämföra 2 RacerTimes. Sista med första i detta fallet
	}

	@Override
	public int getNumberOfLaps() {
		//-1 because addFinishTime should always start a new distance:
		return distanceList.size() - 1 ; 
	}

	@Override
	public void addStartTime(RacerTime racerTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		// TODO Auto-generated method stub
		
	}
}
