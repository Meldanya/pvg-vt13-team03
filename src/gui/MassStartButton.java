package gui;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import racer.RacerTime;
import registration.Register;

public class MassStartButton extends AlternativeButton{

	public MassStartButton(JFrame frame){
		super(frame);
	}

	@Override
	protected String getTitle() {
		return "Masstart";
	}

	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(frame, "Vill du starta alla deltagare?", "", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			Register register = new Register(true);
			RacerTime time = new RacerTime();
			try {
				register.registerMassStart("namnfil.txt", time.toString());
				JOptionPane.showMessageDialog(frame, "Alla deltagares starttid har registrerats");
			} catch (FileNotFoundException e0) {
				JOptionPane.showMessageDialog(frame, "Filen med deltagarnas namn kunde inte öppnas", "", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
	}
}
