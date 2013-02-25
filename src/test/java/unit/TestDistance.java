package unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import racer.Distance;

public class TestDistance {

	private Distance distance;

	@Before
	public void setUp() throws Exception {
		distance = new Distance();
	}
	@Test
	public void testTimeFormatting() {
		
		distance.setStartTime("11.00.00");
		distance.setFinishTime("12.00.00");
		
		assertEquals("Formatting is wrong", "01.00.00; 11.00.00; 12.00.00", distance.toString());
	}

	@Test
	public void testGetLapTime(){
		distance.setStartTime("11.00.00");
		distance.setFinishTime("12.00.00");
		assertEquals("getLapTime is wrong", 3600000, distance.getLapTime());
	}
	
	@Test
	public void testAdd(){
		
		List<Distance> distanceList = new ArrayList<Distance>();

		
		for(int i = 0;	i < 12; i++){
			Distance distance1 = new Distance();
			distance1.setStartTime("00.00.00");
			distance1.setFinishTime("00.05.00");
			distanceList.add(distance1);
		}
		
		long sum = 0;
		for(int i = 0; i < distanceList.size(); i++){
			sum += distanceList.get(i).getLapTime();
		}
			
		Distance distance3 = new Distance();
		distance3.setStartTime("00.05.00");
		distance3.setFinishTime("01.05.00");
		
		assertEquals("Inte samma längd", sum, distance3.getLapTime());
		
		
		
		
	}
}
