package parser;

import java.util.ArrayList;

public class Parser {

	/**
	 * @param string
	 */
	private ArrayList<Value> getValues(String string) {
		String[] result = string.split(",");
		ArrayList<Value> values = new ArrayList<Value>();
		
		for (String s : result) {
			if (s.contains("-")) {
				values.add(new RangeValue(s));
			}
			else {
				values.add(new SingleValue(s));
			}
		}
		
		return values;
	}

	public ArrayList<String> parse(String string) {
		ArrayList<String> data = new ArrayList<String>();
		
		for (Value v : getValues(string)) {
			data.addAll(v.getValue());
		}
		
		return data;
	}
}
