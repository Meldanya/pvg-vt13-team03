package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SorterConfig {
	public static final String CONFIGFILE = "sorter.cfg";

	private Gson gson;
	private Map<String, Object> config;

	public SorterConfig() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		config = new TreeMap<String, Object>();
	}

	public void setDefaults() {
		config.put("Namefile", "namnfil.txt");
		config.put("StartFiles", "start.txt");
		config.put("FinishFiles", "finish.txt");
		config.put("ResultFile", "result.txt");
		config.put("SortedResultFile", "sortresultat.txt");

		config.put("NumberOfLaps", "1");
		config.put("TimeStartIsOpen", "01.00.00");
		config.put("Laps", new Lap[] { new Lap() });
	}

	public static void main(String args[]) {
		SorterConfig conf = new SorterConfig();
		conf.setDefaults();

		try {
			conf.store("sorter.cfg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param propertyName
	 *            the name of the property.
	 * @return the value in this property list with the specified name.
	 * @see java.util.Properties#getProperty(java.lang.String)
	 */
	public Object getProperty(String propertyName) {
		return config.get(propertyName);
	}

	/**
	 * @param propertyName
	 *            the property to be placed into this property list.
	 * @param propertyValue
	 *            the value corresponding to property.
	 * @return the previous value of the specified property in this property
	 *         list, or null if it did not have one.
	 */
	public Object setProperty(String propertyName, Object propertyValue) {
		return config.put(propertyName, propertyValue);
	}

	/**
	 * @param filename
	 *            The filename to load the config from
	 * @throws IOException
	 * @see java.util.Properties#load(java.io.Reader)
	 */
	public void load(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		config = gson.fromJson(sb.toString(), Map.class);
	}

	/**
	 * @param filename
	 *            The filename to store the config to
	 * @param comments
	 * @throws IOException
	 * @see java.util.Properties#store(java.io.Writer, java.lang.String)
	 */
	public void store(String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename);
		String json = gson.toJson(config);
		
		writer.write(json);
		writer.close();
	}

}
