package registration;

import java.util.HashSet;

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

	public boolean addStartTime(int id, int startTime) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addFinishTime(int id, int finishTime) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getResult(int id) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	

}
