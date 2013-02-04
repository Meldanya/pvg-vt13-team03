package sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NameReader extends Reader{
	private HashMap<String, String> map;
	
	public NameReader(){
		map = new HashMap<String, String>();
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
