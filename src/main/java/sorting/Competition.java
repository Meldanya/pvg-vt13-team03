package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import racer.AbstractRacer;
import racer.RacerClass;
import racer.RacerFactory;
import racer.RacerTime;

/**
 * Container for Map with all Racers
 */
public class Competition {
	private RacerFactory factory;
	private Map<String, AbstractRacer> abstractRacers;

	/**
	 * Creates a new TreeMap
	 * @param factory TODO
	 */
	public Competition(RacerFactory factory) {
		this.factory = factory;
		abstractRacers = new TreeMap<String, AbstractRacer>();
	}

	/**
	 * Adds a racer
	 * 
	 * @param abstractRacer
	 */
	private void addRacer(AbstractRacer abstractRacer) {
		abstractRacers.put(abstractRacer.getStartNumber(), abstractRacer);
	}

	public void setNames(Map<String, String> names) {
		String currentClass = "";

		for (String startNumber : names.keySet()) {
			// Kontrollerar att raden är ett startnummer
			if (Character.isDigit(startNumber.charAt(0))) {
				AbstractRacer abstractRacer = abstractRacers.get(startNumber);
				if (abstractRacer == null) {
					abstractRacer = factory.makeRacer(startNumber);
					addRacer(abstractRacer);
				}

				abstractRacer.setName(names.get(startNumber));
				abstractRacer.setClassType(currentClass);
			} else {
				// In this case the start number is the class name
				currentClass = startNumber;
			}
		}
		identifyNonExistingRacers(names);
	}

	/**
	 * @param names
	 */
	private void identifyNonExistingRacers(Map<String, String> names) {
		for (String startNumber : abstractRacers.keySet()) {
			AbstractRacer abstractRacer = abstractRacers.get(startNumber);

			if (!names.keySet().contains(startNumber)) {
				abstractRacer.setClassType("Icke existerande startnummer");
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
	public void writeToFile(String filename, int laps, Comparator<AbstractRacer> comp) {
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
		AbstractRacer abstractRacer = getReferenceToRacer(startNumber);
		for (String time : times) {
			abstractRacer.addStartTime(new RacerTime(time));
		}
	}

	private void addFinishTimestoRacer(String startNumber, ArrayList<String> times) {
		AbstractRacer abstractRacer = getReferenceToRacer(startNumber);
		for (String time : times) {
			abstractRacer.addFinishTime(new RacerTime(time));
		}
		abstractRacer.sortFinishTimes();
	}

	private AbstractRacer getReferenceToRacer(String startNumber) {
		AbstractRacer abstractRacer = abstractRacers.get(startNumber);
		if (abstractRacer == null) {
			abstractRacer = factory.makeRacer(startNumber);
			addRacer(abstractRacer);
		}
		return abstractRacer;
	}

	/**
	 * Gets a set of all the registered classes
	 * 
	 * @return
	 */
	public Set<RacerClass> getClassTypes() {
		Set<RacerClass> set = new TreeSet<RacerClass>();

		for (String key : abstractRacers.keySet()) {
			AbstractRacer r = abstractRacers.get(key);

			set.add(r.getClassType());
		}

		return set;
	}

	public Set<AbstractRacer> getRacers(RacerClass rc, Comparator<AbstractRacer> comp) {
		TreeSet<AbstractRacer> set = new TreeSet<AbstractRacer>(comp);

		for (String key : abstractRacers.keySet()) {
			AbstractRacer r = abstractRacers.get(key);

			if (r.getClassType().equals(rc)) {
				set.add(r);
			}
		}
		
		return set;
	}
}
