package enduro;

import java.io.FileNotFoundException;

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
	    try {
			new Sorter(Integer.parseInt(laps));

			JOptionPane.showMessageDialog(null, "Sorteringen lyckades, resultatet skrivet till result.txt");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, ("Kunde ej hitta filerna"));
			e.printStackTrace();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, ("Sorteringen misslyckades"));
			e.printStackTrace();
		}
	}
}
