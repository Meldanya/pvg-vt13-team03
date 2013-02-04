package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class StartButton extends AlternativeButton{

	public StartButton(JFrame frame){
		super(frame);
		
	}


	@Override
	protected String getTitle() {
		return "Start";
	}

	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(getTitle(), true);
		frame.dispose();
	}
	
	
}
