package unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.CircuitRacer;
import racer.MarathonRacer;

public class TestAbstractRacer {
	private CircuitRacer circuitRacer;
	private AbstractRacer marathonRacer;
	
	@Before
	public void setUp() throws Exception {
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
