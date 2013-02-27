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

	private Distance distance, distanceNoTimes;

	@Before
	public void setUp() throws Exception {
		distance = new Distance();
		distance.addStartTime(new RacerTime("11.00.00"));
		distance.addFinishTime(new RacerTime("12.00.00"));
		
		distanceNoTimes = new Distance();
	}
	
	@Test
	public void testGetStartTime(){
		assertEquals("Wrong start time", "11.00.00", distance.startTimeString());
	}
	
	@Test
	public void testGetStartTimeNoStartTime(){
		assertEquals("Wrong start time", "Start?", distanceNoTimes.startTimeString());
	}
	
	@Test
	public void testGetFinishTime(){
		assertEquals("Wrong finish time", "12.00.00", distance.finishTimeString());
	}
	@Test
	public void testGetFinishTimeNoFinishTime(){
		assertEquals("Wrong finish time", "Slut?", distanceNoTimes.finishTimeString());
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
	
	@Test
	public void testPossibleImpossibleTotalTimeIsEmpty(){
		assertEquals("Clutter in empty impossible totatl time", "",distance.possibleImpossibleTotalTime());
	}
	
	@After
	public void tearDown(){
		distance = null;
	}
}
