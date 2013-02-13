package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class SorterConfig {
	private Properties config;

	public SorterConfig() {
		config = new Properties();
		config.setProperty("Namefile", "namnfil.txt");

		config.setProperty("TypeOfContest", "Marathon");

		config.setProperty("NumberOfLaps", "1");
		config.setProperty("MinimumLapTime", "00.15.00");
		config.setProperty("TimeStartIsOpen", "01.00.00");

		config.setProperty("FinishFiles", "finish.txt");
		config.setProperty("ResultFile", "result.txt");
	}

	/** @param propertyName
	 * @param propertyValue
	 * @return T */
	public Object setProperty(String propertyName, String propertyValue) {
		return config.setProperty(propertyName, propertyValue);
	}

	public String getProperty(String propertyName) {
		return config.getProperty(propertyName);
	}

	/** @param filename TODO
	 * @throws IOException
	 * @see java.util.Properties#load(java.io.Reader) */
	public void load(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		config.load(reader);
	}

	/** @param filename The filename to load the config from
	 * @param comments
	 * @throws IOException
	 * @see java.util.Properties#store(java.io.Writer, java.lang.String) */
	public void store(String filename, String comments) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		config.store(writer, comments);
	}

}
