package racer;


public class CircuitRacer extends AbstractRacer {
	public CircuitRacer(String startNumber) {
		super(startNumber);
	}

	/**
	 * 
	 * @param laps
	 * @return
	 */
	public String racerSpecificString(int laps) {
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
			sb.append(lap.getLapTimeString());
		}
		
		//Den första starttiden:
		sb.append("; ");
		sb.append(firstDistance().getStartTime());

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
		return firstDistance().timeFromStartToOtherStart(lastDistance());
	}

	private Distance firstDistance() {
		return distanceList.get(0);
	}
	
	private Distance lastDistance() {
		return distanceList.get(distanceList.size()-1);
	}


	@Override
	public int getNumberOfLaps() {
		//-1 because addFinishTime should always start a new distance:
		return distanceList.size() - 1 ; 
	}

	@Override
	public void addStartTime(RacerTime racerTime) {
		lastDistance().addStartTime(racerTime);
	}

	@Override
	public void addFinishTime(RacerTime racerTime) {
		lastDistance().addFinishTime(racerTime);
		distanceList.add(new Distance());
		addStartTime(racerTime); //"Start" the next lap immediately
	}
}
