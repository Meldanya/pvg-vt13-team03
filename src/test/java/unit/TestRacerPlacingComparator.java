package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerFactory;
import racer.RacerPlacingComparator;
import racer.RacerTime;

public class TestRacerPlacingComparator {
	private AbstractRacer r1;
	private AbstractRacer r2;
	private RacerFactory factory;
	private RacerPlacingComparator comp;
	
	@Before
	public void setUp() {
		factory = new RacerFactory("circuit");
		r1 = factory.createRacer("1");
		r2 = factory.createRacer("2");
		comp = new RacerPlacingComparator();
	}
	
	@Test
	public void testDifferentNoOfLaps() {
		r1.addStartTime("12.00.00");
		r1.addFinishTime("12.30.00");
		
		r2.addStartTime("12.00.00");
		r2.addFinishTime("12.30.00");
		r2.addFinishTime("13.00.00");
		
		assertTrue("r1 not greater than r2", comp.compare(r1, r2) == 0);
	}
	
	@Test
	public void testEqual() {
		r1.addStartTime("12.00.00");
		r1.addFinishTime("12.30.00");
		r2.addStartTime("12.00.00");
		r2.addFinishTime("12.30.00");
		
		assertEquals("racers not equal", 0, comp.compare(r1, r2));
	}
	
	@Test
	public void testSameNoOfLaps() {
		r1.addStartTime("12.00.00");
		r1.addFinishTime("12.30.00");
		
		r2.addStartTime("12.00.00");
		r2.addFinishTime("12.40.00");
		
		assertTrue("r2 not greater than r1", comp.compare(r1, r2) == 0);
	}

}
