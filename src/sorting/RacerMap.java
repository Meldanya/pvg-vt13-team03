package sorting;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import racer.Racer;
import racer.RacerTime;

public class RacerMap {
	private TreeMap<Integer, Racer> map;

	public RacerMap() {
		map = new TreeMap<Integer, Racer>();
	}

	public int size() {
		return map.size();
	}

	public void addRacerToMap(Racer racer) {
		map.put(racer.getStartNumber(), racer);
	}

	public Racer getRacer(int id) {
		Racer r = map.get(id);
		if (r == null) {
			throw new NoSuchElementException();
		}
		return r;
	}

	public void setName(int id, String name) {

		try{
		Racer r = getRacer(id);
		r.setName(name);
		} catch(Exception e){
			e.printStackTrace();
		}

	}

	public void addStartTime(int id, String startTime) {
		try {
			Racer r = getRacer(id);
			r.addStartTime(new RacerTime(startTime));
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:1 " + e.getMessage());
		}
	}

	public void addFinishTime(int id, String finishTime) {
		try {
			Racer r = getRacer(id);
			r.addFinishTime(new RacerTime(finishTime));
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:2 " + e.getMessage());
		}

	}

	public String getResult(int id) {
		try {
			Racer r = getRacer(id);
			return r.getTotalTime();
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:3 " + e.getMessage());
		}

		return "--.--.--";
	}

	public void writeToFile(String filename) {
		ResultWriter writer = new ResultWriter(map, filename);
		writer.writeToFile();
	}
}
