package racer;

import sorting.SorterConfig;

public class RacerFactory {
	private String raceType;
	private String minTime;
	
	public RacerFactory(String raceType, String minTime) {
		this.minTime = minTime;
		this.raceType = raceType.toLowerCase();
	}
	
	public AbstractRacer makeRacer(String startNumber) {
		if (raceType.equals("circuit")) {
			return new CircuitRacer(startNumber, minTime);
		}
		
		return new MarathonRacer(startNumber, minTime);
	}
}
