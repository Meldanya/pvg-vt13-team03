/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import sorting.RacerMap;

/**
 * @author dat11ge1
 * 
 */
public class TestRacerMap {
	private RacerMap map;
	private Racer testRacer1;
	private Racer testRacer2;
	private Racer testRacer3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testRacer1 = new Racer(1);
		testRacer2 = new Racer(2);
		testRacer3 = new Racer(3);
		map = new RacerMap();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmptyMap() {
		assertEquals("Map has wrong size", 0, map.size());
	}

	@Test
	public void testAddRacerToMap() {
		map.addRacerToMap(testRacer1);
		assertEquals("Map has wrong size", 1, map.size());
	}

	@Test
	public void testAddStartTime() {
		map.addRacerToMap(testRacer1);
		map.addStartTime(1, 1000);
		assertEquals("Wrong start time", 1000, map.getRacer(1).getStartTime());
	}

	@Test
	public void testAddFinishTime() {
		map.addRacerToMap(testRacer1);
		map.addFinishTime(1, 2000);
		assertEquals("Wrong finish time", 2000, map.getRacer(1).getFinishTime());

	}

	@Test
	public void testGetResult() {
		map.addRacerToMap(testRacer1);
		map.addStartTime(1, 1000);
		map.addFinishTime(1, 2000);
		assertEquals("Wrong result", 1000, map.getResult(1));
	}
	
	@Test
	public void testGetRacer() {
		map.addRacerToMap(testRacer1);
		assertSame("Wrong racer returned", testRacer1, map.getRacer(1));
	}

}
