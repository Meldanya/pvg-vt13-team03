package sorting;

import java.util.Map;

import racer.Racer;
import racer.RacerTime;

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
		//sort();
		write();
	}

	private void read() {
		racers.readFromFile("start.txt", "finish.txt");
	}
	
	private void readNames(){
		Map<String, String> names = new Reader().readFromFile("namnfil.txt");
		names.remove("StartNo");
		
		for(String s : names.keySet()){
			racers.setName(Integer.parseInt(s), names.get(s));
		}
		
	}

	/**
	 * The method that performs the reading and sorting.
	 */
	private void sort() {
	}
	

	private void write() {
		racers.writeToFile("result.txt");
	}


	public static void main(String[] args) {
		new Sorter();
	}
}
