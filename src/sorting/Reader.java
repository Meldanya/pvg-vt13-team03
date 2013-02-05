package sorting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Reader {

    // TODO: klass utan tillstånd?
    // TODO: ska Sorter eller RacerMap använda denna? Den första kräver att värdet är en sträng och 
    // den andra att värdet är en lista av tider (formaterade som strängar)
	public void readFromFile(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));

			while (reader.ready()) {
				String line = reader.readLine();
				
				if (line.contains(";")) {
					String[] tempArray = line.split("; ");
					op(tempArray[0], tempArray[1]);
				}
				else {
					op(line, "");
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find " + fileName);
			error();
		} catch (IOException e) {
			System.err.println("Failed while reading " + fileName);
			error();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Failed while parsing " + fileName);
			error();
		}
	}
	
	protected abstract void op(String key, String value);
	protected abstract void error();
}
