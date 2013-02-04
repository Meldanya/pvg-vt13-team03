package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import racer.Racer;
import racer.RacerClass;

public class ResultWriter {
	private RacerMap data;
	private String filename;
	private String header;
	
	// TODO: klass utan modifierbart tillstånd?
	public ResultWriter(RacerMap data, String filename) {
		this.data = data;
		this.filename = filename;
		this.header = "StartNr; Namn; TotalTid; StartTider; Måltider";
	}
	
	/**
	 * Loads and sorts racers before printing them to a file. Actual formatting
	 * is found in Racer class.
	 */
	public void writeToFile() {
		Set<RacerClass> classes = data.getClassTypes();
		
		for (RacerClass racerClass : classes) {
			writeClassTypeToFile(racerClass);
		}
	}

	/**
	 * 
	 */
	private void writeClassTypeToFile(RacerClass racerClass) {
		Set<Racer> racers = data.getRacers(racerClass);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

			writer.write(racerClass.toString());
			writer.newLine();
			writer.write(header);
			writer.newLine();
			
			for (Racer racer : racers) {
				writer.write(racer.toString());
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			System.err.println("File " + filename + " could not be written");
		}
	}
}
