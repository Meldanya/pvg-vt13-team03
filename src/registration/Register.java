package registration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import racer.Racer;

/**
 * A class representing a register (aka a program that registers racers at the
 * start and finish line).
 */
public class Register {
	private Racer racer;
	private boolean start;

	/**
	 * Creates a new Register instance with the boolean parameter specifying
	 * whether it should register start or finish times.
	 * 
	 * @param start
	 *            Whether the Register instance should register start of finish
	 *            times.
	 */
	public Register(boolean start) {
		this.start = start;
	}

	/**
	 * Writes the result to the file with the provided file name.
	 * 
	 * @param fileName
	 *            The file name to write to.
	 */
	public void writeToFile(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(racer.getStartNumber() + "; ");
			if (start) {
				writer.write(Integer.toString(racer.getStartTime()));
			} else {
				writer.write(Integer.toString(racer.getFinishTime()));
			}
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
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
	public void register(int startNumber, int time) {
		racer = new Racer(startNumber);
		if (start) {
			racer.setStartTime(time);
			writeToFile("start.txt");
		} else {
			racer.setFinishTime(time);
			writeToFile("finish.txt");
		}
	}
	
	public static void main(String[] args) {
		new Register(false).register(1, (int) (System.currentTimeMillis() / 1000));
	}
}
