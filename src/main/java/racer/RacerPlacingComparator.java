package racer;

import java.util.Comparator;

public class RacerPlacingComparator implements Comparator<AbstractRacer> {

	@Override
	public int compare(AbstractRacer o1, AbstractRacer o2) {
		// upp och ner-komparator!
		if (o1.getNumberOfLaps() == o2.getNumberOfLaps()) {
			// samma antal varv eller maratontävling
			return o1.getTotalTime().compareTo(o2.getTotalTime());
		} else {
			return o2.getNumberOfLaps() - o1.getNumberOfLaps();
		}
	}

}
