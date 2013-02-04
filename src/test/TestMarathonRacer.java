package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.MarathonRacer;
import racer.Racer;
import racer.RacerTime;

public class TestMarathonRacer {
	private MarathonRacer racer;

	@Before
	public void setUp() throws Exception {
		racer = new MarathonRacer("1");
		racer.setName("Kalle");
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testNoTimes() {
		assertEquals("Start time exists", "Starttid?", racer.getStartTime());
		assertEquals("Finish time exists", "Sluttid?", racer.getFinishTime());
	}

	@Test
	public void testTimeFormatting() {
		racer.addStartTime(new RacerTime("11.12.13"));
		assertEquals("Formatting is wrong", "1; Kalle; --.--.--; 11.12.13; Sluttid?", racer.toString());
	}

	@Test
	public void testEquals() {
		MarathonRacer racer2 = new MarathonRacer("1");
		assertEquals("Racers not equal", racer, racer2);
	}
	
	@Test
	public void testNotEquals() {
		MarathonRacer racer2 = new MarathonRacer("2");
		assertFalse("Racers equal", racer.equals(racer2));
	}
}
