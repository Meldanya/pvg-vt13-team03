package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import racer.Racer;
import racer.RacerClass;
import racer.RacerTime;

/**
 * Container for Map with all Racers
 */
public class Competition {
	private Map<String, Racer> racers;

	/**
	 * Creates a new TreeMap
	 */
	public Competition() {
		racers = new TreeMap<String, Racer>();
	}

	/**
	 * Adds a racer
	 * 
	 * @param racer
	 */
	private void addRacer(Racer racer) {
		racers.put(racer.getStartNumber(), racer);
	}
	
	public void setNames(Map<String, String> names) {
		String currentClass = "";
		
		for (String startNumber : names.keySet()) {
			// Kontrollerar att raden är ett startnummer
			if (Character.isDigit(startNumber.charAt(0))) {
				Racer racer = racers.get(startNumber);
				if (racer == null) {
					racer = new Racer(startNumber);
					addRacer(racer);
				}
				
				racer.setName(names.get(startNumber));
				racer.setClassType(currentClass);
			}
			else {
				// In this case the start number is the class name
				currentClass = startNumber;
			}
		}
	}

	/**
	 * Writes the current map to a file, passes responsibility to ResultWriter
	 * 
	 * @param filename
	 *            File to write to
	 * @param laps
	 *            Amount of specified laps
	 */
	public void writeToFile(String filename, int laps) {
		ResultWriter writer = new ResultWriter(this, filename);

		writer.writeToFile(laps);
	}
	
	/**
	 * Reads start/finish times from a file and loads it into Racers,
	 * creates new Racers to store the data in where necessary.
	 * 
	 * @param fileName
	 */
	public void readFromFile(String fileName, boolean start) throws IOException{
		TimeReader reader = new TimeReader();
		Map<String, ArrayList<String>> timesMap = reader
				.readFromTimeFile(fileName);
		
		for (String startNumber : timesMap.keySet()) {
			if (startNumber.trim().length() < 1) {
				// Makes sure that the read line is not empty
				continue;
			}

			Racer racer = new Racer(startNumber);

			if (racers.containsKey(startNumber)) {
				racer = racers.get(startNumber);
			} else {
				addRacer(racer);
			}

			ArrayList<String> times = timesMap.get(startNumber);

			for (String time : times) {
				if (start) {
					racer.addStartTime(new RacerTime(time));
				} else {
					racer.addFinishTime(new RacerTime(time));
				}
			}
		}
	}

	/**
	 * Gets a set of all the registered classes
	 * 
	 * @return
	 */
	public Set<RacerClass> getClassTypes() {
		Set<RacerClass> set = new TreeSet<RacerClass>();

		for (String key : racers.keySet()) {
			Racer r = racers.get(key);

			set.add(r.getClassType());
		}

		return set;
	}

	/**
	 * Gets a set of all the Racers with the specified class
	 * 
	 * @param rc
	 *            The specified class
	 * @return
	 */
	public Set<Racer> getRacers(RacerClass rc) {
		Set<Racer> set = new TreeSet<Racer>();

		for (String key : racers.keySet()) {
			Racer r = racers.get(key);

			if (r.getClassType().equals(rc)) {
				set.add(r);
			}
		}

		return set;
	}
}
