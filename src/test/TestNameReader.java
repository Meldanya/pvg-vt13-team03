package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.NameReader;

public class TestNameReader {

	private NameReader reader;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reader = new NameReader();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testReader() throws IOException {

		Map<String, String> tempMap = new TreeMap<String, String>();
		tempMap.put("StartNo", "Namn");
		tempMap.put("1", "Anders Asson");
		tempMap.put("2", "Bengt Bsson");
		tempMap.put("3", "Chris Csson");
		tempMap.put("4", "David Dsson");
		tempMap.put("5", "Erik Esson");

		Map<String, String> map = reader.readFromNameFile("test/TestNameReader/namnfil.txt");

		for (String s : map.keySet()) {
			assertEquals(tempMap.get(s), map.get(s));
		}

	}

	@Test (expected=ArrayIndexOutOfBoundsException.class)
	public void testIncorrectlyFormattedFileInsufficientColumns() throws IOException {
		assertNull(reader.readFromNameFile("test/TestNameReader/incorrectstart.txt"));
	}

}
