package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import racer.CircuitRacer;

public class TestCircuitRacer {
	private CircuitRacer racer1, racer2;

	@Before
	public void setUp() throws Exception {
		racer1 = new CircuitRacer("1");
		racer1.setName("Kalle");
		racer2 = new CircuitRacer("2");
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testNoTimes() {
		assertEquals("Start time exists", "1; Kalle; 0; --.--.--; Start?; Slut?", racer1.toString());
	}

	@Test
	public void testTimeFormatting() {
		racer1.addStartTime("11.12.13");
		assertEquals("Formatting is wrong", "1; Kalle; 0; --.--.--; 11.12.13; Slut?", racer1.toString());
	}

	@Test
	public void testEquals() {
		racer2 = new CircuitRacer("1");
		assertEquals("Racers not equal", racer1, racer2);
	}

	@Test
	public void testNotEquals() {
		assertFalse("Racers equal", racer1.equals(racer2));
	}

	@Test
	public void testAddOneLapTime() {
		racer1.addStartTime("11.11.14");
		racer1.addFinishTime("11.12.14");
		assertEquals(1, racer1.getNumberOfLaps());
	}

	@Test
	public void testAddManyLapTimes() {
		for (int i = 0; i < 10; i++) {
			racer1.addStartTime("11.11." + i);
			racer1.addFinishTime("11.12." + i);
		}
		assertEquals(10, racer1.getNumberOfLaps());
	}
	@Ignore
	@Test
	public void testFinishTime() {
		for (int i = 0; i < 10; i++) {

			racer1.addFinishTime("11.12." + i);
		}
//		assertEquals(racer1.getFinishTime(), "11.12.09");
	}

	@Test
	public void testNumberOfLapsZero() {
		racer1.addStartTime("12.00.00");
		assertEquals("Wrong number of laps. Should be zero.", 0, racer1.getNumberOfLaps());
	}

	@Test
	public void testNumberOfLapsOne() {
		racer1.addStartTime("12.00.00");
		racer1.addFinishTime("12.15.00");
		assertEquals("Wrong number of laps. Should be one.", 1, racer1.getNumberOfLaps());
	}

	@Test
	public void testNumberOfLapsThree() {
		racer1.addStartTime("12.00.00");
		racer1.addFinishTime("12.15.00");
		racer1.addFinishTime("12.30.00");
		racer1.addFinishTime("12.45.00");
		assertEquals("Wrong number of laps. Should be three.", 3, racer1.getNumberOfLaps());
	}

}
