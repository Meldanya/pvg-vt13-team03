package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.MarathonRacer;

public class TestMarathonRacer {

	private AbstractRacer andersAsson, bengtBsson;

	@Before
	public void setUp() throws Exception {
		andersAsson = new MarathonRacer("1");
		andersAsson.setName("Anders Asson");
		andersAsson.addStartTime("12.00.00");
		andersAsson.addFinishTime("13.23.34");
		
		bengtBsson = new MarathonRacer("2");
		bengtBsson.setName("Bengt Bsson");
		bengtBsson.addStartTime("12.01.00");
		bengtBsson.addFinishTime("13.15.16");
	}

	@Test
	public void testTimeFormatting() {
		assertEquals("Formatting is wrong", "1; Anders Asson; 01.23.34; 12.00.00; 13.23.34", andersAsson.toString());
	}
	
	 @Test
	 public void testGetTotalTime(){
		 //TODO getTotalTime should probably not include a semicolon.
		 assertEquals("Total time is incorrect for racer 1", "; 01.23.34", andersAsson.getTotalTime());
		 assertEquals("Total time is incorrect for racer 2", "; 01.14.16", bengtBsson.getTotalTime());
	 }
	
	@After
	public void tearDown(){
		andersAsson = null;
	}
}
