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
public class Register extends Observable {
	private Racer racer;
	private boolean isStart;
	private String lastLine;

	/**
	 * Creates a new Register instance with the boolean parameter specifying
	 * whether it should register start or finish times.
	 * 
	 * @param start
	 *            Whether the Register instance should register start of finish
	 *            times.
	 */
	public Register(boolean start) {
		this.isStart = start;
	}

	/**
	 * Writes the result to the file with the provided file name.
	 * 
	 * @param fileName
	 *            The file name to write to.
	 */
	public void writeToFile(String fileName) {
		// TODO: döp om till addResult eftersom den inte bara skriver till fil
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,
					true));
			String text = racer.getStartNumber() + "; ";
			if (isStart) {
				text += racer.getStartTime();
			} else {
				text += racer.getFinishTime();
			}
			writer.append(text);

			writer.newLine();
			writer.close();

			lastLine = text;

			setChanged();
		} catch (IOException e) {
			e.printStackTrace();
		}
		notifyObservers();
	}

	/**
	 * Returns the last written line
	 * 
	 * @return the last written line
	 */
	public String lastLine() {
		return lastLine;
	}

	/**
	 * Registers a new time for a racer with the provided start number.
	 * 
	 * @param startNumber
	 *            The start number of the driver to register.
	 */
	public void register(String startNumber) {
		racer = new Racer(startNumber);
		if (isStart) {
			racer.addStartTime(new RacerTime());
			writeToFile(Strings.START);
		} else {
			racer.addFinishTime(new RacerTime());
			writeToFile(Strings.FINISH);
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
		racer = new Racer(startNumber);
		register(startNumber, new RacerTime(time));
	}

	// Om man går via register(String startNumber, String time) så kommer det
	// skapas två objekt, lite dum kod
	public void register(String startNumber, RacerTime time) {
		racer = new Racer(startNumber);
		if (isStart) {
			racer.addStartTime(time);
			writeToFile(Strings.START);
		} else {
			racer.addFinishTime(time);
			writeToFile(Strings.FINISH);
		}
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
