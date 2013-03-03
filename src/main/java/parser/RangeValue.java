package parser;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RangeValue implements Value {
	private ArrayList<String> values;
	
	public RangeValue(String value) {
		this.values = new ArrayList<String>();
		String[] values = value.split("-");
		int start = Integer.parseInt(values[0]);
		int end = Integer.parseInt(values[1]);
		if(start > end) {
			JOptionPane.showMessageDialog(new JFrame(), "Felaktig inmatning, se manualen för korrekt formatering", "Ett fel har uppstått",
			        JOptionPane.ERROR_MESSAGE);
		}
		for (int i = start; i <= end; i++) {
			this.values.add(Integer.toString(i));
		}

	}

	@Override
	public ArrayList<String> getValue() {
		return values;
	}
}
