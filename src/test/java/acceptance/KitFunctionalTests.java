package acceptance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import sorting.Sorter;
import sorting.SorterDefaultConfig;

public class KitFunctionalTests {

	/* --- FILE PATHS --- */
	private static final String ACCEPTANCE_PATH = "acceptance/acceptanstest";
	private static final String START_TIME = "starttider.txt";
	private static final String RESULT_PATH = "resultat.txt";
	private static final String NAMES_PATH = "namnfil.txt";
	private static final String FINISH_TIME_PATH = "maltider.txt";
	private static final String SEPARATOR = "/";
	/* --- Filer som bara finns på skolan, vad heter de? --- */
	private static final String START_TIME2 = "starttider.txt";
	private static final String RESULT_PATH2 = "resultat.txt";
	private static final String NAMES_PATH2 = "namnfil.txt";
	private static final String FINISH_TIME_PATH2 = "maltider.txt";


	@SuppressWarnings("unused")
	private Properties config;

	@Before
	public void setUp() {
		config = new Properties(new SorterDefaultConfig());
	}

	@After
	public void tearDown() {
		config = null;
	}

	//@Test
	public void test5() {
		simpleLapTest("5");
	}

	private void simpleLapTest(String number) {
		// läs in filerna som förväntas
		String expectedStartTime = getStringFromFile(ACCEPTANCE_PATH + number +SEPARATOR
				+ START_TIME);
		String expectedFinishTime = getStringFromFile(ACCEPTANCE_PATH + number+SEPARATOR
				+ FINISH_TIME_PATH);
		String expectedNames = getStringFromFile(ACCEPTANCE_PATH + number+SEPARATOR
				+ NAMES_PATH);
		String expectedResult = getStringFromFile(ACCEPTANCE_PATH + number+SEPARATOR
				+ RESULT_PATH);

		// kör sortern för att generera filer.
		try {
			new Sorter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		// Hämta genererade filer
		String generatedStartTime = getGenerated(START_TIME2);
		String generatedFinishTime = getGenerated(FINISH_TIME_PATH2);
		String generatedNames = getGenerated(NAMES_PATH2);
		String generatedResult = getGenerated(RESULT_PATH2);

		// jämför
		assertEquals(expectedStartTime, generatedStartTime);
		assertEquals(expectedFinishTime, generatedFinishTime);
		assertEquals(expectedNames, generatedNames);
		assertEquals(expectedResult, generatedResult);

	}

	/**
	 * Reads all lines from a file.
	 * 
	 * @param fileType
	 * @return
	 */
	private String getGenerated(String fileType) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(fileType));
		} catch (FileNotFoundException e) {
			//Bryt test. 
			e.printStackTrace();
		}

		return readFromStream(stream);
	}

	/**
	 * Reads all lines from a resource.
	 * 
	 * @param resourcePath
	 * @return
	 */
	private String getStringFromFile(String resourcePath) {

		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(resourcePath);
		return readFromStream(stream);
	}

	/**
	 * Reads all lines from a stream.
	 * 
	 * @param stream
	 * @return
	 */
	private String readFromStream(InputStream stream) {
		Scanner scanner = new Scanner(stream);
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNext()) {
			sb.append(scanner.nextLine());
			sb.append(System.getProperty("line.separator"));
		}
		scanner.close();
		return sb.toString();
	}

}
