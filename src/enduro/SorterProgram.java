package enduro;

import javax.swing.JOptionPane;

import sorting.Sorter;

public class SorterProgram {

	/**
	 * Launches the sorting application
	 * @param args Unused
	 */
	public static void main(String[] args) {
	    String laps;
	    if (args.length == 1) {
	        laps = args[0];
	    } else {
	        laps = JOptionPane.showInputDialog("Fyll i önskat antal varv, 1 för maratontävling");
	    }
		new Sorter(Integer.parseInt(laps));
	}
}
