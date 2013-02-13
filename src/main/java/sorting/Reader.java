package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Reader {

    // TODO: klass utan tillstånd?
    // TODO: ska Sorter eller RacerMap använda denna? Den första kräver att värdet är en sträng och 
    // den andra att värdet är en lista av tider (formaterade som strängar)
	public void readFromFile(String fileName) throws IOException {
		BufferedReader reader;
		
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
	}
	
	protected abstract void op(String key, String value);
}
