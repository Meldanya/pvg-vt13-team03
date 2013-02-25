package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import racer.RacerTime;

public class TestRacerTime {
	RacerTime racerTime;
	
	@Before
	public void setUp() {
		racerTime = new RacerTime("11.12.13");
	}
	
	@Test
	public void testFormat() {
		assertEquals("Time doesn't match", "11.12.13", racerTime.toString());
	}
	
	@Test
	public void testBasicCompareTo() {
		RacerTime compare = new RacerTime("11.12.14");
		assertEquals("Diff not correct", "00.00.01", racerTime.getDifferenceTo(compare));
	}

	@Test
	public void testComplexCompareTo() {
		RacerTime compare = new RacerTime("22.13.14");
		assertEquals("Diff not correct", "11.01.01", racerTime.getDifferenceTo(compare));
	}
	
	@Test
	public void testEquals(){
		RacerTime time1 = new RacerTime("11.11.11");
		RacerTime time2 = new RacerTime("11.11.11");
		
		assertEquals("RacerTimes are not equal", time1, time2);
		assertEquals("RacerTimes are not equal", 0, time1.compareTo(time2));
	}
	
	@Test
	public void testComparable() {
		RacerTime time1 = new RacerTime("11.11.11");
		RacerTime time2 = new RacerTime("11.13.11");
		
		assertTrue("racerTime not greater than time1", racerTime.compareTo(time1) > 0);
		assertTrue("racerTime not less than time2", racerTime.compareTo(time2) < 0);
	}

}
