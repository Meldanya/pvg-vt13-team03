package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

import racer.AbstractRacer;
import racer.RacerClass;

public abstract class Writer {

	private BufferedWriter writer;
	protected Competition data;
	protected String fileName;
	protected Comparator<AbstractRacer> comparator;

	// TODO: klass utan modifierbart tillstånd?
		public Writer(Competition data, String filename, Comparator<AbstractRacer> comp) {
			this.data = data;
			this.fileName = filename;
			this.comparator = comp;
		}

	/**
	 * Loads and sorts racers before printing them to a file. Actual formatting
	 * is found in Racer class.
	 * @param includeAbsoluteTimes TODO
	 */
	public void writeToFile(int laps, boolean includeAbsoluteTimes) {
		Set<RacerClass> classes = data.getClassTypes();

		try {
			writer = new BufferedWriter(new FileWriter(fileName));

			for (RacerClass racerClass : classes) {
				writeClassTypeToFile(racerClass, laps, includeAbsoluteTimes);
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("File " + fileName + " could not be written");
		}
	}

	private void writeClassTypeToFile(RacerClass racerClass, int laps, boolean includeAbsoluteTimes)
			throws IOException {
		Set<AbstractRacer> racers = data.getRacers(racerClass, comparator);

		if (racerClass.toString().length() > 0) {
			writer.write(racerClass.toString());
			writer.newLine();
		}

		// Ser till så att den inte skriver ut fler varv än nödvändigt
		int maxLapCount = 0;
		for (AbstractRacer racer : racers) {
			if (maxLapCount < racer.getNumberOfDistances()) {
				maxLapCount = racer.getNumberOfDistances();
			}
		}

		if (maxLapCount > 0 && maxLapCount < laps) {
			laps = maxLapCount;
		}

		writer.write(getHeader(laps));
		writer.write(formatRacers(racers, laps, includeAbsoluteTimes));
		
	}
	
	protected abstract String getHeader(int laps);

	protected abstract String formatRacers(Set<AbstractRacer> racers, int laps, boolean includeAbsoluteTimes);
}