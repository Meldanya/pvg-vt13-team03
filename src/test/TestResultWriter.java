/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import registration.ResultWriter;

/**
 * @author dat11rla
 *
 */
public class TestResultWriter {
	private Map<Integer, Racer> map;
	private ResultWriter writer;
	private String filename;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		filename = "test.txt";
		map = new HashMap<Integer, Racer>();
		writer = new ResultWriter(map, filename);
		
		deleteTestFile();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		deleteTestFile();
	}

	/**
	 * Removes testfile after it has been tested to avoid leaving crap around
	 */
	private void deleteTestFile() {
		File file = new File(filename);
		file.delete();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
