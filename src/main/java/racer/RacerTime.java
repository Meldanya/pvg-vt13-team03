package racer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

	public RacerTime(String inputTime) {
		this();

		if (inputTime.indexOf('.') < 0) {
			inputTime = format(Long.parseLong(inputTime));
		}

		try {
			time = format.parse(inputTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return format.format(time);
	}

	public static String format(long duration) {
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

	public long computeLapTime(RacerTime other){
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
	
	public long getTime(){
		return time.getTime();
	}
}
