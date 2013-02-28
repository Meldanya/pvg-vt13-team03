package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import registration.FileNames;
import registration.Register;

public class FinishButton extends AlternativeButton{

	public FinishButton(JFrame frame){
		super(frame);
	}

	@Override
	protected String getTitle() {
		return "M\u00E5l";
	}

	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(getTitle(), new Register(FileNames.FINISH));
		frame.dispose();
	}

}
