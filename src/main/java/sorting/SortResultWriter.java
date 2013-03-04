package sorting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import racer.AbstractRacer;
import racer.RacerTime;

public class SortResultWriter extends Writer {
	private RacerTime timeStartIsOpen;

	public SortResultWriter(Competition data, String filename,
			Comparator<AbstractRacer> comp, String timeStartIsOpen) {
		super(data, filename, comp);
		try {
			this.timeStartIsOpen = new RacerTime(timeStartIsOpen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getHeader(int laps) {
		String header;
		if (laps < 2) {
			header = "Plac; StartNr; Namn; TotalTid; Starttid; Måltid\n";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Plac; StartNr; Namn; #Varv; TotalTid; ");

			for (int i = 1; i <= laps; i++) {
				sb.append("Varv");
				sb.append(i);
				sb.append("; ");
			}

			sb.delete(sb.length() - 2, sb.length()); // ta bort sista '; '
			sb.append('\n');
			header = sb.toString();
		}
		return header;
	}

	@Override
	protected String formatRacers(Set<AbstractRacer> racers, int laps,
			boolean includeAbsoluteTimes) {
		StringBuilder sb = new StringBuilder();
		int rank = 1;
		ArrayList<AbstractRacer> unranked = new ArrayList<AbstractRacer>();
		for (AbstractRacer racer : racers) {
			try {
				RacerTime totalTime = new RacerTime(racer.getTotalTime());
				if (totalTime.compareTo(timeStartIsOpen) >= 0) {
					sb.append(rank);
					sb.append("; ");
					sb.append(racer.racerString(laps, includeAbsoluteTimes));
					sb.append('\n');
					rank++;
				} else {
					unranked.add(racer);
				}
			} catch (ParseException e) {
				unranked.add(racer);
			}

		}
		for (AbstractRacer racer : unranked) {
			sb.append("; ");
			sb.append(racer.racerString(laps, includeAbsoluteTimes));
			sb.append('\n');
		}
		return sb.toString();
	}

}
