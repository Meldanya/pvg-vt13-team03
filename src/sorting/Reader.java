package sorting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reader {

    // TODO: klass utan tillstånd?
    // TODO: ska Sorter eller RacerMap använda denna? Den första kräver att värdet är en sträng och 
    // den andra att värdet är en lista av tider (formaterade som strängar)
	public Map<String, String> readFromFile(String fileName) {

		Map<String, String> map = new HashMap<String, String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));

			String result = null;
			while ((result = reader.readLine()) != null) {
				String[] tempArray = result.split("; ");
				map.put(tempArray[0], tempArray[1]);
			}
			reader.close();
			return map;
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find " + fileName);
		} catch (IOException e) {
			System.out.println("Failed while reading " + fileName);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("File incorrectly formatted " + fileName);
		}

		return null;
	}

}
