package unit;

import static org.junit.Assert.assertEquals;

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
		
		distance.addStartTime("11.00.00");
		distance.addFinishTime("12.00.00");
		
		assertEquals("Formatting is wrong", "01.00.00; 11.00.00; 12.00.00", distance.toString());
	}

}
