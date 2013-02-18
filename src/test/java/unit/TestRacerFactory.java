package unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import racer.AbstractRacer;
import racer.CircuitRacer;
import racer.LapRacer;
import racer.MarathonRacer;
import racer.RacerFactory;

public class TestRacerFactory {
	RacerFactory factory;
	AbstractRacer racer;

	@Test
	public void testCircuitRacer() {
		factory = new RacerFactory("circuit");
		racer = new CircuitRacer("42");

		assertEquals("RacerFactory doesn't return CircuitRacer", racer, factory.getRacer("42"));
	}

	@Test
	public void testLapRacer() {
		factory = new RacerFactory("lap");
		racer = new LapRacer("42");

		assertEquals("RacerFactory doesn't return LapRacer", racer, factory.getRacer("42"));
	}

	@Test
	public void testMarathonRacer() {
		factory = new RacerFactory("marathon");
		racer = new MarathonRacer("42");

		assertEquals("RacerFactory doesn't return MarathonRacer", racer, factory.getRacer("42"));
	}

	@After
	public void tearDown() throws Exception {
		factory = null;
		racer = null;
	}
}
