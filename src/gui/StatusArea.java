package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import registration.Register;


/**
 * @author dat11ajo
 * 
 * This class is used to give feedback to the user as to what has happened.
 */
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
	
	/**
	 * @param line the String that shall be written to the bottom of the text area
	 */
	private void println(String line){
		area.append("\n" + line);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		println(register.lastLine());
		field.setText(""); // TODO: är det inte någon annans ansvar att tömma inmatningsfältet?
	}
}
