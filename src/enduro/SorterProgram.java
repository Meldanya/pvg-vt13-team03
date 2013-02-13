package enduro;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import sorting.Sorter;
import constants.FileNames;

public class SorterProgram {

	/** Launches the sorting application
	 * 
	 * @param args Unused */
	public static void main(String[] args) {
		try {
			new Sorter();

			JOptionPane.showMessageDialog(null, "Sorteringen lyckades, resultatet skrivet till " + FileNames.OUTFILE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, ("Kunde ej hitta filerna"));
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ("Sorteringen misslyckades"));
			e.printStackTrace();
		}
	}
}
