package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import registration.FileNames;
import registration.Register;

public class StartButton extends AlternativeButton{

	public StartButton(JFrame frame){
		super(frame);
		
	}


	@Override
	protected String getTitle() {
		return "Start";
	}

	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(getTitle(), new Register(FileNames.START));
		frame.dispose();
	}
	
	
}
