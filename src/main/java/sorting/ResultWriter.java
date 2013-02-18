package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

import racer.AbstractRacer;
import racer.RacerClass;

public class ResultWriter {
	private BufferedWriter writer;
	private Competition data;
	private String fileName;
	private String header;
	private Comparator<AbstractRacer> comparator;

	public ResultWriter(Competition data, String filename, Comparator<AbstractRacer> comparator) {

		this.data = data;
		this.fileName = filename;
		this.header = "StartNr; Namn; TotalTid; StartTider; Måltider";
		this.comparator = comparator;
	}

	/**
	 * Loads and sorts racers before printing them to a file. Actual formatting
	 * is found in AbstractRacer class.
	 */
	public void writeToFile(int laps) {
		Set<RacerClass> classes = data.getClassTypes();

		try {
			writer = new BufferedWriter(new FileWriter(fileName));

			for (RacerClass racerClass : classes) {
				writeClassTypeToFile(racerClass, laps);
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("File " + fileName + " could not be written");
		}
	}
	private void writeClassTypeToFile(RacerClass racerClass, int laps) throws IOException {

		Set<AbstractRacer> abstractRacers = data.getRacers(racerClass, comparator);

		if (racerClass.toString().length() > 0) {
			writer.write(racerClass.toString());
			writer.newLine();
		}

		// Ser till så att den inte skriver ut fler varv än nödvändigt
		int maxLapCount = 0;
		for (AbstractRacer abstractRacer : abstractRacers) {
			if (maxLapCount < abstractRacer.getNumberOfLaps()) {
				maxLapCount = abstractRacer.getNumberOfLaps();
			}
		}

		if (maxLapCount > 0 && maxLapCount < laps) {
			laps = maxLapCount;
		}

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
		writer.write(header);

		for (AbstractRacer abstractRacer : abstractRacers) {
			writer.write(abstractRacer.toString(laps));
			writer.newLine();
		}
	}
}
