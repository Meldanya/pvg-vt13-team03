package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import racer.Racer;

/**
 * A class representing a sorter. It reads start.txt and finish.txt and outputs
 * result.txt but only for one driver.
 */
public class Sorter {
	private Racer racer;

	/**
	 * A private helper method for reading the first line of a file and split
	 * the line on "; ".
	 * 
	 * @param fileName
	 *            The file name to read
	 * @return The first line split into a String array.
	 */
	private String[] readFile(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String[] result = reader.readLine().split("; ");
			reader.close();
			return result;
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find " + fileName);
		} catch (IOException e) {
			System.out.println("Failed while reading " + fileName);
		}

		return null;
	}
	
	public Map<String, String> readInputFile(String fileName){
		return null;
	}

	/**
	 * The method that performs the reading and sorting.
	 */
	public void sort() {
		try {
			String[] finishFileContent = readFile("finish.txt");
			String[] startFileContent = readFile("start.txt");

			racer = new Racer(Integer.parseInt(startFileContent[0]));
			racer.setStartTime(Integer.parseInt(startFileContent[1]));
			racer.setFinishTime(Integer.parseInt(finishFileContent[1]));

			BufferedWriter resultWriter = new BufferedWriter(new FileWriter("result.txt"));
			resultWriter.write(racer.toString());
			resultWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file: " + e.toString());
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Failed while reading file: " + e.toString());
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		new Sorter().sort();
	}
}
