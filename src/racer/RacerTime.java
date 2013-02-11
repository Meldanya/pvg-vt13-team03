package racer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RacerTime {
	private Date time;
	private SimpleDateFormat format;
	
	public RacerTime() {
		time = new Date();
		format = new SimpleDateFormat("HH.mm.ss");
	}

	public RacerTime(String inputTime) {
		this();
		
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
		int seconds = (int)(milliseconds / 1000);
		int hours = seconds / 3600;
		int minutes = (seconds - hours * 3600) / 60;
		seconds -= hours * 3600 + minutes * 60;
		
		return String.format("%02d.%02d.%02d", hours, minutes, seconds);
	}

	public String getDifferenceTo(RacerTime compare) {
		long difference = compare.time.getTime() - time.getTime();
		
		return formatString(difference);
	}
	
}
