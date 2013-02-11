package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorting.Sorter;
import sorting.SorterDefaultConfig;
import constants.FileNames;

public class TestSorter {
	Properties config;
	
	@Before
	public void setUp() throws Exception{
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.START)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.FINISH)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.NAMEFILE)));
		new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileNames.CONFIG)));
		config = new Properties(new SorterDefaultConfig());
	}
	
	@Test
	public void testFinishFilesCanHandleWhitespace() throws IOException{
		config.setProperty("FinishFiles","finish1.txt, finish2.txt,\tfinish3.txt");
    	config.store(new FileOutputStream(FileNames.CONFIG),"Test config for Enduro Sorter");
    	Sorter sorter = new Sorter();
    	ArrayList<String> finishFiles = sorter.finishFiles();
    	assertEquals("Whitespace wasn't handled","finish1.txt",finishFiles.get(0));
    	assertEquals("Whitespace wasn't handled","finish2.txt",finishFiles.get(1));
    	assertEquals("Whitespace wasn't handled","finish3.txt",finishFiles.get(2));
	}
	
	@After
	public void tearDown(){
		config = null;
	}
}
