package sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import constants.FileNames;


/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {
	private Competition racers;
	private Properties config;
	
	public Sorter() throws IOException {
		racers = new Competition();
		
		
		this.config = new Properties(new SorterDefaultConfig());
		
		try {
			config.load(new BufferedReader(new FileReader(FileNames.CONFIG)));
		} catch (FileNotFoundException e1) {
			new SorterDefaultConfig().store(new FileOutputStream(FileNames.CONFIG),"Default config for Enduro Sorter");
			// May throw an exception. For example if the user doesn't have
			// permission to write  
		}
		read();
		readNames();
		
		write();
	}
	private int laps(){
		return Integer.parseInt(config.getProperty("NumberOfLaps"));
	}
	private String namefile(){
		return config.getProperty("Namefile");
	}
	private ArrayList<String> finishFiles(){
		ArrayList<String> finishFiles = new ArrayList<String>();
		finishFiles.add(config.getProperty("FinishFiles"));
		return finishFiles;
	}
	private void read() throws IOException {
	
		File directory = new File(".");
		String[] finishFiles = directory.list(new FinishFileFilter());
		
		racers.readFromFile(FileNames.START, true);

		
		for(String fileName : finishFiles) {
			racers.readFromFile(fileName, false);			
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
			racers.writeToFile(FileNames.OUTFILE, laps(),null);
		}
	}
	
	/**
	 * Returns a copy of the current configuration. This is used for testing. 
	 * 
	 * @return a copy of the configuration
	 */
	public Properties getCopyOfConfig() {
		return new Properties(config);
	}
}
