package unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.LapRacer;
import racer.RacerTime;

public class TestLapRacer {

	private AbstractRacer lapRacer;

	@Before
	public void setUp() throws Exception {
		lapRacer = new LapRacer("1");
		lapRacer.setName("Ander Asson");
	}

	@Test
	public void testTimeFormatting() {
		lapRacer.addStartTime(new RacerTime("11.00.00"));
		lapRacer.addFinishTime(new RacerTime("12.00.00"));
	
		
		assertEquals("Formatting is wrong", "1; Ander Asson; 01.00.00; 1; 01.00.00; 11.00.00; 12.00.00", lapRacer.toString());
	}
}
