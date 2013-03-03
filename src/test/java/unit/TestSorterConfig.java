package unit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.SorterConfig;

public class TestSorterConfig {

	private SorterConfig conf;

	@Before
	public void setUp() throws Exception {
		conf = new SorterConfig();
	}

	@After
	public void tearDown() throws Exception {
		conf = null;
	}

	@Test
	public void testSetDefaultConfig() throws IOException {
		conf.setDefaults();
		
		assertEquals("namnfil.txt", conf.get("Namefile"));
		assertEquals("start.txt", conf.get("StartFiles"));
	}

	@Test
	public void testStoreConfig() throws IOException {
		conf.setDefaults();
		conf.store();

		conf = new SorterConfig();
		conf.load();
		
		assertEquals("namnfil.txt", conf.get("Namefile"));
		assertEquals("start.txt", conf.get("StartFiles"));
	}

}
