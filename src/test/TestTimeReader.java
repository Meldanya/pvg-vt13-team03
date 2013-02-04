package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.TimeReader;

public class TestTimeReader {

	private TimeReader reader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reader = new TimeReader();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testTimeReader() {
		Map<String, ArrayList<String>> tempMap = new HashMap<String, ArrayList<String>>();
		tempMap.put("1", new ArrayList<String>());
		tempMap.get("1").add("1359359445");
		tempMap.put("2", new ArrayList<String>());
		tempMap.get("2").add("1359359446");
		tempMap.put("3", new ArrayList<String>());
		tempMap.get("3").add("1359359447");
		tempMap.put("4", new ArrayList<String>());
		tempMap.get("4").add("1359359448");
		Map<String, ArrayList<String>> map = reader.readFromTimeFile("test/TestTimeReader/start.txt");

		for(String s : map.keySet()){
			assertEquals(tempMap.get(s).size(), map.get(s).size());
		}

	}
	
	@Test
	public void testIncorrectlyFormattedFile(){
		assertEquals(reader.readFromTimeFile("test/TestTimeReader/incorrectstart.txt"), null);
	}

}
