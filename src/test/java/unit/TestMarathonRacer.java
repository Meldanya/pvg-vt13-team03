package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.MarathonRacer;

public class TestMarathonRacer {

	private AbstractRacer marathonRacer;

	@Before
	public void setUp() throws Exception {
		marathonRacer = new MarathonRacer("1");
		marathonRacer.setName("Anders Asson");
		marathonRacer.addStartTime("12.00.00");
		marathonRacer.addFinishTime("13.23.34");
	}

	@Test
	public void testTimeFormatting() {
		assertEquals("Formatting is wrong", "1; Anders Asson; 01.23.34; 12.00.00; 13.23.34", marathonRacer.toString());
	}
	
	@After
	public void tearDown(){
		marathonRacer = null;
	}
}

