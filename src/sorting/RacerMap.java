package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import racer.Racer;
import racer.RacerClass;
import racer.RacerTime;

/**
 * Container for Map with all Racers
 */
public class RacerMap {
	private Map<String, Racer> map;

	/**
	 * Creates a new TreeMap
	 */
	public RacerMap() {
		map = new TreeMap<String, Racer>();
	}

	/**
	 * Returns the size of the map
	 * 
	 * @return int
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Adds a racer
	 * 
	 * @param racer
	 */
	public void addRacer(Racer racer) {
		map.put(racer.getStartNumber(), racer);
	}

	/**
	 * Returns the racer with the specified start number
	 * 
	 * @param id
	 *            The start number
	 * @return Racer
	 */
	public Racer getRacer(String id) {
		Racer r = map.get(id);

		if (r == null) {
			throw new NoSuchElementException();
		}

		return r;
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
	 * Reads starttimes and finishtimes from files and loads it into Racers,
	 * creates new Racers to store the data in where necessary.
	 * @param startFilename
	 * @param finishFilename
	 */
	public void readFromFile(String startFilename, String finishFilename) throws IOException {
		TimeReader reader = new TimeReader();
		Map<String, ArrayList<String>> finish = reader
				.readFromTimeFile(finishFilename);
		Map<String, ArrayList<String>> start = reader
				.readFromTimeFile(startFilename);

		for (String startNumber : finish.keySet()) {
			Racer racer = new Racer(startNumber);
			ArrayList<String> times = finish.get(startNumber);

			for (int i = 0; i < times.size(); i++) {
				racer.addFinishTime(new RacerTime(times.get(i)));
			}

			addRacer(racer);
		}

		for (String startNumber : start.keySet()) {
			if (startNumber.trim().length() < 1) {
				// Makes sure that the read line is not empty
				continue;
			}
			
			Racer racer = new Racer(startNumber);
			
			if (map.containsKey(startNumber)) {
				racer = map.get(startNumber);
			} else {
				addRacer(racer);
			}
			
			ArrayList<String> times = start.get(startNumber);
			
			for (String time : times) {
				racer.addStartTime(new RacerTime(time));
			}
		}
	}

	/**
	 * Gets a set of all the keys
	 * @return
	 */
	public Set<String> keySet() {
		return map.keySet();
	}

	/**
	 * Gets a set of all the registered classes
	 * @return
	 */
	public Set<RacerClass> getClassTypes() {
		Set<RacerClass> set = new TreeSet<RacerClass>();

		for (String key : map.keySet()) {
			Racer r = map.get(key);

			set.add(r.getClassType());
		}

		return set;
	}

	/**
	 * Gets a set of all the Racers with the specified class
	 * @param rc The specified class
	 * @return
	 */
	public Set<Racer> getRacers(RacerClass rc) {
		Set<Racer> set = new TreeSet<Racer>();

		for (String key : map.keySet()) {
			Racer r = map.get(key);

			if (r.getClassType().equals(rc)) {
				set.add(r);
			}
		}

		return set;
	}
}
