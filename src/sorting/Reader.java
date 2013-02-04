package sorting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Reader {

    // TODO: klass utan tillstånd?
    // TODO: ska Sorter eller RacerMap använda denna? Den första kräver att värdet är en sträng och 
    // den andra att värdet är en lista av tider (formaterade som strängar)
	public void readFromFile(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));

			String result = null;
			while ((result = reader.readLine()) != null) {
				String[] tempArray = result.split("; ");
				op(tempArray[0], tempArray[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find " + fileName);
			error();
		} catch (IOException e) {
			System.out.println("Failed while reading " + fileName);
			error();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("File incorrectly formatted " + fileName);
			error();
		}
	}
	
	protected abstract void op(String key, String value);
	protected abstract void error();
}
