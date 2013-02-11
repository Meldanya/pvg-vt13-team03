/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import racer.RacerClass;
import sorting.Competition;

public class TestCompetition {
	private Competition competition;
	private Map<String, String> nameMappings;

	@Before
	public void setUp() throws Exception {
		competition = new Competition();
		nameMappings = new HashMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyCompetition() {
		Set<Racer> racers = competition.getRacers(new RacerClass(""));
		assertEquals("wrong number of racers", 0, racers.size());
	}

	@Test
	public void testSetAndGetNames() {
		nameMappings.put("1", "Kalle");

		competition.setNames(nameMappings);

		Set<Racer> racers = competition.getRacers(new RacerClass(""));
		
		assertEquals("wrong number of racers", 1, racers.size());
	}

}
