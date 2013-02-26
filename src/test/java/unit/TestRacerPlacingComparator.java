package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerFactory;
import racer.RacerPlacingComparator;

public class TestRacerPlacingComparator {
	private AbstractRacer racer1;
	private AbstractRacer racer2;
	private RacerFactory factory;
	private RacerPlacingComparator comp;
	
	@Before
	public void setUp() {
		factory = new RacerFactory("circuit");
		racer1 = factory.createRacer("1");
		racer2 = factory.createRacer("2");
		comp = new RacerPlacingComparator();
	}
	
	@Test
	public void testDifferentNoOfLaps() {
		racer1.addStartTime("12.00.00");
		racer1.addFinishTime("12.30.00");
		
		racer2.addStartTime("12.00.00");
		racer2.addFinishTime("12.30.00");
		racer2.addFinishTime("13.00.00");
		
		int compInt = comp.compare(racer1, racer2);
		assertTrue("Racer 2 was before Racer 1, compInt was " + compInt, compInt < 0);
	}
	
	@Test
	public void testEqual() {
		racer1.addStartTime("12.00.00");
		racer1.addFinishTime("12.30.00");
		racer2.addStartTime("12.00.00");
		racer2.addFinishTime("12.30.00");
		
		assertEquals("racers not equal", 0, comp.compare(racer1, racer2));
	}
	
	@Test
	public void testSameNoOfLapsDifferentTimes() {
		racer1.addStartTime("12.00.00");
		racer1.addFinishTime("12.30.00");
		
		racer2.addStartTime("12.00.00");
		racer2.addFinishTime("12.40.00");
		
		int compInt = comp.compare(racer1, racer2);
		assertTrue("Racer 2 was before Racer 1, compInt was " + compInt, compInt < 0);
	}

}
