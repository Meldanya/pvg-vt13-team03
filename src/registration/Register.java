package registration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

import constants.Strings;

import racer.RacerTime;

/**
 * A class representing a register (aka a program that registers racers at the
 * start and finish line).
 */
public abstract class Register extends Observable {
	private String lastLine;

	/**
	 * Writes the result to the file with the provided file name.
	 * 
	 * @param fileName
	 *            The file name to write to.
	 * @param time TODO
	 */
	public void writeToFile(String fileName, String startNumber, RacerTime time) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,
					true));
			lastLine = stringToAppendToFile(startNumber, time);

			writer.append(lastLine);
			writer.newLine();
			writer.close();

			setChanged();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		notifyObservers();
	}

	/**
	 * @param startNumber
	 * @param time
	 * @return
	 */
	private String stringToAppendToFile(String startNumber,
			RacerTime time) {
		StringBuilder text = new StringBuilder();

		text.append(startNumber);
		text.append("; ");
		text.append(time);
		
		return text.toString();
	}

	/**
	 * Returns the last written line, used to output the written line in the GUI
	 * 
	 * @return the last written line
	 */
	public String lastLine() {
		return lastLine;
	}
	
	protected abstract String getFileName();

	/**
	 * Registers a new time for a racer with the provided start number. It will use the current time.
	 * 
	 * @param startNumber
	 *            The start number of the driver to register.
	 */
	public void register(String startNumber) {
		register(startNumber, new RacerTime());
	}

	/**
	 * Registers a new time for a racer with the provided start number.
	 * 
	 * @param startNumber
	 *            The start number of the driver to register.
	 * @param time
	 *            The time to register.
	 */
	public void register(String startNumber, String time) {
		register(startNumber, new RacerTime(time));
	}

	/**
	 * Registers the specified time
	 * 
	 * @param startNumber
	 * @param time
	 */
	public void register(String startNumber, RacerTime time) {
		writeToFile(getFileName(), startNumber, time);
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
