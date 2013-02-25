package racer;

import java.util.Comparator;

public class RacerPlacingComparator implements Comparator<AbstractRacer> {

	@Override
	public int compare(AbstractRacer o1, AbstractRacer o2) {
		// ???
		int o1laps = o1.getNumberOfLaps();
		int o2laps = o2.getNumberOfLaps();
		String o1total = o1.getTotalTime();
		String o2total = o2.getTotalTime();
		if (o1.getNumberOfLaps() == o2.getNumberOfLaps()) {
			// samma antal varv eller maratontävling
			return o1.getTotalTime().compareTo(o2.getTotalTime());
		} else {
			return o2.getNumberOfLaps() - o1.getNumberOfLaps();
		}
	}

}
