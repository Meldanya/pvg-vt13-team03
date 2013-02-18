package sorting;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class NameReader extends Reader{
	private Map<String, String> map;
	
	public NameReader(){
		map = new LinkedHashMap<String, String>();
	}
	
	protected void op(String key, String value) {
		map.put(key, value);
		
	}
	
	public Map<String, String> readFromNameFile(String fileName) throws IOException {
		readFromFile(fileName);
		return map;
	}
}
