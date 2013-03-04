package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import racer.Distance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SorterConfig {
	public static final String CONFIG_FILE = System.getProperty("user.home") + "/sorter.cfg";

	private Gson gson;
	private Map<String, Object> config;

	public SorterConfig() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		config = new TreeMap<String, Object>();
	}

	/**
	 * Set as its own method to allow for creating empty configuration
	 */
	public void setDefaults() {
		set("Namefile", "namnfil.txt");
		set("StartFiles", "start.txt");
		set("FinishFilePrefix", "finish");
		set("ResultFile", "result.txt");
		set("SortedResultFile", "sortresultat.txt");
		set("ContestType", "marathon");
		set("NumberOfLaps", "1");
		set("TimeStartIsOpen", "01.00.00");
		set("IncludeAbsoluteTimes", "true");
		set("MinTime", "00.15.00");
	}

	/**
	 * Masks private map
	 * 
	 * @param propertyName
	 *            the name of the property.
	 * @return the value in this property list with the specified name.
	 */
	public Object get(String propertyName) {
		return config.get(propertyName);
	}

	/**
	 * Masks private map
	 * 
	 * @param propertyName
	 *            the property to be placed into this property list.
	 * @param propertyValue
	 *            the value corresponding to property.
	 * @return the previous value of the specified property in this property
	 *         list, or null if it did not have one.
	 */
	public Object set(String propertyName, Object propertyValue) {
		return config.put(propertyName, propertyValue);
	}

	/**
	 * Loads configuration from file and parses it as JSON, suppresses warnings
	 * as map class needs type definition which is impossible to pass in this
	 * use case.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE));
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		config = gson.fromJson(sb.toString(), Map.class);

		if (config == null) {
			throw new EmptyConfigurationFileException();
		}
	}

	/**
	 * Outputs the configuration to a configuration file formatted as JSON
	 * 
	 * @throws IOException
	 */
	public void store() throws IOException {
		PrintWriter writer = new PrintWriter(CONFIG_FILE);
		String json = gson.toJson(config);

		writer.write(json);
		writer.close();
	}

}
