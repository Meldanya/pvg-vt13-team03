package racer;

import java.util.Comparator;

public class RacerPlacingComparator implements Comparator<Racer> {

	@Override
	public int compare(Racer o1, Racer o2) {
		if (o1.getNumberOfLaps() == o2.getNumberOfLaps()) {
			// samma antal varv eller maratontävling
			return o2.getTotalTime().compareTo(o1.getTotalTime());
		} else {
			return o1.getNumberOfLaps() - o2.getNumberOfLaps();
		}
	}

}
