package sorting;

import java.util.Set;

import racer.Racer;

public class ResultWriter extends Writer {

	public ResultWriter(Competition data, String filename) {
		super(data, filename, null);
	}

	

	@Override
	protected String formatRacers(Set<Racer> racers, int laps) {
		StringBuilder sb = new StringBuilder();
		for (Racer racer : racers) {
			sb.append(racer.toString(laps, true));
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	protected String getHeader(int laps) {
		String header;
		if (laps < 2) {
			header = "StartNr; Namn; TotalTid; StartTider; Måltider\n";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("StartNr; Namn; #Varv; TotalTid; ");
			for (int i = 1; i <= laps; i++) {
				sb.append("Varv" + i + "; ");
			}

			sb.append("Start; ");

			for (int i = 1; i < laps; i++) {
				sb.append("Varvning" + i + "; ");
			}

			sb.append("Mål");
			sb.append('\n');
			header = sb.toString();
		}
		return header;
	}
}
