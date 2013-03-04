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
	    try {
			Sorter sorter = new Sorter();

			JOptionPane.showMessageDialog(null, "Sorteringen lyckades, resultatet skrivet till " + sorter.resultfile() + " och " + sorter.sortedresultfile());
		} catch (FileNotFoundException e) {
			String missingFile = e.getMessage().split(" ")[0];
			JOptionPane.showMessageDialog(null, "Kunde ej hitta " + missingFile);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Konfigurationsfilen gick inte att läsa");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sorteringen misslyckades");
			e.printStackTrace();
		}
	}
}
