package racer;


public class MarathonRacer extends AbstractRacer {	
	public MarathonRacer(String startNumber) {
		super(startNumber);
		// TODO Auto-generated constructor stub
	}
	
	public String getStartTime() {
		return distance().getStartTime().toString();
	}

	private Distance distance() {
		return distanceList.get(0);
	}

	/**
	 * Returns the racer as a line in the format the Sorter wants. Is only run
	 * if laps = 1
	 */
	@Override
	public String toString() {
		///// Skriv om - skriv rätt => Tester kommer gå igenom
		StringBuilder sb = new StringBuilder();
		sb.append(startNumber);
		sb.append("; ");
		sb.append(name);
		sb.append("; ");
		sb.append(getTotalTime());
		sb.append("; ");
		
		Distance distance = distance();
		
		sb.append(distance.getStartTime());
		sb.append("; ");
		sb.append(distance.getFinishTime());
		
		sb.append(distance.possibleMultipleFinishTimes());
		
		sb.append(distance.possibleMultipleStartTimes());
		
		sb.append(distance.possibleImpossibleTotalTime());
		
		
	
		return sb.toString();
	}

	@Override
	public void addStartTime(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFinishTime(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTotalTime() {
		return distance().getLapTime().toString();
	}

	@Override
	public int getNumberOfLaps() {
		return 1;
	}
}
