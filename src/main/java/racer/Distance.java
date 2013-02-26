package racer;

import java.util.ArrayList;
import java.util.List;

public class Distance {
	private List<RacerTime> startTimes, finishTimes;
	private String minTime;

	public Distance() {
		startTimes = new ArrayList<RacerTime>();
		finishTimes = new ArrayList<RacerTime>();
		minTime = "; " + "00.15.00";
	}

	public String toString() {
		String start = getStartTime();
		String finish = getFinishTime();

		return startTimes.get(0).getDifferenceTo(finishTimes.get(0)) + "; "
				+ start + "; " + finish;
	}

	public String getStartTime() {
		int startTimesSize = startTimes.size();
		if (startTimesSize == 0) {
			return "Start?";
		} else {
			return startTimes.get(0).toString();
		}
	}

	public String getFinishTime() {
		int finishTimesSize = finishTimes.size();
		if (finishTimesSize == 0) {
			return "Slut?";
		} else {
			return finishTimes.get(0).toString();
		}
	}

	public void addStartTime(RacerTime racerTime) {
		startTimes.add(racerTime);
	}

	public void addFinishTime(RacerTime racerTime) {
		finishTimes.add(racerTime);
	}

	private long computeRacerTime() {
		return startTimes.get(0).computeLapTime(finishTimes.get(0));
	}

	public long getLapTime() {
		if (startTimes.size() > 0 && finishTimes.size() > 0) {
			return computeRacerTime();
		} else {
			return 0;
		}
	}

	public String getLapTimeString() {
		if (startTimes.size() > 0 && finishTimes.size() > 0) {
			return "; "+RacerTime.format(computeRacerTime());
		} else {
			return "";
		}
	}

	public String possibleMultipleStartTimes() {
		StringBuilder sb = new StringBuilder();
		if (startTimes.size() > 1) {
			sb.append("; Flera starttider?");
			for (int i = 1; i < startTimes.size(); i++) {
				sb.append(" ");
				sb.append(startTimes.get(i));
			}
		}
		return sb.toString();
	}

	public String possibleMultipleFinishTimes() {
		StringBuilder sb = new StringBuilder();
		if (finishTimes.size() > 1) {
			sb.append("; Flera måltider?");
			for (int i = 1; i < finishTimes.size(); i++) {
				sb.append(" ");
				sb.append(finishTimes.get(i));
			}
		}
		return sb.toString();
	}

	/** @return Error message if finishTime is "" */
	public String possibleImpossibleTotalTime() {
		String lapTime = getLapTimeString();
		if (lapTime.equals("")){
			return "";
		} else if (lapTime.compareTo(minTime) < 0) {
			return ("; Omöjlig Totaltid?");
		}
		return "";
	}

	public String timeFromStartToOtherStart(Distance other) {
		if (startTimes.size() > 0 && finishTimes.size() > 0) {
			long lapTime = startTimes.get(0).computeLapTime(other.startTimes.get(0));
			return RacerTimeFormatter.format(lapTime);
		} else {
			return "--.--.--";
		}
	}
}
