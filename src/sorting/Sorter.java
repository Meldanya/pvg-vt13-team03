package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import racer.Racer;
import registration.RacerSet;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	/**
	 * The method that performs the reading and sorting.
	 */
	public void sort() {

		RacerSet racers = new RacerSet();
		Reader reader = new Reader();

		Map<String, String> finish = reader.readFromFile("finish.txt");
		Map<String, String> start = reader.readFromFile("start.txt");

		for (String s : finish.keySet()) {
			Racer racer = new Racer(Integer.parseInt(s));
			racer.setFinishTime(Integer.parseInt(finish.get(s)));
			racer.setStartTime(Integer.parseInt(start.get(s)));
			racers.addRacerToSet(racer);
		}


		try {		
			BufferedWriter resultWriter = new BufferedWriter(new FileWriter(
				"result.txt"));
			resultWriter.write("NYI");
			resultWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		new Sorter().sort();
	}
}
