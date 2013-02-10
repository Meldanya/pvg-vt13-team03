package registration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

import racer.Racer;
import racer.RacerTime;
import res.Strings;

/**
 * A class representing a register (aka a program that registers racers at the
 * start and finish line).
 */
public abstract class Register extends Observable {
	private String lastLine;
	protected Racer racer;

	/**
	 * Writes the result to the file with the provided file name.
	 * 
	 * @param fileName
	 *            The file name to write to.
	 */
	public void writeToFile(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,
					true));
			StringBuilder text = new StringBuilder();

			text.append(racer.getStartNumber());
			text.append("; ");
			text.append(getRacerTime());

			writer.append(text);
			writer.newLine();
			writer.close();

			lastLine = text.toString();

			setChanged();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		notifyObservers();
	}
	
	/**
	 * Subclasses implements this method to return either start time or finish time,
	 * depending on the use of Register for Start or Finish.
	 * @return Representation of the read time
	 */
	protected abstract String getRacerTime();

	/**
	 * Returns the last written line, used to output the written line in the GUI
	 * 
	 * @return the last written line
	 */
	public String lastLine() {
		return lastLine;
	}

	/**
	 * Registers a new time for a racer with the provided start number. It will use the current time.
	 * 
	 * @param startNumber
	 *            The start number of the driver to register.
	 */
	public void register(String startNumber) {
		register(startNumber, new RacerTime());
	}
	
	protected abstract void addTime(RacerTime racerTime);
	
	protected abstract String getFileName();

	/**
	 * Registers a new time for a racer with the provided start number.
	 * 
	 * @param startNumber
	 *            The start number of the driver to register.
	 * @param time
	 *            The time to register.
	 */
	public void register(String startNumber, String time) {
		racer = new Racer(startNumber);
		register(startNumber, new RacerTime(time));
	}

	/**
	 * Registers the specified time
	 * 
	 * @param startNumber
	 * @param time
	 */
	public void register(String startNumber, RacerTime time) {
		racer = new Racer(startNumber);
		addTime(time);
		writeToFile(getFileName());
	}

	/**
	 * Reads Racer IDs from nameFile and writes the same startTime for every
	 * Racer.
	 * 
	 * @param nameFile
	 *            name of the file containing names of Racers
	 * @param startTime
	 *            start time to register
	 * @throws FileNotFoundException
	 *             If nameFile doesn't exist
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public void registerMassStart(String nameFile, String startTime) throws IOException {
		new FileWriter(Strings.START).close(); // clear the file!
		
		  BufferedReader reader = new BufferedReader(new FileReader(nameFile));
		  
		  reader.readLine(); while (reader.ready()) { String line =
		  reader.readLine(); String[] tempArray = line.split("; ");
		  register(tempArray[0], startTime); } reader.close();
		 
	}
}
