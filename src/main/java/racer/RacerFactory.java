package racer;

public class RacerFactory {
	private String raceType;
	
	public RacerFactory(String raceType) {
		this.raceType = raceType.toLowerCase();
	}
	
	public AbstractRacer makeRacer(String startNumber) {
		if (raceType.equals("circuit")) {
			return new CircuitRacer(startNumber);
		}
		
		return new MarathonRacer(startNumber);
	}
}
