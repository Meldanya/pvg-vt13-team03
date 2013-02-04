package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class FinishButton extends AlternativeButton{

	public FinishButton(JFrame frame){
		super(frame);
	}

	@Override
	protected String getTitle() {
		return "Mål";
	}

	@Override
	protected boolean getChoice() {
		return false;
	}


}
