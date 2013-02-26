package unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import racer.TimeUnitFormatter;

public class TestTimeUnitFormatter {
	private TimeUnitFormatter formatter;

	@Before
	public void setUp() {
		formatter = new TimeUnitFormatter();
	}

	@Test
	public void testFormat1Second() {
		assertEquals("Time incorrectly formatted, 1 second", "00.00.01", formatter.format(1000));
	}
	@Test
	public void testFormat1Minute(){
		assertEquals("Time incorrectly formatted, 1 minute", "00.01.00", formatter.format(60*1000));
	}
	@Test
	public void testFormat1Hour(){
		assertEquals("Time incorrectly formatted, 1 hour", "01.00.00", formatter.format(60*60*1000));
	}
	
	@Test
	public void testFormatArbitraryTime(){
		assertEquals("Time incorrectly formatted, 1 hour", "25.52.28", formatter.format(25*60*60*1000+52*60*1000+28*1000 ));
	}
}
