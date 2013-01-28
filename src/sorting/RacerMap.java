package sorting;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import racer.Racer;

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

	public void addStartTime(int id, int startTime) {
		try {
			Racer r = getRacer(id);
			r.setStartTime(startTime);
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:1 " + e.getMessage());
		}
	}

	public void addFinishTime(int id, int finishTime) {
		try {
			Racer r = getRacer(id);
			r.setFinishTime(finishTime);
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:2 " + e.getMessage());
		}

	}

	public int getResult(int id) {

		int totalTime = 0;
		try {
			Racer r = getRacer(id);
			totalTime = r.getTotalTime();
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:3 " + e.getMessage());
		}
		return totalTime;
	}

	public Map<Integer, Racer> toMap() {
		return map;
	}

}
