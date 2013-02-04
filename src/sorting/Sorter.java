package sorting;

import java.util.Map;

import racer.Racer;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	private RacerMap racers;
	
	public Sorter() {
		racers = new RacerMap();
		
		read();
		readNames();
		write();
	}

	private void read() {
		racers.readFromFile("start.txt", "finish.txt");
	}

	/**
	 * @todo kolla vad första raden innehåller istället.
	 * @todo skicka in en Map<id, namn> till RacerMap istället
	 */
	private void readNames() {
		Map<String, String> names = new Reader().readFromFile("namnfil.txt");
		String currentClass = "";

		names.remove("StartNo");

		for (String s : names.keySet()) {
			// Kontrollerar att raden är ett startnummer
			if (Character.isDigit(s.charAt(0))) {
				Racer racer = racers.getRacer(names.get(s));
				
				racer.setName(names.get(s));
				racer.setClassType(currentClass);
			}
			else {
				currentClass = s;
			}
		}
	}

	private void write() {
		racers.writeToFile("result.txt");
	}

	public static void main(String[] args) {
		new Sorter();
	}
}
