package sorting;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import racer.LapRacer;
import racer.MarathonRacer;
import racer.Racer;
import racer.RacerClass;
import racer.RacerTime;

public class RacerMap {
	private TreeMap<String, Racer> map;
	private char typeOfRace;

	public RacerMap(char typeOfRace) {
		map = new TreeMap<String, Racer>();
		this.typeOfRace= typeOfRace;
	}

	// TODO: används bara för test => ta bort. Hitta på nåt annat.
	public int size() {
		return map.size();
	}

	// TODO: döp om till addRacer
	public void addRacerToMap(Racer racer) {
		map.put(racer.getStartNumber(), racer);
	}

	// TODO: används bara internt och för test => private. Hitta på nåt annat för testen.
	public Racer getRacer(String id) {
	    // TODO: vem har sagt att vi ska ha felhantering här?
		Racer r = map.get(id);
		if (r == null) {
			throw new NoSuchElementException();
		}
		return r;
	}

	public void setName(String id, String name) {
	    // TODO: ta emot Map<id, name> istället
		try{
		Racer r = getRacer(id);
		r.setName(name);
		} catch(Exception e){
		    // TODO: varför catch Exception? Vad kan hända och vad kan inte hända?
			e.printStackTrace();
		}

	}

	public void addStartTime(String id, String startTime) {
		try {
			Racer r = getRacer(id);
			r.addStartTime(new RacerTime(startTime));
		} catch (NoSuchElementException e) {
		    // TODO: varför felhantering här?
			System.err.println("NoSuchElementException:1 " + e.getMessage());
		}
	}

	public void addFinishTime(String id, String finishTime) {
		try {
			Racer r = getRacer(id);
			r.addFinishTime(new RacerTime(finishTime));
		} catch (NoSuchElementException e) {
		    // TODO: varför felhantering här?
			System.err.println("NoSuchElementException:2 " + e.getMessage());
		}

	}

	public String getResult(String id) {
		try {
			Racer r = getRacer(id);
			return r.getTotalTime();
		} catch (NoSuchElementException e) {
		    // TODO: varför felhantering här?
			System.err.println("NoSuchElementException:3 " + e.getMessage());
		}

		return "--.--.--";
	}

	public void writeToFile(String filename) {
		ResultWriter writer = new ResultWriter(this, filename);
		writer.writeToFile();
	}
	
	public void readFromFile(String startFilename, String finishFilename) {

		TimeReader reader = new TimeReader();
		Map<String, ArrayList<String>> finish = reader.readFromTimeFile(finishFilename);
		Map<String, ArrayList<String>> start = reader.readFromTimeFile(startFilename);
		
		for (String s : finish.keySet()) {
			Racer racer=null;
			switch(typeOfRace){
			case 'm':  
				racer = new MarathonRacer(s);
				break;
			case 'l':
				racer = new LapRacer(s);
				break;
			}
			ArrayList<String> times = finish.get(s);
			for(int i = 0; i< times.size(); i++){
				racer.addFinishTime(new RacerTime(times.get(i)));
			}
			addRacerToMap(racer);
		}
		for (String s : start.keySet()) {
			Racer racer=null;
			if (map.containsKey(s)) {
				racer = map.get(s);
			} else {
				switch(typeOfRace){
				case 'm':  
					racer = new MarathonRacer(s);
					break;
				case 'l':
					racer = new LapRacer(s);
					break;
				}
				addRacerToMap(racer);
			}
			ArrayList<String> times = start.get(s);
			for(int i = 0; i< times.size(); i++){
				racer.addStartTime(new RacerTime(times.get(i)));
			}
		}
	}
	
	public Racer get(String key) {
		return map.get(key);
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}
	
	public Set<RacerClass> getClassTypes() {
		Set<RacerClass> set  = new TreeSet<RacerClass>();

		for(String key : map.keySet()) {
			Racer r = map.get(key);

			set.add(r.getClassType());
		}
		
		return set;
	}
	
	public Set<Racer> getRacers(RacerClass rc) {
		Set<Racer> set  = new TreeSet<Racer>();

		for(String key : map.keySet()) {
			Racer r = map.get(key);

			if (r.getClassType().equals(rc)) {
				set.add(r);
			}
		}
		
		return set;
	}
}
