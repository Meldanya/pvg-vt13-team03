/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

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
		assertEquals(true,set.addStartTime(1, 1000));
	}
	@Test
	public void testAddFinishTime() {
		assertEquals(true, set.addFinishTime(1, 2000));
		
	}
	@Test
	public void testGetResult() {
		assertEquals(1000,set.getResult(1));
		
	}
	
	}
