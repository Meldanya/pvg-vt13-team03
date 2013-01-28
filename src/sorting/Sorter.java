package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import racer.Racer;
import registration.RacerSet;
import registration.ResultWriter;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	private RacerSet racers;
	
	public Sorter() {
		racers = new RacerSet();
		
		read();
		//sort();
		write();
	}

	private void read() {
		Reader reader = new Reader();
		Map<String, String> finish = reader.readFromFile("finish.txt");
		Map<String, String> start = reader.readFromFile("start.txt");

		for (String s : finish.keySet()) {
			Racer racer = new Racer(Integer.parseInt(s));
			racer.setFinishTime(Integer.parseInt(finish.get(s)));
			racer.setStartTime(Integer.parseInt(start.get(s)));
			racers.addRacerToSet(racer);
		}

	}

	/**
	 * The method that performs the reading and sorting.
	 */
	private void sort() {
	}
	

	private void write() {
		
		ResultWriter writer = new ResultWriter(racers.toHashMap(), "result.txt");
		writer.writeToFile();
		
	}


	public static void main(String[] args) {
		new Sorter();
	}
}
