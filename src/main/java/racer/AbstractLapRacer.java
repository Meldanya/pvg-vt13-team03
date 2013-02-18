package racer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLapRacer extends AbstractRacer {
	protected List<Distance> distanceList;

	public AbstractLapRacer(String startNumber) {
		super(startNumber);
		distanceList = new ArrayList<Distance>();
	}

	public void addStartTime(String string) {
		for (int i = 0; i < distanceList.size(); i++) {
			Distance distance = distanceList.get(i);

			if (distance.getStartTime() == null) {
				distance.setStartTime(string);

				return;
			}
		}

		Distance dist = new Distance();

		dist.setStartTime(string);
		distanceList.add(dist);
	}

	public void addFinishTime(String string) {
		for (int i = 0; i < distanceList.size(); i++) {
			Distance distance = distanceList.get(i);

			if (distance.getFinishTime() == null) {
				distance.setFinishTime(string);

				return;
			}
		}

		Distance dist = new Distance();

		dist.setFinishTime(string);
		distanceList.add(dist);
	}
}