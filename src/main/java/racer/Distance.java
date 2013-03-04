package racer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Distance {
	private transient List<RacerTime> startTimes, finishTimes;
	private String minTime;

	public Distance(String minTime) {
		startTimes = new ArrayList<RacerTime>();
		finishTimes = new ArrayList<RacerTime>();
		this.minTime = minTime;
	}

	public String startTimeString() {
		int startTimesSize = startTimes.size();
		if (startTimesSize == 0) {
			return "Start?";
		} else {
			return startTimes.get(0).toString();
		}
	}

	public String finishTimeString() {
		int finishTimesSize = finishTimes.size();
		if (finishTimesSize == 0) {
			return "Slut?";
		} else {
			return finishTimes.get(0).toString();
		}
	}

	public long getStartTime() {
		if (startTimes.size() > 0) {
			return startTimes.get(0).getTime();
		} else {
			return 0;
		}
	}

	public long getFinishTime() {
		if (finishTimes.size() > 0) {
			return finishTimes.get(0).getTime();
		} else {
			return 0;
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
			return Distance.formatDuration(computeRacerTime());
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

	/**
	 * @param errorMessage
	 *            TODO
	 * @return Error message if finishTime is ""
	 */
	public String possibleImpossibleTime(String errorMessage) {
		String lapTime = getLapTimeString();
		if (lapTime.equals("")) {
			return "";
		} else if (lapTime.compareTo(minTime) < 0) {
			return ("; " + errorMessage);
		}
		return "";
	}

	public long timeFromStartToOtherStart(Distance other) {
		if (startTimes.size() > 0 && finishTimes.size() > 0) {
			return startTimes.get(0).computeLapTime(other.startTimes.get(0));
		} else {
			return 0;
		}
	}

	public static String formatDuration(long duration) {
		if (duration == 0) {
			return "--.--.--";
		} else {
			StringBuilder sb = new StringBuilder();

			long hours = TimeUnit.MILLISECONDS.toHours(duration);
			duration -= hours * 60 * 60 * 1000;
			if (hours < 10) {
				sb.append('0');
			}
			sb.append(Long.toString(hours));

			sb.append('.');

			long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
			duration -= minutes * 60 * 1000;
			if (minutes < 10) {
				sb.append('0');
			}
			sb.append(Long.toString(minutes));

			sb.append('.');

			long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
			if (seconds < 10) {
				sb.append("0");
			}
			sb.append(Long.toString(seconds));

			return sb.toString();
		}
	}
}
