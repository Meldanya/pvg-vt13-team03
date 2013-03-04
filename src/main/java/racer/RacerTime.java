package racer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RacerTime implements Comparable<RacerTime> {
	private Date time;
	private SimpleDateFormat format;

	public RacerTime() {
		time = new Date();
		format = new SimpleDateFormat("HH.mm.ss");
		format.getTimeZone().setRawOffset(0);
	}

	public RacerTime(long millisec) {
		this();
		time = new Date(millisec);
	}

	public RacerTime(String inputTime) throws ParseException {
		this();

		if (!inputTime.equals("")) {
			if (inputTime.indexOf('.') < 0) {
				inputTime = format.format(Long.parseLong(inputTime));
			}

			time = format.parse(inputTime);
		}
	}

	public String toString() {
		return format.format(time);
	}

	public long computeLapTime(RacerTime other) {
		return other.time.getTime() - time.getTime();
	}

	@Override
	public int compareTo(RacerTime other) {
		return time.compareTo(other.time);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		RacerTime other = (RacerTime) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public long getTime() {
		return time.getTime();
	}
}
