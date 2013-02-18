package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerTime;

public class TestCircuitRacer {
	private AbstractRacer abstractRacer;

	@Before
	public void setUp() throws Exception {
		abstractRacer = new AbstractRacer("1");
		abstractRacer.setName("Kalle");
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testNoTimes() {
		assertEquals("Start time exists", "Start?", abstractRacer.getStartTime());
		assertEquals("Finish time exists", "Slut?", abstractRacer.getFinishTime());
	}

	@Test
	public void testTimeFormatting() {
		abstractRacer.addStartTime(new RacerTime("11.12.13"));
		assertEquals("Formatting is wrong", "1; Kalle; --.--.--; 11.12.13; Slut?", abstractRacer.toString());
	}

	@Test
	public void testEquals() {
		AbstractRacer racer2 = new AbstractRacer("1");
		assertEquals("Racers not equal", abstractRacer, racer2);
	}
	
	@Test
	public void testNotEquals() {
		AbstractRacer racer2 = new AbstractRacer("2");
		assertFalse("Racers equal", abstractRacer.equals(racer2));
	}
	
	@Test
	public void testAddOneLapTime(){
		abstractRacer.addStartTime(new RacerTime("11.11.14"));
		abstractRacer.addFinishTime(new RacerTime("11.12.14"));
		assertEquals(1, abstractRacer.getNumberOfLaps());
	}
	
	@Test
	public void testAddManyLapTimes(){
		for(int i = 0; i<10; i++){
			abstractRacer.addStartTime(new RacerTime("11.11." + i));
			abstractRacer.addFinishTime(new RacerTime("11.12." + i));
		}
		assertEquals(10, abstractRacer.getNumberOfLaps());
	}
	
	@Test
	public void testFinishTime(){
	for(int i = 0; i<10; i++){
			
			abstractRacer.addFinishTime(new RacerTime("11.12." + i));
		}
		assertEquals(abstractRacer.getFinishTime(),"11.12.09");
	}
	
	@Test
	public void testNumberOfLapsZero(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		assertEquals("Wrong number of laps. Should be zero.",0,abstractRacer.getNumberOfLaps());
	}
	
	@Test
	public void testNumberOfLapsOne(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		abstractRacer.addFinishTime(new RacerTime("12.15.00"));
		assertEquals("Wrong number of laps. Should be one.",1,abstractRacer.getNumberOfLaps());
	}
	
	@Test
	public void testNumberOfLapsThree(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		abstractRacer.addFinishTime(new RacerTime("12.15.00"));
		abstractRacer.addFinishTime(new RacerTime("12.30.00"));
		abstractRacer.addFinishTime(new RacerTime("12.45.00"));
		assertEquals("Wrong number of laps. Should be three.",3,abstractRacer.getNumberOfLaps());
	}
	
	@Test
	public void testLapTimesZero(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		assertEquals("Lap times existed when the shouldn't",new ArrayList<String>(),abstractRacer.getLapTimes());
	}
	
	@Test
	public void testLaptimesOne(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		abstractRacer.addFinishTime(new RacerTime("12.15.00"));
		assertEquals("Lap time did not add upp for on lap.","00.15.00",abstractRacer.getLapTimes().get(0));
	}
	
	@Test
	public void testLaptimesThree(){
		abstractRacer.addStartTime(new RacerTime("12.00.00"));
		abstractRacer.addFinishTime(new RacerTime("12.15.00"));
		abstractRacer.addFinishTime(new RacerTime("12.35.00"));
		abstractRacer.addFinishTime(new RacerTime("13.00.00"));
		assertEquals("Lap time did not add upp for lap one.","00.15.00",abstractRacer.getLapTimes().get(0));
		assertEquals("Lap time did not add upp for lap two.","00.20.00",abstractRacer.getLapTimes().get(1));
		assertEquals("Lap time did not add upp for lap three.","00.25.00",abstractRacer.getLapTimes().get(2));
	}
}
