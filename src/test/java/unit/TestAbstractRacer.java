package unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.CircuitRacer;
import racer.LapRacer;
import racer.MarathonRacer;

public class TestAbstractRacer {
	private LapRacer lapRacer;
	private CircuitRacer circuitRacer;
	private AbstractRacer marathonRacer;
	
	@Before
	public void setUp() throws Exception {
		lapRacer = new LapRacer("1");
		circuitRacer = new CircuitRacer("2");
		marathonRacer = new MarathonRacer("3");

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testgetTotalTime(){
		
	}

}
