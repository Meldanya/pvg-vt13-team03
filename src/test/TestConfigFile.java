package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.Sorter;
import constants.FileNames;

public class TestConfigFile {
	
	Properties config;
	
	@Before
	public void setUp() throws Exception{
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.START)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.FINISH)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.NAMEFILE)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.CONFIG)));
		config = new Sorter(1).getCopyOfConfig();
	}
	
	@Test
	public void testDefaultConfigProperlyWritten() throws IOException{
		assertEquals("Namefile=namnfil.txt wasn't read.", "namnfil.txt", config.getProperty("Namefile"));
		assertEquals("TypeOfContest=Marathon wasn't read.", "Marathon", config.getProperty("TypeOfContest"));
		assertEquals("NumberOfLaps=1 wasn't read.", "1", config.getProperty("NumberOfLaps"));
		assertEquals("MinumumLapTime=00.15.00 wasn't read.", "00.15.00", config.getProperty("MinumumLapTime"));
		assertEquals("TimeLimit=01.00.00 wasn't read.", "01.00.00", config.getProperty("TimeLimit"));
	}
	
	@After
	public void tearDown() throws Exception{
		config = null;
	}
	
}
