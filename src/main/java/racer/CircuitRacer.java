package racer;

import java.util.ArrayList;

public class CircuitRacer extends AbstractRacer {
	public CircuitRacer(String startNumber) {
		super(startNumber);
	}

	/**
	 * 
	 * @param laps
	 * @return
	 */
	public String toString() {
		int laps = distanceList.size();
		StringBuilder out = new StringBuilder();
		ArrayList<String> lapTimes = getLapTimes();
		boolean impossibleLapTime = false;

		out.append(startNumber + "; " + name + "; " + getNumberOfLaps() + "; "
				+ getTotalTime());

		for (int i = 0; i < laps; i++) {
			try {
				String laptime = lapTimes.get(i);
				RacerTime impossible = new RacerTime("00.15.00");

				if (!laptime.equals("")
						&& (new RacerTime(laptime)).compareTo(impossible) < 0) {
					impossibleLapTime = true;
				}
				out.append("; " + laptime);

			} catch (IndexOutOfBoundsException e) {
				// Laptime doesn't exist, print column anyway
				out.append("; ");
			}
		}

		RacerTime startTime = distanceList.get(0).getStartTime();
		out.append("; "
				+ ((startTime == null) ? "Start?" : startTime.toString()));

		/*
		 * for (int i = 0; i < laps; i++) { try { RacerTime laptime =
		 * finishTimes.get(i); out.append("; " + laptime.toString()); } catch
		 * (IndexOutOfBoundsException e) { // Laptime doesn't exist, print
		 * column anyway if (i == laps - 1) { out.append(";"); } else {
		 * out.append("; "); } } }
		 */

		if (getLastLap().getFinishTime() == null) {
			out.append("; Slut?");
		}

		if (startTimes.size() > 1) {
			out.append("; Flera starttider?");
			for (int i = 1; i < startTimes.size(); i++) {
				out.append(" ");
				out.append(startTimes.get(i));
			}
		}

		if (impossibleLapTime) {
			out.append("; Omöjlig varvtid?");
		}

		return out.toString();
	}

	@Override
	public String getTotalTime() {
		if (distanceList.isEmpty()) {
			return "--.--.--";
		}
		
		Distance firstLap = getFirstLap();
		Distance lastLap = getLastLap();
		
		return firstLap.getStartTime().getDifferenceTo(lastLap.getFinishTime());
	}

	/**
	 * @return
	 */
	private Distance getFirstLap() {
		Distance firstLap = distanceList.get(0);
		return firstLap;
	}

	/**
	 * @return
	 */
	private Distance getLastLap() {
		Distance lastLap = distanceList.get(distanceList.size() - 1);
		return lastLap;
	}
}
