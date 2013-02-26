package racer;

import java.util.concurrent.TimeUnit;

public class TimeUnitFormatter {
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
}
