package parser;

import java.util.ArrayList;

public class RangeValue implements Value {
	private ArrayList<String> values;
	
	public RangeValue(String value) {
		this.values = new ArrayList<String>();
		String[] values = value.split("-");
		int start = Integer.parseInt(values[0]);
		int end = Integer.parseInt(values[1]);
		
		for (int i = start; i <= end; i++) {
			this.values.add(Integer.toString(i));
		}
	}

	@Override
	public ArrayList<String> getValue() {
		return values;
	}
}
