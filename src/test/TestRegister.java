package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import registration.Register;

public class TestRegister {

	Register register;
	
	@Before
	public void setUp(){
		
	}
	
	private void startMany(String fileName) throws IOException {
		register = new Register(true);
		register.registerMassStart(fileName, "12.00.00");
		
		BufferedReader reader = new BufferedReader(new FileReader("start.txt"));

		String expected = "1; 12.00.00" +
				"2; 12.00.00" +
				"3; 12.00.00" +
				"4; 12.00.00" +
				"5; 12.00.00";
		
		String line = null;
		StringBuilder actual = new StringBuilder();
		while (reader.ready()) {
			line = reader.readLine();
			actual.append(line);
		}
		
		assertEquals("Mass start failed.", expected, actual.toString());
	}
	
	@Test
	public void testStartMany() throws IOException {
		startMany("src/test/testNamnFil.txt");
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testNameFileNotFound() throws IOException {
		startMany("nonexisting");
	}

}
