package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import racer.AbstractRacer;
import racer.CircuitRacer;
import racer.MarathonRacer;
import racer.RacerFactory;

public class TestRacerFactory {
	RacerFactory factory;
	AbstractRacer racer;

	@Test
	public void testCircuitRacer() {
		factory = new RacerFactory("circuit", "00.15.00");
		racer = new CircuitRacer("42", "00.15.00");

		assertEquals("RacerFactory doesn't return CircuitRacer", racer, factory.makeRacer("42"));
	}

	@Test
	public void testMarathonRacer() {
		factory = new RacerFactory("marathon", "00.15.00");
		racer = new MarathonRacer("42", "00.15.00");

		assertEquals("RacerFactory doesn't return MarathonRacer", racer, factory.makeRacer("42"));
	}

	@After
	public void tearDown() throws Exception {
		factory = null;
		racer = null;
	}
}
