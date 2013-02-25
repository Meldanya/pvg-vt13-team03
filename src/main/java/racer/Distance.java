package racer;

public class Distance {

	private RacerTime startTime, finishTime;
	

	public Distance() {
		
	}
	
	public String toString(){
		
		String start = (startTime == null) ? "Start?" : startTime.toString();
		String finish = (finishTime == null) ? "Slut?" : finishTime.toString();
		
		return startTime.getDifferenceTo(finishTime) + "; " + start + "; " + finish;	
	}
	
	public void setStartTime(String string) {
		startTime = new RacerTime(string);
		
	}

	public void setFinishTime(String string) {
		finishTime = new RacerTime(string);
		
	}
	
	public RacerTime getStartTime() {
		return startTime;
	}

	public RacerTime getFinishTime() {
		return finishTime;
	}

	public RacerTime getLapTime(){
		RacerTime lapTime = startTime.computeLapTime(finishTime);
		return lapTime;
	}
}
