package racer;

import java.util.Comparator;

public class RacerRankingComparator implements Comparator<AbstractRacer> {

	@Override
	public int compare(AbstractRacer o1, AbstractRacer o2) {
		// ???
		int o1laps = o1.getNumberOfDistances();
		int o2laps = o2.getNumberOfDistances();
		String o1total = o1.getTotalTime();
		String o2total = o2.getTotalTime();
		if (o1laps == o2laps) {
			// samma antal varv eller maratontävling
			int comparedResult = o1total.compareTo(o2total);			
			return comparedResult == 0 ? 1 : comparedResult;
		} else {
			return o2laps - o1laps;
		}
	}

}
