package sorting;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import constants.FileNames;

import racer.Racer;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	private RacerMap racers;
	private int laps;
	
	public Sorter(int laps) throws IOException {
		racers = new RacerMap();
		
		this.laps = laps;
		
		read();
		readNames();
		
		write();
	}

	private void read() throws IOException {
		racers.readFromFile(FileNames.START, FileNames.FINISH);
	}

	/**
	 * @todo kolla vad första raden innehåller istället.
	 * @todo skicka in en Map<id, namn> till RacerMap istället
	 */
	private void readNames() throws IOException {
		Map<String, String> names = new NameReader().readFromNameFile(FileNames.NAMEFILE);
		String currentClass = "";

		names.remove("StartNr");

		for (String startNumber : names.keySet()) {
			// Kontrollerar att raden är ett startnummer
			if (Character.isDigit(startNumber.charAt(0))) {
				try {
					Racer racer = racers.getRacer(startNumber);
					
					racer.setName(names.get(startNumber));
					racer.setClassType(currentClass);
				} catch (NoSuchElementException e) {
					Racer racer = new Racer(startNumber);

					racer.setName(names.get(startNumber));
					racer.setClassType(currentClass);
					
					racers.addRacer(racer);
				}
			}
			else {
				// In this case the start number is the class name
				currentClass = startNumber;
			}
		}
	}

	private void write() {
		racers.writeToFile(FileNames.OUTFILE, laps);
	}
}
