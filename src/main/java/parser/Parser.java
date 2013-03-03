package parser;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			} else {
				values.add(new SingleValue(s));
			}
		}

		return values;
	}

	public ArrayList<String> parse(String string) {
		
		ArrayList<String> data = new ArrayList<String>();
		
		for (int i = 0; i < string.length(); i++) {

			// If we find a non-digit character we return false.
			if (!(Character.isDigit(string.charAt(i))
					|| string.charAt(i) == ',' || string.charAt(i) == '-')) {

				JOptionPane.showMessageDialog(new JFrame(),
								"Felaktig inmatning, se manualen för korrekt formatering",
								"Ett fel har uppstått",
								JOptionPane.ERROR_MESSAGE);
				return data;
				

			}
		}

		// if(string.)
	

		for (Value v : getValues(string)) {
			data.addAll(v.getValue());
		}

		return data;
	}
}
