package racer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLapRacer extends AbstractRacer {
	protected List<Distance> distanceList; 
	public AbstractLapRacer(String startNumber) {
		super(startNumber);
		distanceList = new ArrayList<Distance>();
	}

}