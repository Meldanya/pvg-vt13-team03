package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import racer.RacerRankingComparator;

import com.google.gson.Gson;


/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {
	private Competition racers;
	private SorterConfig config;

	public Sorter() throws IOException {
		racers = new Competition();
					
		initializeConfig();
		read();
		readNames();

		write();
	}

	/**
	 * Tries to load config from file, if file cannot be found we instead load
	 * the defaults and create a new file.
	 * @throws IOException
	 */
	private void initializeConfig() throws IOException {
		config = new SorterConfig();

		try {
			config.load(SorterConfig.CONFIGFILE);
		} catch (FileNotFoundException e1) {
			config = new SorterConfig();
			config.setDefaults();
			config.store(SorterConfig.CONFIGFILE);  
		}
	}

	private int laps() {
		return Integer.parseInt((String) config.getProperty("NumberOfLaps"));
	}
	private String namefile(){
		return (String) config.getProperty("Namefile");
	}
	
	/**
	 * Returns a list with the filenames that the sorter will read goal times from.
	 * @return A list with the goal times.
	 */
	private ArrayList<String> finishFiles(){
		return getPropertyMultipleEntries("FinishFiles");
	}

	private ArrayList<String> startFiles() {
		return getPropertyMultipleEntries("StartFiles");
	}
	
	private ArrayList<String> getPropertyMultipleEntries(String propertyName){
		ArrayList<String> properties = new ArrayList<String>();
		String propertiesString = (String) config.getProperty(propertyName);
		propertiesString = propertiesString.replaceAll("\\s",""); // strip whitespace
		String[] propertiesArray = propertiesString.split(",");
		for (int i = 0; i < propertiesArray.length; i++){
			properties.add(propertiesArray[i]);
		}
		return properties;
	}

	public String resultfile() {
		return (String) config.getProperty("ResultFile");
	}
	private void read() throws IOException {		
		for (String fileName : startFiles()){
			racers.setStartTimesFromFile(fileName);
		}

		File directory = new File(".");
		String[] finishFiles = directory.list(new FinishFileFilter());
		for (String fileName : finishFiles) {
			racers.setFinishTimesFromFile(fileName);
		}
	}
	
	private class FinishFileFilter implements FilenameFilter{
		@Override
		public boolean accept(File dir, String name) {
			 return name.startsWith("finish") && name.endsWith(".txt");
		}
	}

	/**
	 * @todo kolla vad första raden innehåller istället.
	 * @todo skicka in en Map<id, namn> till RacerMap istället
	 */
	private void readNames() throws IOException {
		Map<String, String> names = new NameReader().readFromNameFile(namefile());


		names.remove("StartNr");
		racers.setNames(names);
	}

	private void write() {
		ArrayList<String> finishFiles = finishFiles();
		for (int i = 0; i < finishFiles.size(); i++){
			new ResultWriter(racers, resultfile()).writeToFile(laps());
			String timeStartIsOpen = (String) config.getProperty("TimeStartIsOpen");
			new SortResultWriter(racers, (String) config.getProperty("SortedResultFile"), new RacerRankingComparator(), timeStartIsOpen).writeToFile(laps());
		}
	}
}
