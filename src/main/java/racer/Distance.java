package racer;

import java.util.ArrayList;
import java.util.List;

public class Distance {

	private List<RacerTime> startTimes, finishTimes;
	

	public Distance() {
		startTimes = new ArrayList<RacerTime>();
		finishTimes = new ArrayList<RacerTime>();
	}
	
	public String toString(){
		String start = startString();
		String finish = finishString();
		
		return startTimes.get(0).getDifferenceTo(finishTimes.get(0)) + "; " + start + "; " + finish;	
	}

	private String startString() {
		if (startTimes.size() == 0){
			return "Start?";
		} else if (startTimes.size() == 1 ){
			return startTimes.get(0).toString();
		} else {
			return "Flera starttider?";
		}
	}
	private String finishString() {
		int finishTimesSize = finishTimes.size();
		if (finishTimesSize == 0){
			return "Slut?";
		} else if (finishTimesSize == 1 ){
			return finishTimes.get(0).toString();
		} else {
			return "Flera måltider?";
		}
	}
	public void addStartTime(String string) {
		startTimes.add(new RacerTime(string));
	}
	
	public void addFinishTime(String string) {
		finishTimes.add(new RacerTime(string));
	}

	public RacerTime getLapTime(){
		RacerTime lapTime = startTimes.get(0).computeLapTime(finishTimes.get(0));
		return lapTime;
	}
}
