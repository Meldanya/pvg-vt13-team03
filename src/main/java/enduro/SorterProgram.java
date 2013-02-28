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

			JOptionPane.showMessageDialog(null, "Sorteringen lyckades, resultatet skrivet till " + sorter.resultfile());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, ("Kunde ej hitta filerna"));
			e.printStackTrace();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, ("Sorteringen misslyckades"));
			e.printStackTrace();
		}
	}
}
