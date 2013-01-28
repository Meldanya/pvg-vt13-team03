/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import registration.RacerSet;

/**
 * @author dat11ge1
 *
 */
public class TestRacerSet {
	private RacerSet set;
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
		set = new RacerSet();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptySet() {
		assertEquals(0, set.size());
	    }
	@Test
	public void testAddRacerToSet(){
		
		assertEquals(true, set.addRacerToSet(testRacer1));
	}
	
	@Test
	public void testAddStartTime() {
		set.addRacerToSet(testRacer1);
		assertEquals(true,set.addStartTime(1, 1000));
	}
	@Test
	public void testAddFinishTime() {
		set.addRacerToSet(testRacer1);
		assertEquals(true, set.addFinishTime(1, 2000));
		
	}
	@Test
	public void testGetResult() {
		set.addRacerToSet(testRacer1);
		set.addStartTime(1, 1000);
		set.addFinishTime(1, 2000);
		assertEquals(1000,set.getResult(1));
		
	}
	
	@Test
	public void testToMap() {
		set.addRacerToSet(testRacer1);
		set.addRacerToSet(testRacer2);
		set.addRacerToSet(testRacer3);
		Map<Integer, Racer> map = set.toMap();
		assertEquals(3, map.size());
	}
	
	}
