package registration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import racer.RacerTime;

/**
 * A class representing a register (aka a program that registers racers at the
 * start and finish line).
 */
public class Register extends Observable {
	private String fileName;
	private String lastWrittenLine;

	public Register(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Registers a new time for a list of racers with the provided start
	 * numbers. It will use the current time.
	 * 
	 * @param startNumbers
	 *            The start numbers of the drivers to register.
	 */
	public void register(ArrayList<String> startNumbers) {

		RacerTime racerTime = new RacerTime();
		for (String startNumber : startNumbers) {
			register(startNumber, racerTime);
		}
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
		try {
			register(startNumber, new RacerTime(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Writes the result to the file with the provided file name.
	 * 
	 * @param fileName
	 *            The file name to write to.
	 * @param time
	 *            TODO
	 */
	public void register(String startNumber, RacerTime time) {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName, true), "UTF-8"));
			lastWrittenLine = stringToAppendToFile(startNumber, time);

			writer.append(lastWrittenLine);
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
	private String stringToAppendToFile(String startNumber, RacerTime time) {
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
	public String getLastWrittenLine() {
		return lastWrittenLine;
	}

	/**
	 * Reads AbstractRacer IDs from nameFile and writes the same startTime for every
	 * AbstractRacer.
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
	public void registerMassStart(String nameFile, String startTime)
			throws IOException {

		new File(FileNames.START).delete(); // remove existing start file

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				nameFile)));

		scanner.nextLine();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tempArray = line.split("; ");
			register(tempArray[0], startTime);
		}
		scanner.close();

	}
}
