package unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.LapRacer;
import racer.MarathonRacer;
import racer.RacerTime;

public class TestMarathonRacer {

	private AbstractRacer marathonRacer;

	@Before
	public void setUp() throws Exception {
		marathonRacer = new MarathonRacer("1");
		marathonRacer.setName("Anders Asson");
	}

	@Test
	public void testTimeFormatting() {
		marathonRacer.addStartTime("12.00.00");
		marathonRacer.addFinishTime("13.23.34");
	
		
		assertEquals("Formatting is wrong", "1; Anders Asson; 01.23.34; 12.00.00; 13.23.34", marathonRacer.toString());
	}
}
