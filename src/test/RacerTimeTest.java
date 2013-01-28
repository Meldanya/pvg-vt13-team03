package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.RacerTime;

public class RacerTimeTest {
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
}
