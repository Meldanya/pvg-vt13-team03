package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import racer.RacerPlacingComparator;
import constants.FileNames;

/** A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver. */
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

	/** @throws IOException */
	private void initializeConfig() throws IOException {
		this.config = new SorterConfig();

		try {
			config.load(FileNames.CONFIG);
		} catch (FileNotFoundException e1) {
			try {
				new SorterConfig().store(FileNames.CONFIG, "Default config for Enduro Sorter");
			} catch (IOException e) {
				System.err.println("Couldn't create a file with the default configuration.");
			}
		}
	}

	private int laps() {
		return Integer.parseInt(config.getProperty("NumberOfLaps"));
	}

	private String namefile() {
		return config.getProperty("Namefile");
	}

	/** Returns a list with the filenames that the sorter will read goal times
	 * from.
	 * 
	 * @return A list with the goal times. */
	public ArrayList<String> finishFiles() {
		ArrayList<String> finishFiles = new ArrayList<String>();
		String finishFilesString = config.getProperty("FinishFiles");
		finishFilesString = finishFilesString.replaceAll("\\s", "");
		String[] finishFilesArray = finishFilesString.split(",");
		for (int i = 0; i < finishFilesArray.length; i++) {
			finishFiles.add(finishFilesArray[i]);
		}
		return finishFiles;
	}

	private String startFile() {
		return config.getProperty("StartFile");
	}

	private String resultfile() {
		return config.getProperty("ResultFile");
	}

	private void read() throws IOException {

		File directory = new File(".");
		String[] finishFiles = directory.list(new FinishFileFilter());

		racers.setStartTimesFromFile(startFile());

		for (String fileName : finishFiles) {
			racers.setFinishTimesFromFile(fileName);
		}

	}

	private class FinishFileFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("finish") && name.endsWith(".txt");
		}
	}

	/** @todo kolla vad första raden innehåller istället.
	 * @todo skicka in en Map<id, namn> till RacerMap istället */
	private void readNames() throws IOException {
		Map<String, String> names = new NameReader().readFromNameFile(namefile());

		names.remove("StartNr");
		racers.setNames(names);
	}

	private void write() {
		ArrayList<String> finishFiles = finishFiles();
		for (int i = 0; i < finishFiles.size(); i++) {
			racers.writeToFile(resultfile(), laps(), null);
			racers.writeToFile(FileNames.SORTED_OUTFILE, laps(), new RacerPlacingComparator());
		}
	}
}
