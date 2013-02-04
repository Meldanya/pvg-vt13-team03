package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.LapRacer;
import racer.Racer;
import racer.RacerTime;

public class TestLapRacer {
	private LapRacer racer;

	@Before
	public void setUp() throws Exception {
		racer = new LapRacer("1");
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
		Racer racer2 = new LapRacer("1");
		assertEquals("Racers not equal", racer, racer2);
	}
	
	@Test
	public void testNotEquals() {
		Racer racer2 = new LapRacer("2");
		assertFalse("Racers equal", racer.equals(racer2));
	}
	
	@Test
	public void testAddOneLapTime(){
		racer.addFinishTime(new RacerTime("11.12.14"));
		assertEquals(1, racer.getNumberOfLaps());
	}
	
	@Test
	public void testAddManyLapTimes(){
		for(int i = 0; i<10; i++){
			
			racer.addFinishTime(new RacerTime("11.12." + i));
		}
		assertEquals(10, racer.getNumberOfLaps());
	}
	
	@Test
	public void testFinishTime(){
	for(int i = 0; i<10; i++){
			
			racer.addFinishTime(new RacerTime("11.12." + i));
		}
		assertEquals(racer.getFinishTime(),"11.12.09");
	}
	
//	@Test
//	public void testNumberOfLaps(){
//		
//	}
}
