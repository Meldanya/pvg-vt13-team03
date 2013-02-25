package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Distance;
import racer.RacerTime;

public class TestDistance {

	private Distance distance;

	@Before
	public void setUp() throws Exception {
		distance = new Distance();
		distance.addStartTime(new RacerTime("11.00.00"));
		distance.addFinishTime(new RacerTime("12.00.00"));
	}
	@Test
	public void testTimeFormatting() {
		assertEquals("Formatting is wrong", "01.00.00; 11.00.00; 12.00.00", distance.toString());
	}

	@Test
	public void testGetLapTime(){
		assertEquals("getLapTime is wrong", "01.00.00", distance.getLapTime().toString());
	}
	
	@Test
	public void testPossibleMultipleStartTimesIsEmpty(){
		assertEquals("Clutter in empty starttimes", "",distance.possibleMultipleStartTimes());
	}
	
	@Test
	public void testPossibleMultipleFinishTimesIsEmpty(){
		assertEquals("Clutter in empty finishtimes", "",distance.possibleMultipleFinishTimes());
	}
	
	@After
	public void tearDown(){
		distance = null;
	}
}
