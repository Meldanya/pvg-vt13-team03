package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import racer.Racer;
import racer.RacerClass;
import racer.RacerTime;

/** Container for Map with all Racers */
public class Competition {
	private Map<String, Racer> racers;

	/** Creates a new TreeMap */
	public Competition() {
		racers = new TreeMap<String, Racer>();
	}

	/** Adds a racer
	 * 
	 * @param racer */
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
			} else {
				// In this case the start number is the class name
				currentClass = startNumber;
			}
		}
		identifyNonExistingRacers(names);
	}

	/** @param names */
	private void identifyNonExistingRacers(Map<String, String> names) {
		for (String startNumber : racers.keySet()) {
			Racer racer = racers.get(startNumber);

			if (!names.keySet().contains(startNumber)) {
				racer.setClassType("Icke existerande startnummer");
			}
		}

	}

	/** Writes the current map to a file, passes responsibility to ResultWriter
	 * 
	 * @param filename File to write to
	 * @param laps Amount of specified laps */
	public void writeToFile(String filename, int laps, Comparator<Racer> comp) {
		ResultWriter writer = new ResultWriter(this, filename, comp);

		writer.writeToFile(laps);
	}

	/** Reads start times from a file and loads it into Racers, creates new
	 * Racers to store the data in where necessary.
	 * 
	 * @param fileName
	 * @throws IOException */
	public void setStartTimesFromFile(String fileName) throws IOException {
		Map<String, ArrayList<String>> timesMap = readTimesFromFile(fileName);
		for (String startNumber : timesMap.keySet()) {
			if (startNumber.trim().length() >= 1) {
				addStartTimestoRacer(startNumber, timesMap.get(startNumber));
			}
		}
	}

	/** Reads finish times from a file and loads it into Racers, creates new
	 * Racers to store the data in where necessary.
	 * 
	 * @param fileName
	 * @throws IOException */
	public void setFinishTimesFromFile(String fileName) throws IOException {
		Map<String, ArrayList<String>> timesMap = readTimesFromFile(fileName);
		for (String startNumber : timesMap.keySet()) {
			if (startNumber.trim().length() >= 1) {
				addFinishTimestoRacer(startNumber, timesMap.get(startNumber));
			}
		}
	}

	private Map<String, ArrayList<String>> readTimesFromFile(String fileName) throws IOException {
		TimeReader reader = new TimeReader();
		return reader.readFromTimeFile(fileName);
	}

	private void addStartTimestoRacer(String startNumber, ArrayList<String> times) {
		Racer racer = getReferenceToRacer(startNumber);
		for (String time : times) {
			racer.addStartTime(new RacerTime(time));
		}
	}

	private void addFinishTimestoRacer(String startNumber, ArrayList<String> times) {
		Racer racer = getReferenceToRacer(startNumber);
		for (String time : times) {
			racer.addFinishTime(new RacerTime(time));
		}
		racer.sortFinishTimes();
	}

	private Racer getReferenceToRacer(String startNumber) {
		Racer racer = racers.get(startNumber);
		if (racer == null) {
			racer = new Racer(startNumber);
			addRacer(racer);
		}
		return racer;
	}

	/** Gets a set of all the registered classes
	 * 
	 * @return */
	public Set<RacerClass> getClassTypes() {
		Set<RacerClass> set = new TreeSet<RacerClass>();

		for (String key : racers.keySet()) {
			Racer r = racers.get(key);

			set.add(r.getClassType());
		}

		return set;
	}

	public Set<Racer> getRacers(RacerClass rc, Comparator<Racer> comp) {
		TreeSet<Racer> set = new TreeSet<Racer>(comp);

		for (String key : racers.keySet()) {
			Racer r = racers.get(key);

			if (r.getClassType().equals(rc)) {
				set.add(r);
			}
		}

		return set;
	}
}
