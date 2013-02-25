package unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

		distance.addStartTime(new RacerTime("11.00.00"));
		distance.addFinishTime(new RacerTime("12.00.00"));
		assertEquals("getLapTime is wrong", 3600000, distance.getLapTime());
	}
	
	@Test
	public void testAdd(){
		
		List<Distance> distanceList = new ArrayList<Distance>();

		
		for(int i = 0;	i < 12; i++){
			Distance distance1 = new Distance();
			distance1.addStartTime(new RacerTime("00.00.00"));
			distance1.addFinishTime(new RacerTime("00.05.00"));
			distanceList.add(distance1);
		}
		
		long sum = 0;
		for(int i = 0; i < distanceList.size(); i++){
			sum += distanceList.get(i).getLapTime();
		}
			
		Distance distance3 = new Distance();
		distance3.addStartTime(new RacerTime("00.05.00"));
		distance3.addFinishTime(new RacerTime("01.05.00"));
		
		assertEquals("Inte samma längd", sum, distance3.getLapTime());
		

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
