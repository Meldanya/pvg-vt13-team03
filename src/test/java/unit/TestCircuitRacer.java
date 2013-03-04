package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.CircuitRacer;
import racer.RacerTime;

public class TestCircuitRacer {
	private AbstractRacer racer1, racer2;

	@Before
	public void setUp() throws Exception {
		racer1 = new CircuitRacer("1", "00.15.00");
		racer1.setName("Kalle");
		racer2 = new CircuitRacer("2", "00.15.00");
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testNoTimes() {
		assertEquals("Start time exists", "1; Kalle; 0; --.--.--; ; Start?; Slut?", racer1.racerString(1, true));
	}

	@Test
	public void testTimeFormatting() throws Exception{
		racer1.addStartTime(new RacerTime("11.12.13"));
		assertEquals("Formatting is wrong", "1; Kalle; 0; --.--.--; ; 11.12.13; Slut?", racer1.racerString(1, true));
	}

	@Test
	public void testEquals() {
		racer2 = new CircuitRacer("1", "00.15.00");
		assertEquals("Racers not equal", racer1, racer2);
	}

	@Test
	public void testNotEquals() {
		assertFalse("Racers equal", racer1.equals(racer2));
	}

	@Test
	public void testAddOneLapTime() throws Exception{
		racer1.addStartTime(new RacerTime("11.11.14"));
		racer1.addFinishTime(new RacerTime("11.12.14"));
		assertEquals(1, racer1.getNumberOfDistances());
	}

	@Test
	public void testAddManyLapTimes() throws Exception{
		for (int i = 0; i < 10; i++) {
			racer1.addStartTime(new RacerTime("11.11." + i));
			racer1.addFinishTime(new RacerTime("11.12." + i));
		}
		assertEquals(10, racer1.getNumberOfDistances());
	}

	@Test
	public void testNumberOfLapsZero() throws Exception{
		racer1.addStartTime(new RacerTime("12.00.00"));
		assertEquals("Wrong number of laps. Should be zero.", 0, racer1.getNumberOfDistances());
	}

	@Test
	public void testNumberOfLapsOne()throws Exception {
		racer1.addStartTime(new RacerTime("12.00.00"));
		racer1.addFinishTime(new RacerTime("12.15.00"));
		assertEquals("Wrong number of laps. Should be one.", 1, racer1.getNumberOfDistances());
	}

	@Test
	public void testNumberOfLapsThree()throws Exception {
		racer1.addStartTime(new RacerTime("12.00.00"));
		racer1.addFinishTime(new RacerTime("12.15.00"));
		racer1.addFinishTime(new RacerTime("12.30.00"));
		racer1.addFinishTime(new RacerTime("12.45.00"));
		assertEquals("Wrong number of laps. Should be three.", 3, racer1.getNumberOfDistances());
	}
	
	@Test
	public void testGetTotalTime()throws Exception{
		racer1.addStartTime(new RacerTime("12.00.00"));
		racer1.addFinishTime(new RacerTime("12.30.00"));
		
		assertEquals("Total time incorrect", "00.30.00",racer1.getTotalTime());
	}
}
