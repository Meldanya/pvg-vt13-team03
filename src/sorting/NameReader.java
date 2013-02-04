package sorting;

import java.util.Map;
import java.util.TreeMap;

public class NameReader extends Reader{
	private TreeMap<String, String> map;
	
	public NameReader(){
		map = new TreeMap<String, String>();
	}
	
	protected void op(String key, String value) {
		map.put(key, value);
		
	}
	
	public Map<String, String> readFromNameFile(String fileName) {
		readFromFile(fileName);
		return map;
	}

	@Override
	protected void error() {
		map=null;
		
	}

}
