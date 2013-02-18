package racer;

public class RacerFactory {
	private String raceType;
	
	public RacerFactory(String raceType) {
		this.raceType = raceType.toLowerCase();
	}
	
	public AbstractRacer createRacer(String startNumber) {
		if (raceType.equals("circuit")) {
			return new CircuitRacer(startNumber);
		}
		
		if (raceType.equals("lap")) {
			return new LapRacer(startNumber);
		}
		
		return new MarathonRacer(startNumber);
	}
}
