package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import registration.Register;


public class StatusArea extends JScrollPane implements Observer{
	JTextArea area;
	StartNbrField field;
	public StatusArea(Register register, StartNbrField field){
		register.addObserver(this);
		area = new JTextArea(4,0);
		this.field = field;
		area.setEditable(false);
		this.setViewportView(area);
	}
	
	protected void printStartNbr(String startNbr, long time){
		println("Startnummer " + startNbr + " har fått tiden " + time + " registrerad.");
	}
	
	private void println(String line){
		area.append("\n" + line);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		printStartNbr(field.getText(),System.currentTimeMillis() / 1000);
		field.setText("");
	}
}
