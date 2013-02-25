package racer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
			inputTime = formatString(Long.parseLong(inputTime));
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

	private String formatString(long milliseconds) {
		int seconds = (int) (milliseconds / 1000);
		int hours = Math.round(seconds / 3600f);
		int minutes = (seconds - hours * 3600) / 60;
		seconds -= hours * 3600 + minutes * 60;

		return String.format("%02d.%02d.%02d", hours, minutes, seconds);
	}
	
	//test
	public RacerTime computeLapTime(RacerTime compare) {
		long difference = compare.time.getTime() - time.getTime();
		return new RacerTime(difference);
	}

	public String getDifferenceTo(RacerTime compare) {
		long difference = compare.time.getTime() - time.getTime();

		return formatString(difference);
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
