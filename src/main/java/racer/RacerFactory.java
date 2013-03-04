package racer;

public class RacerFactory {
	private String raceType;
	private String minTime;

	public RacerFactory(String raceType, String minTime) {
		this.minTime = minTime;
		this.raceType = raceType;
	}

	public AbstractRacer makeRacer(String startNumber) {
		if (raceType.equalsIgnoreCase("circuit")) {
			return new CircuitRacer(startNumber, minTime);
		} else {
			return new MarathonRacer(startNumber, minTime);
		}
	}
}
