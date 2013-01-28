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
	
	/**
	 * Has some strange behaviors, needs to be fixed
	 * @param milliseconds
	 * @return
	 */
	private String formatString(long milliseconds) {
		int seconds;
		int hours;
		int minutes;
		
		seconds = (int)(milliseconds / 1000);
		hours = seconds / 3600;
		minutes = (seconds - hours * 60) / 60;
		seconds -= hours * 3600 + minutes * 60;
		
		return hours + "." + minutes + "." + seconds;
	}

	public String getDifferenceTo(RacerTime compare) {
		long difference = compare.time.getTime() - time.getTime();
		
		return formatString(difference);
	}
	
	public static void main(String[] args) {
		RacerTime rt1 = new RacerTime("13.49.00");
		RacerTime rt2 = new RacerTime("13.50.20");
		
		System.out.println(rt1.getDifferenceTo(rt2));
	}
}
