package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.Sorter;

public class TestSorterReader {

	private Sorter sorter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sorter = new Sorter();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test() {
		
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("1", "1359359445");
		tempMap.put("2", "1359359446");
		tempMap.put("3", "1359359447");
		tempMap.put("4", "1359359448");
	
		Map<String, String> map = sorter.readInputFile("teststart.txt");
		
		for(String s : map.keySet()){
			assertEquals(tempMap.get(s), map.get(s));
		}

	}

}
