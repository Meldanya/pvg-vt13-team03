package racer;


public class MarathonRacer extends AbstractRacer {

	private Distance distance;
	
	public MarathonRacer(String startNumber) {
		super(startNumber);
		// TODO Auto-generated constructor stub
	}
	
	public String getStartTime() {
		return distance.getStartTime().toString();
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
		String finishTime = null;
		if (finishTimes.size() <= 1) {
	
			finishTime = getTotalTime();
	
		} else {
			finishTime = startTimes.get(0).getDifferenceTo(finishTimes.get(0));
		}
		sb.append(finishTime);
		sb.append("; ");
		sb.append(distance.getStartTime().toString());
		sb.append("; ");
		if (finishTimes.size() <= 1) {
			sb.append(getFinishTime());
		} else {
			sb.append(finishTimes.get(0).toString());
			sb.append("; Flera måltider?");
			for (int i = 1; i < finishTimes.size(); i++) {
				sb.append(" ");
				sb.append(finishTimes.get(i));
			}
	
		}
		if (startTimes.size() > 1) {
			sb.append("; Flera starttider?");
			for (int i = 1; i < startTimes.size(); i++) {
				sb.append(" ");
				sb.append(startTimes.get(i));
			}
		}
	
		// Makes sure that finishTime is not "--.--.--"
		if(!finishTime.equals("--.--.--") && (new RacerTime(finishTime)).compareTo(new RacerTime("00.15.00"))<0){
			sb.append("; Omöjlig Totaltid?");
		}
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfLaps() {
		return 1;
	}
}
