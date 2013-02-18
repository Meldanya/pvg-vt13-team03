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
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import racer.Racer;
import racer.RacerClass;
import racer.RacerRankingComparator;
import racer.RacerTime;
import sorting.Competition;
import sorting.SortResultWriter;

/**
 * @author dat11rla
 *
 */
public class TestSortResultWriter {
	private Competition competition;
	private SortResultWriter writer;
	private String filename;
	private String header;
	private RacerRankingComparator comp = new RacerRankingComparator();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		filename = "resultat.txt";
		header = "Plac; StartNr; Namn; TotalTid";
		competition = new Competition();
		writer = new SortResultWriter(competition, filename, comp, "01.00.00");
		
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
		Racer r1 = new Racer("1");
		r1.setName(name);
		Map<String, String> nameMappings = new HashMap<String, String>();
		nameMappings.put("1", name);
		competition.setNames(nameMappings);
		
		writer.writeToFile(1);
		String expected = header + "\n; " + r1 + "\n";
		assertEquals("Result doesn't match", expected, readFile());
	}

	@Test
	public void testMultipleRacers() throws Exception {
		StringBuilder expected = new StringBuilder();
		Map<String, String> nameMappings = new HashMap<String, String>();
		
		String name1 = "Anders Asson";
		String name2 = "Bengt Bsson";
		String name3 = "Chris Csson";
		
		Racer r1 = new Racer("1");
		r1.setName(name1);
		r1.addStartTime(new RacerTime("12.00.00"));
		r1.addFinishTime(new RacerTime("15.00.00"));
		Racer r2 = new Racer("2");
		r2.setName(name2);
		r2.addStartTime(new RacerTime("12.00.00"));
		r2.addFinishTime(new RacerTime("14.00.00"));
		Racer r3 = new Racer("3");
		r3.setName(name3);
		r3.addStartTime(new RacerTime("12.00.00"));
		r3.addFinishTime(new RacerTime("13.00.00"));
		
		nameMappings.put("1", name1);
		nameMappings.put("2", name2);
		nameMappings.put("3", name3);
		
		expected.append(header + "\n");
		expected.append("1; " + r3 + "\n");
		expected.append("2; " + r2 + "\n");
		expected.append("3; " + r1 + "\n");

		competition.setNames(nameMappings);
		Set<Racer> racers = competition.getRacers(new RacerClass(""), null);
		int counter = 15;
		for(Racer racer: racers){
			racer.addStartTime(new RacerTime("12.00.00"));
			racer.addFinishTime(new RacerTime(counter + ".00.00"));
			counter--;
		}
		
		writer.writeToFile(1);

		assertEquals("Result doesn't match", expected.toString(), readFile());
	}

}
