package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.MarathonRacer;

public class TestMarathonRacer {

	private AbstractRacer marathonRacer1, marathonRacer2;

	@Before
	public void setUp() throws Exception {
		marathonRacer1 = new MarathonRacer("1");
		marathonRacer1.setName("Anders Asson");
		marathonRacer1.addStartTime("12.00.00");
		marathonRacer1.addFinishTime("13.23.34");
		
		marathonRacer2 = new MarathonRacer("2");
		marathonRacer2.setName("Erik Jansson");
		marathonRacer2.addStartTime("00.00.00");
		marathonRacer2.addFinishTime("13.23.34");
	}

	@Test
	public void testTimeFormatting() {
		assertEquals("Formatting is wrong", "1; Anders Asson; 01.23.34; 12.00.00; 13.23.34", marathonRacer1.toString());
	}
	
	 @Test
	 public void testGetTotalTime(){
		 //TODO getTotalTime should probably not include a semicolon.
		 assertEquals("Total time is incorrect for racer 1", "; 01.23.34",marathonRacer1.getTotalTime());
		 assertEquals("Total time is incorrect for racer 2", "; 13.23.34", marathonRacer2.getTotalTime());
	 }
	
	@After
	public void tearDown(){
		marathonRacer1 = null;
	}
}
