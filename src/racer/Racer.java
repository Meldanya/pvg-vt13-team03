package racer;

import java.util.ArrayList;

/**
 * A class representing a racer (aka driver) with a start number, start time and
 * finish time.
 */
public class Racer implements Comparable<Racer> {

	private RacerClass classType;
	private String startNumber;
	private ArrayList<RacerTime> startTimes;
	protected ArrayList<RacerTime> finishTimes;
	private String name;

	public Racer(String startNumber) {
		this.classType = new RacerClass("");
		this.startNumber = startNumber;
		this.startTimes = new ArrayList<RacerTime>();
		this.finishTimes = new ArrayList<RacerTime>();
		this.name = "";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public String getStartTime() {
		if (startTimes.size() > 0) {
			return startTimes.get(0).toString();
		}

		return "Start?";
	}

	public void addStartTime(RacerTime startTime) {
		this.startTimes.add(startTime);
	}

	public String getFinishTime() {
		if (finishTimes.size() > 0) {
			return finishTimes.get(finishTimes.size() - 1).toString();
		}

		return "Slut?";
	}

	public void addFinishTime(RacerTime finishTime) {
		this.finishTimes.add(finishTime);
	}

	public String getTotalTime() {
		if (startTimes.size() < 1 || finishTimes.size() < 1) {
			return "--.--.--";
		}

		RacerTime startTime = startTimes.get(0);
		RacerTime finishTime = finishTimes.get(finishTimes.size() - 1);

		return startTime.getDifferenceTo(finishTime);
	}

	/**
	 * Returns the racer as a line in the format the Sorter wants. Is only run
	 * if laps = 1
	 */
	@Override
	public String toString() {
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
		sb.append(getStartTime());
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

	/**
	 * 
	 * @param laps
	 * @return
	 */
	public String toString(int laps) {
		if (laps == 1) {
			return toString();
		}

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

		out.append("; " + getStartTime());

		for (int i = 0; i < laps; i++) {
			try {
				RacerTime laptime = finishTimes.get(i);
				out.append("; " + laptime.toString());
			} catch (IndexOutOfBoundsException e) {
				// Laptime doesn't exist, print column anyway
				if (i == (laps - 1) ) {
					out.append("; Slut?");
				} else {
					out.append("; ");
				}
			}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((startNumber == null) ? 0 : startNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racer other = (Racer) obj;
		if (startNumber == null) {
			if (other.startNumber != null)
				return false;
		} else if (!startNumber.equals(other.startNumber))
			return false;
		return true;
	}

	public RacerClass getClassType() {
		return classType;
	}

	public void setClassType(RacerClass classType) {
		this.classType = classType;
	}

	public void setClassType(String className) {
		this.classType = new RacerClass(className);
	}

	@Override
	public int compareTo(Racer o) {
		return startNumber.compareTo(o.getStartNumber());
	}

	public ArrayList<String> getLapTimes() {
		ArrayList<String> lapTimes = new ArrayList<String>();

		if (finishTimes.size() > 0) {
			if (startTimes.size() > 0) {
				String lapOne = startTimes.get(0).getDifferenceTo(
						finishTimes.get(0));
				lapTimes.add(lapOne);
			} else {
				lapTimes.add("");
			}
			for (int i = 1; i < finishTimes.size(); i++) {
				String lapTime = finishTimes.get(i - 1).getDifferenceTo(
						finishTimes.get(i));

				lapTimes.add(lapTime);
			}
		}

		return lapTimes;
	}

	public int getNumberOfLaps() {
		int laps = finishTimes.size();

		// Subtract one lap if the racer lacks a start time
		laps -= startTimes.size() > 0 ? 0 : 1;

		return laps;
	}

}
