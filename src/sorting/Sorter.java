package sorting;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import racer.Racer;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	private RacerMap racers;
	private int laps;
	
	public Sorter() {
		racers = new RacerMap();
		
		String laps = JOptionPane.showInputDialog("Fyll i önskat antal varv, 1 för maratontävling");
		this.laps = Integer.parseInt(laps);
		
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
		Map<String, String> names = new NameReader().readFromNameFile("namnfil.txt");
		String currentClass = "";

		names.remove("StartNo");

		for (String s : names.keySet()) {
			// Kontrollerar att raden är ett startnummer
			if (Character.isDigit(s.charAt(0))) {
				try {
					Racer racer = racers.getRacer(s);
					
					racer.setName(names.get(s));
					racer.setClassType(currentClass);
				} catch (NoSuchElementException e) {
					// Om racern inte finns definerad så hoppas den över
					continue;
				}
			}
			else {
				currentClass = s;
			}
		}
	}

	private void write() {
		racers.writeToFile("result.txt", laps);
	}

	public static void main(String[] args) {
		new Sorter();
	}
}
