package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import registration.StartRegister;

public class StartButton extends AlternativeButton{

	public StartButton(JFrame frame){
		super(frame);
		
	}


	@Override
	protected String getTitle() {
		return "Start";
	}

	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(getTitle(), new StartRegister());
		frame.dispose();
	}
	
	
}
