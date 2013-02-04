package racer;

import java.util.ArrayList;

public class LapRacer extends Racer {
	private ArrayList<RacerTime> lapTimes;

	public LapRacer(String startNumber) {
		super(startNumber);
		lapTimes = new ArrayList<RacerTime>();
	}

	public void addLapTime(RacerTime lap) {
		lapTimes.add(lap);
	}

	public int getNumberOfLaps() {
		return lapTimes.size();
	}

	public String getFinishTime() {
		if(lapTimes.size()>0)
			return lapTimes.get(lapTimes.size()-1).toString();
		else
			return "Sluttid?";
	}
}
