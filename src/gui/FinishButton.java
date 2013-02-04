package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class FinishButton extends AlternativeButton{

	public FinishButton(JFrame frame){
		super(frame);
	}

	@Override
	protected String getTitle() {
		return "M\u00E5l";
	}

	@Override
	protected boolean getChoice() {
		return false;
	}


}
