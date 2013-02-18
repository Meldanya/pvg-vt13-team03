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
		config.setProperty("StartFiles", "start.txt");
		config.setProperty("FinishFiles", "finish.txt");
		config.setProperty("ResultFile", "result.txt");

		config.setProperty("NumberOfLaps", "1");
		config.setProperty("TimeStartIsOpen", "01.00.00");
	}

	/** @param propertyName the name of the property.
	 * @return the value in this property list with the specified name.
	 * @see java.util.Properties#getProperty(java.lang.String) */
	public String getProperty(String propertyName) {
		return config.getProperty(propertyName);
	}

	/** @param propertyName the property to be placed into this property list.
	 * @param propertyValue the value corresponding to property.
	 * @return the previous value of the specified property in this property
	 *         list, or null if it did not have one. */
	public Object setProperty(String propertyName, String propertyValue) {
		return config.setProperty(propertyName, propertyValue);
	}

	/** @param filename The filename to load the config from
	 * @throws IOException
	 * @see java.util.Properties#load(java.io.Reader) */
	public void load(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		config.load(reader);
	}

	/** @param filename The filename to store the config to
	 * @param comments
	 * @throws IOException
	 * @see java.util.Properties#store(java.io.Writer, java.lang.String) */
	public void store(String filename, String comments) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		config.store(writer, comments);
	}

}
