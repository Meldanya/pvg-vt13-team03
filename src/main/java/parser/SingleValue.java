package parser;

import java.util.ArrayList;

public class SingleValue implements Value {
	String value;
	
	public SingleValue(String value) {
		this.value = value.trim();
		if(value.isEmpty()) throw new IllegalArgumentException();
		
	}

	@Override
	public ArrayList<String> getValue() {
		ArrayList<String> values = new ArrayList<String>();
		
		values.add(value);
		
		return values;
	}
}
