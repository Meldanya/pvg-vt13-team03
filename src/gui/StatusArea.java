package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import registration.Register;


public class StatusArea extends JScrollPane implements Observer{
	JTextArea area;
	StartNbrField field;
	Register register;
	public StatusArea(Register register, StartNbrField field){
		this.register = register;
		register.addObserver(this);
		area = new JTextArea(4,0);
		this.field = field;
		area.setEditable(false);
		this.setViewportView(area);
	}
	
	private void println(String line){
		area.append("\n" + line);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		println(register.lastLine());
		field.setText("");
	}
}
