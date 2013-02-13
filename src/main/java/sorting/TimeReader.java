package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeReader extends Reader {
	private HashMap<String, ArrayList<String>> map;

	protected void op(String key, String value) {
		if(!map.containsKey(key)){
			map.put(key, new ArrayList<String>());
		} 
		map.get(key).add(value);			
	}

	public Map<String, ArrayList<String>> readFromTimeFile(String fileName) throws IOException {
		map = new HashMap<String, ArrayList<String>>();
		readFromFile(fileName);
		return map;
	}
}
