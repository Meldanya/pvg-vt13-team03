package sorting;

import java.io.IOException;
import java.util.Map;

import constants.FileNames;


/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {

	private Competition racers;
	private int laps;
	
	public Sorter(int laps) throws IOException {
		racers = new Competition();
		
		this.laps = laps;
		
		read();
		readNames();
		
		write();
	}

	private void read() throws IOException {
	
//		String yourPath = ".";
//		File directory = new File(yourPath);
//		String[] myFiles = directory.list(new FinishFileFilter());
		
		racers.readFromFile(FileNames.START, true);
		racers.readFromFile(FileNames.FINISH, false);		

	}
	
//	private class FinishFileFilter implements FilenameFilter{
//		@Override
//		public boolean accept(File dir, String name) {
//			 return name.startsWith("maltider") && name.endsWith(".txt");
//		}
//	}

	/**
	 * @todo kolla vad första raden innehåller istället.
	 * @todo skicka in en Map<id, namn> till RacerMap istället
	 */
	private void readNames() throws IOException {
		Map<String, String> names = new NameReader().readFromNameFile(FileNames.NAMEFILE);


		names.remove("StartNr");
		racers.setNames(names);
	}

	private void write() {
		racers.writeToFile(FileNames.OUTFILE, laps);
	}
}
