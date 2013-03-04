package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerFactory;
import racer.RacerRankingComparator;
import racer.RacerTime;

public class TestRacerPlacingComparator {
	private AbstractRacer racer1;
	private AbstractRacer racer2;
	private RacerFactory factory;
	private RacerRankingComparator comp;
	
	@Before
	public void setUp() {
		factory = new RacerFactory("circuit", "00.15.00");
		racer1 = factory.makeRacer("1");
		racer2 = factory.makeRacer("2");
		comp = new RacerRankingComparator();
	}
	
	@Test
	public void testDifferentNoOfLaps() throws Exception{
		racer1.addStartTime(new RacerTime("12.00.00"));
		racer1.addFinishTime(new RacerTime("12.30.00"));

		
		racer2.addStartTime(new RacerTime("12.00.00"));
		racer2.addFinishTime(new RacerTime("12.30.00"));
		racer2.addFinishTime(new RacerTime("13.00.00"));
		
		int compInt = comp.compare(racer1, racer2);
		assertTrue("Racer 2 was before Racer 1, compInt was " + compInt, compInt < 0);
	}

	@Test
	public void testSameNoOfLapsDifferentTimes() throws Exception {
		racer1.addStartTime(new RacerTime("12.00.00"));
		racer1.addFinishTime(new RacerTime("12.30.00"));
		
		racer2.addStartTime(new RacerTime("12.00.00"));
		racer2.addFinishTime(new RacerTime("12.40.00"));
		
		int compInt = comp.compare(racer1, racer2);
		assertTrue("Racer 2 was before Racer 1, compInt was " + compInt, compInt < 0);
	}

}
