package racer;

import java.util.ArrayList;

public class LapRacer extends Racer {

	public LapRacer(String startNumber) {
		super(startNumber);
		finishTimes = new ArrayList<RacerTime>();
	}

	public int getNumberOfLaps() {
		return finishTimes.size();
	}

	public String getFinishTime() {
		if(finishTimes.size()>0)
			return finishTimes.get(finishTimes.size()-1).toString();
		else
			return "Sluttid?";
	}
}
