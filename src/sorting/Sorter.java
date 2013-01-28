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
		Reader reader = new Reader();
		Map<String, String> finish = reader.readFromFile("finish.txt");
		Map<String, String> start = reader.readFromFile("start.txt");
		
		for (String s : finish.keySet()) {
			Racer racer = new Racer(s);
			racer.addFinishTime(new RacerTime(finish.get(s)));
			racer.addStartTime(new RacerTime(start.get(s)));
			racers.addRacerToMap(racer);
		}

	}
	
	private void readNames(){
		Map<String, String> names = new Reader().readFromFile("namnfil.txt");
		names.remove("StartNo");
		
		for(String s : names.keySet()){
			racers.setName(s, names.get(s));
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
