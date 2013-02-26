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
		assertEquals("Diff not correct", 1000, racerTime.computeLapTime(compare));
	}

	@Test
	public void testComplexCompareTo() {
		RacerTime compare = new RacerTime("22.13.14");
		assertEquals("Diff not correct", 11*60*60*1000+60*1000+1000, racerTime.computeLapTime(compare));
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
	@Test
	public void testFormat1Second() {
		assertEquals("Time incorrectly formatted, 1 second", "00.00.01", racerTime.format(1000));
	}
	@Test
	public void testFormat1Minute(){
		assertEquals("Time incorrectly formatted, 1 minute", "00.01.00", racerTime.format(60*1000));
	}
	@Test
	public void testFormat1Hour(){
		assertEquals("Time incorrectly formatted, 1 hour", "01.00.00", racerTime.format(60*60*1000));
	}
	
	@Test
	public void testFormatArbitraryTime(){
		assertEquals("Time incorrectly formatted, 1 hour", "25.52.28", racerTime.format(25*60*60*1000+52*60*1000+28*1000 ));
	}
}
