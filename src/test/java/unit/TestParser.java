package unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.Parser;

public class TestParser {
	private Parser parser;
	
	@Before
	public void setUp() {
		parser = new Parser();
	}
	
	@Test
	public void testSingleValue() {
		ArrayList<String> racers = parser.parse("1");
		
		assertEquals("Should return 1", "1", racers.get(0));
	}
	
	@Test
	public void testMultipleValue() {
		ArrayList<String> racers = parser.parse("1,2,3");

		assertEquals("Should return 1", "1", racers.get(0));
		assertEquals("Should return 2", "2", racers.get(1));
		assertEquals("Should return 3", "3", racers.get(2));
	}
	
	@Test
	public void testRange() {
		ArrayList<String> racers = parser.parse("1-3");

		assertEquals("Should return 1", "1", racers.get(0));
		assertEquals("Should return 2", "2", racers.get(1));
		assertEquals("Should return 3", "3", racers.get(2));
	}
	
	@Test
	public void testCombination() {
		ArrayList<String> racers = parser.parse("1,2-4");

		assertEquals("Should return 1", "1", racers.get(0));
		assertEquals("Should return 2", "2", racers.get(1));
		assertEquals("Should return 3", "3", racers.get(2));
		assertEquals("Should return 4", "4", racers.get(3));
	}
}
