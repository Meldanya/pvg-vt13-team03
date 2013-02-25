package unit;

import static org.junit.Assert.*;

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
		
		assertEquals("namnfil.txt", conf.getProperty("Namefile"));
		assertEquals("start.txt", conf.getProperty("StartFiles"));
	}

	@Test
	public void testStoreConfig() throws IOException {
		conf.setDefaults();
		conf.store(SorterConfig.CONFIGFILE);

		conf = new SorterConfig();
		conf.load(SorterConfig.CONFIGFILE);
		
		assertEquals("namnfil.txt", conf.getProperty("Namefile"));
		assertEquals("start.txt", conf.getProperty("StartFiles"));
	}

}
