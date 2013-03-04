package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Reader {

	public void readFromFile(String fileName) throws IOException {
		BufferedReader reader;

		reader = new BufferedReader(new FileReader(fileName));

		while (reader.ready()) {
			String line = reader.readLine();
			if (line.contains(";")) {
				String[] tempArray = line.split(";");

				String first = tempArray[0].trim();
				try {
					first = String.valueOf(Integer.parseInt(first));
				} catch (Exception e) {
				}

				op(first, tempArray[1].trim());
			} else {
				op(line, "");
			}
		}

		reader.close();
	}

	protected abstract void op(String key, String value);
}
