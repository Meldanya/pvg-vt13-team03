/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import racer.Racer;
import racer.RacerTime;
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
		testRacer1 = new Racer("1");
		testRacer2 = new Racer("2");
		testRacer3 = new Racer("3");
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
		map.addRacer(testRacer1);
		assertEquals("Map has wrong size", 1, map.size());
	}

	@Test
	public void testAddStartTime() {
		map.addRacer(testRacer1);
		map.getRacer("1").addStartTime(new RacerTime("10.10.10"));
		assertEquals("Wrong start time", "10.10.10", map.getRacer("1").getStartTime());
	}

	@Test
	public void testAddFinishTime() {
		map.addRacer(testRacer1);
		map.getRacer("1").addFinishTime(new RacerTime("20.20.20"));
		assertEquals("Wrong finish time", "20.20.20", map.getRacer("1").getFinishTime());
	}

	@Test
	public void testGetRacer() {
		map.addRacer(testRacer1);
		assertSame("Wrong racer returned", testRacer1, map.getRacer("1"));
	}
}
