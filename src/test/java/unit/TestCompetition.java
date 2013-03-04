/**
 * 
 */
package unit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerClass;
import racer.RacerFactory;
import sorting.Competition;

public class TestCompetition {
	private Competition competition;
	private Map<String, String> nameMappings;

	@Before
	public void setUp() throws Exception {
		competition = new Competition(new RacerFactory("marathon", "00.15.00"));
		nameMappings = new HashMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyCompetition() {
		Set<AbstractRacer> abstractRacers = competition.getRacers(new RacerClass(""), null);
		assertEquals("wrong number of racers", 0, abstractRacers.size());
	}

	@Test
	public void testSetAndGetNames() {
		nameMappings.put("1", "Kalle");

		competition.setNames(nameMappings);

		Set<AbstractRacer> abstractRacers = competition.getRacers(new RacerClass(""), null);
		
		assertEquals("wrong number of racers", 1, abstractRacers.size());
	}

}
