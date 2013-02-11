package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import racer.RacerPlacingComparator;
import racer.Racer;
import racer.RacerTime;

public class TestRacerPlacingComparator {
	private Racer r1;
	private Racer r2;
	private RacerPlacingComparator comp;
	
	@Before
	public void setUp() {
		r1 = new Racer("1");
		r2 = new Racer("2");
		comp = new RacerPlacingComparator();
	}
	
	@Test
	public void testDifferentNoOfLaps() {
		r1.addStartTime(new RacerTime("12.00.00"));
		r1.addFinishTime(new RacerTime("12.30.00"));
		r2.addStartTime(new RacerTime("12.00.00"));
		r2.addFinishTime(new RacerTime("12.30.00"));
		r2.addFinishTime(new RacerTime("13.00.00"));
		
		assertTrue("r2 not greater than r1", comp.compare(r1, r2) < 0);
	}
	
	@Test
	public void testEqual() {
		r1.addStartTime(new RacerTime("12.00.00"));
		r1.addFinishTime(new RacerTime("12.30.00"));
		r2.addStartTime(new RacerTime("12.00.00"));
		r2.addFinishTime(new RacerTime("12.30.00"));
		
		assertEquals("racers not equal", 0, comp.compare(r1, r2));
	}
	
	@Test
	public void testSameNoOfLaps() {
		r1.addStartTime(new RacerTime("12.00.00"));
		r1.addFinishTime(new RacerTime("12.30.00"));
		r2.addStartTime(new RacerTime("12.00.00"));
		r2.addFinishTime(new RacerTime("12.40.00"));
		
		assertTrue("r1 not greater than r2", comp.compare(r1, r2) > 0);
	}

}
