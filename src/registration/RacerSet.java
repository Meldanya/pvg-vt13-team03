package registration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

import racer.Racer;

public class RacerSet {
	private HashSet<Racer> set;

	public RacerSet() {
		set = new HashSet<Racer>();
	}

	public int size() {
		// TODO Auto-generated method stub
		return set.size();
	}

	public boolean addRacerToSet(Racer racer) {
		// TODO Auto-generated method stub
		return set.add(racer);
	}

	private Racer getRacer(int id) {
		Racer r = new Racer(id);
		for (Racer racer : set) {
			if (r.equals(racer)) {
				return racer;
			}
		}
		throw new NoSuchElementException();

	}

	public boolean addStartTime(int id, int startTime) {
		try {
			Racer r = getRacer(id);
			r.setStartTime(startTime);
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:1 " + e.getMessage());
		}
		return true;
	}

	public boolean addFinishTime(int id, int finishTime) {
		try {
			Racer r = getRacer(id);
			r.setFinishTime(finishTime);
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:2 " + e.getMessage());
		}
		return true;
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

	public HashMap<Integer, Racer> toHashMap() {
		return null;
	}

}
