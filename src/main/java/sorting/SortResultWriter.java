package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import racer.Racer;
import racer.RacerTime;

public class SortResultWriter extends Writer {
	private RacerTime timeStartIsOpen;

	public SortResultWriter(Competition data, String filename,
			Comparator<Racer> comp, String timeStartIsOpen) {
		super(data, filename, comp);
		this.timeStartIsOpen = new RacerTime(timeStartIsOpen);
	}

	@Override
	protected String getHeader(int laps) {
		String header;
		if (laps < 2) {
			header = "Plac; StartNr; Namn; TotalTid\n";
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
	protected String formatRacers(Set<Racer> racers, int laps) {
		StringBuilder sb = new StringBuilder();
		int rank = 1;
		ArrayList<Racer> unranked = new ArrayList<Racer>();
		for (Racer racer : racers) {
			RacerTime totalTime = new RacerTime(racer.getTotalTime());
			if (totalTime.compareTo(timeStartIsOpen) >= 0) {
				sb.append(rank);
				sb.append("; ");
				sb.append(racer.toString(laps, false));
				sb.append('\n');
				rank++;
			} else {
				unranked.add(racer);
			}
		}
		for (Racer racer : unranked) {
			sb.append("; ");
			sb.append(racer.toString(laps, false));
			sb.append('\n');
		}
		return sb.toString();
	}

}
