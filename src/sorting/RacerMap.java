package sorting;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import racer.Racer;
import racer.RacerTime;

public class RacerMap {
	private TreeMap<String, Racer> map;

	public RacerMap() {
		map = new TreeMap<String, Racer>();
	}

	public int size() {
		return map.size();
	}

	public void addRacerToMap(Racer racer) {
		map.put(racer.getStartNumber(), racer);
	}

	public Racer getRacer(String id) {
		Racer r = map.get(id);
		if (r == null) {
			throw new NoSuchElementException();
		}
		return r;
	}

	public void setName(String id, String name) {

		try{
		Racer r = getRacer(id);
		r.setName(name);
		} catch(Exception e){
			e.printStackTrace();
		}

	}

	public void addStartTime(String id, String startTime) {
		try {
			Racer r = getRacer(id);
			r.addStartTime(new RacerTime(startTime));
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:1 " + e.getMessage());
		}
	}

	public void addFinishTime(String id, String finishTime) {
		try {
			Racer r = getRacer(id);
			r.addFinishTime(new RacerTime(finishTime));
		} catch (NoSuchElementException e) {
			System.err.println("NoSuchElementException:2 " + e.getMessage());
		}

	}

	public String getResult(String id) {
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
	
	public void readFromFile(String startFilename, String finishFilename) {

		Reader reader = new Reader();
		Map<String, String> finish = reader.readFromFile(finishFilename);
		Map<String, String> start = reader.readFromFile(startFilename);
		
		for (String s : finish.keySet()) {
			Racer racer = new Racer(s);
			racer.addFinishTime(new RacerTime(finish.get(s)));
			addRacerToMap(racer);
		}
		for (String s : start.keySet()) {
			Racer racer;
			if (map.containsKey(s)) {
				racer = map.get(s);
			} else {
				racer = new Racer(s);
				addRacerToMap(racer);
			}
			racer.addStartTime(new RacerTime(start.get(s)));
		}
	}
}
