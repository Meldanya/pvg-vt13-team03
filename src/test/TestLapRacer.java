package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import racer.RacerTime;

public class TestLapRacer {
	private Racer racer;

	@Before
	public void setUp() throws Exception {
		racer = new Racer("1");
		racer.setName("Kalle");
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testNoTimes() {
		assertEquals("Start time exists", "Start?", racer.getStartTime());
		assertEquals("Finish time exists", "Slut?", racer.getFinishTime());
	}

	@Test
	public void testTimeFormatting() {
		racer.addStartTime(new RacerTime("11.12.13"));
		assertEquals("Formatting is wrong", "1; Kalle; --.--.--; 11.12.13; Slut?", racer.toString());
	}

	@Test
	public void testEquals() {
		Racer racer2 = new Racer("1");
		assertEquals("Racers not equal", racer, racer2);
	}
	
	@Test
	public void testNotEquals() {
		Racer racer2 = new Racer("2");
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
	
	@Test
	public void testNumberOfLapsZero(){
		racer.addStartTime(new RacerTime("12.00.00"));
		assertEquals("Wrong number of laps. Should be zero.",0,racer.getNumberOfLaps());
	}
	
	@Test
	public void testNumberOfLapsOne(){
		racer.addStartTime(new RacerTime("12.00.00"));
		racer.addFinishTime(new RacerTime("12.15.00"));
		assertEquals("Wrong number of laps. Should be one.",1,racer.getNumberOfLaps());
	}
	
	@Test
	public void testNumberOfLapsThree(){
		racer.addStartTime(new RacerTime("12.00.00"));
		racer.addFinishTime(new RacerTime("12.15.00"));
		racer.addFinishTime(new RacerTime("12.30.00"));
		racer.addFinishTime(new RacerTime("12.45.00"));
		assertEquals("Wrong number of laps. Should be three.",3,racer.getNumberOfLaps());
	}
	
	@Test
	public void testLapTimesZero(){
		racer.addStartTime(new RacerTime("12.00.00"));
		assertEquals("Lap times existed when the shouldn't",new ArrayList<String>(),racer.getLapTimes());
	}
	
	@Test
	public void testLaptimesOne(){
		racer.addStartTime(new RacerTime("12.00.00"));
		racer.addFinishTime(new RacerTime("12.15.00"));
		assertEquals("Lap time did not add upp for on lap.","00.15.00",racer.getLapTimes().get(0));
	}
	
	@Test
	public void testLaptimesThree(){
		racer.addStartTime(new RacerTime("12.00.00"));
		racer.addFinishTime(new RacerTime("12.15.00"));
		racer.addFinishTime(new RacerTime("12.35.00"));
		racer.addFinishTime(new RacerTime("13.00.00"));
		assertEquals("Lap time did not add upp for lap one.","00.15.00",racer.getLapTimes().get(0));
		assertEquals("Lap time did not add upp for lap two.","00.20.00",racer.getLapTimes().get(1));
		assertEquals("Lap time did not add upp for lap three.","00.25.00",racer.getLapTimes().get(2));
	}
}
