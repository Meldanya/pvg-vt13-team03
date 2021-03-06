/**
 * 
 */
package unit;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.AbstractRacer;
import racer.RacerFactory;
import sorting.Competition;
import sorting.ResultWriter;

/**
 *
 */
public class TestResultWriter {
	private Competition competition;
	private RacerFactory factory;
	private ResultWriter writer;
	private String filename;
	private String header;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		filename = "resultat.txt";
		header = "StartNr; Namn; TotalTid; StartTider; Måltider";
		factory = new RacerFactory("marathon", "00.15.00");
		competition = new Competition(factory);
		writer = new ResultWriter(competition, filename, null);
		
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

	/**
	 * Reads the testfile and returns it contents
	 * @return The testfiles contents
	 * @throws IOException
	 */
	private String readFile() throws IOException {
		FileReader file = new FileReader(filename);
		BufferedReader reader = new BufferedReader(file);
		
		StringBuilder sb = new StringBuilder();
		String line;
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		
		return sb.toString();
	}

	@Test
	public void testOneRacer() throws IOException {
		String name = "Anders Asson";
		AbstractRacer r1 = factory.makeRacer("1");
		r1.setName(name);
		Map<String, String> nameMappings = new HashMap<String, String>();
		nameMappings.put("1", name);
		competition.setNames(nameMappings);
		
		writer.writeToFile(1);
		String expected = header + "\n" + r1.racerString(1, true) + "\n";

		assertEquals("Result doesn't match", expected, readFile());
	}

	@Test
	public void testMultipleRacers() throws IOException {
		StringBuilder expected = new StringBuilder();
		Map<String, String> nameMappings = new HashMap<String, String>();
		
		String name1 = "Anders Asson";
		String name2 = "Bengt Bsson";
		String name3 = "Chris Csson";
		
		AbstractRacer r1 = factory.makeRacer("1");
		r1.setName(name1);
		AbstractRacer r2 = factory.makeRacer("2");
		r2.setName(name2);
		AbstractRacer r3 = factory.makeRacer("3");
		r3.setName(name3);
		
		nameMappings.put("1", name1);
		nameMappings.put("3", name3);
		nameMappings.put("2", name2);
		
		expected.append(header + "\n");
		expected.append(r1.racerString(1, true) + "\n");
		expected.append(r2.racerString(1, true) + "\n");
		expected.append(r3.racerString(1, true) + "\n");


		competition.setNames(nameMappings);
		
		writer.writeToFile(1);

		assertEquals("Result doesn't match", expected.toString(), readFile());
	}

}
